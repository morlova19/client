package view.forms;

import controller.interfaces.ITasksController;
import listeners.CustomMouseListener;
import listeners.CustomWindowListener;
import utils.Constants;
import utils.RegistryUtils;
import view.interfaces.ITasksView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * GUI to display list of the tasks.
 */
public class TasksForm extends JFrame implements ITasksView, CustomWindowListener, CustomMouseListener {
    /**
     * Constant for question of confirmation deleting the data.
     */
    public static final String ARE_YOU_SURE = "Are you sure?";
    /**
     * Constant for message that will be displayed if the task was not selected.
     */
    public static final String PLEASE_SELECT_TASK = "Please, select task";
    /**
     * Constant for title of the confirm dialog.
     */
    public static final String DELETE = "Delete";
    /**
     * Constant for name of the app.
     */
    public static final String TASK_MANAGER = "Task manager";
    /**
     * Displays list of the tasks.
     * By default list of the current tasks.
     */
    private JList<String> taskList;
    /**
     * Button to open the window to add a new task.
     */
    private JButton addButton;
    /**
     * Button for deleting the selected task.
     */
    private JButton deleteButton;
    /**
     * Component to specify a list of tasks to be displayed.
     * If the value of property isSelected is true, then the current tasks will be displayed.
     * The value of property isSelected is true by default;
     */
    private JRadioButton current_rb;
    /**
     * Component to specify a list of tasks to be displayed.
     * If the value of property isSelected is true, then the completed tasks will be displayed.
     */
    private JRadioButton completed_rb;
    /**
     * Container for components of this form.
     */
    private JPanel mainPanel;
    private JLabel user_label;
    /**
     * Controls this form.
     */
    ITasksController c ;
    /**
     * Constant for actionCommand.
     */
    private final String ADD_ACTION = "add data";
    /**
     * Constant for actionCommand.
     */
    private final String DELETE_ACTION = "delete data";
    /**
     * Constant for actionCommand.
     */
    private final String DISPLAY_CURRENT_ACTION = "display current";
    /**
     * Constant for actionCommand.
     */
    private final String DISPLAY_COMPLETED_ACTION = "display completed";
    /**
     * Thread-safe array in which index this is number of the task in {@link #taskList}
     * and value of this element in array this is identifier of this task.
     */
    private AtomicIntegerArray pairs;
    /**
     * Creates and displays form.
     * @param c controller
     * @throws HeadlessException thrown when code that is dependent on a keyboard, display, or mouse
     * is called in an environment that does not support a keyboard, display,
     * or mouse.
     */
    public TasksForm(ITasksController c) throws HeadlessException {
        if(c != null) {
            this.c = c;
        }
    }
    /**
     * Configures specified button.
     * Adds action listener and sets specified actionCommand.
     * @param button button.
     * @param action actionCommand.
     */
    private void configButton(AbstractButton button, String action) {
        button.setActionCommand(action);
        button.addActionListener(this);
    }
    /**
     * Asks to confirm deleting the task.
     * If user confirms, informs the controller.
     */
    private void deleteAction() {
        int index = taskList.getSelectedIndex();
        if(index != -1) {
            c.delete(pairs.get(index));
        }
        else {
            display_message_dialog(PLEASE_SELECT_TASK);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand())
        {
            case ADD_ACTION:
                c.open_add_dialog();
                break;
            case DELETE_ACTION:
                deleteAction();
                break;
            case DISPLAY_COMPLETED_ACTION:
                c.change_state(Constants.COMPLETED);
                break;
            case DISPLAY_CURRENT_ACTION:
                c.change_state(Constants.CURRENT);
            default:
                break;
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == taskList && e.getClickCount() == 2)
        {
            int index = taskList.getSelectedIndex();
            if(index != -1) {

                c.show(pairs.get(index));
            }
        }
    }

    @Override
    public void updateList(ArrayList<String> tasks_names, int[] indecies) {
        if(tasks_names != null) {
            pairs = new AtomicIntegerArray(indecies);
            DefaultListModel<String> listModel = new DefaultListModel<>();
            tasks_names.stream().forEach(s -> listModel.addElement(s));
            taskList.setModel(listModel);
        }
    }

    @Override
    public void enable_add_button(boolean b) {
        addButton.setEnabled(b);
    }

    @Override
    public int display_confirm_dialog() {
        return JOptionPane.showConfirmDialog(getContentPane(), ARE_YOU_SURE, DELETE, JOptionPane.YES_NO_OPTION);
    }

    @Override
    public void display_message_dialog(String msg) {
        JOptionPane.showMessageDialog(getContentPane(),msg);
    }

    @Override
    public void open() {
        pack();
        setVisible(true);
    }

    @Override
    public void close() {
        dispose();
    }

    @Override
    public void showError(String error, int component) {

    }

    @Override
    public void resetError(int component) {

    }

    @Override
    public void createView() {
        setContentPane(mainPanel);
        setTitle(TASK_MANAGER);

        String text = "<html> User: <b> <em>" + RegistryUtils.getClientLogin() + "</em></b></html>";
        user_label.setText(text);
        setIconImage(utils.Icon.getIcon());
        taskList.setFixedCellWidth(200);
        taskList.setVisibleRowCount(15);
        configButton(addButton, ADD_ACTION);
        configButton(deleteButton, DELETE_ACTION);
        taskList.addMouseListener(this);
        configButton(current_rb, DISPLAY_CURRENT_ACTION);
        configButton(completed_rb, DISPLAY_COMPLETED_ACTION);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        c.close();
    }
}
