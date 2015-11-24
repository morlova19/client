package view.forms;

import controller.interfaces.ITasksController;
import view.interfaces.ITaskView;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * GUI to display the information about the task.
 * It contains components for displaying task's details.
 */
public class TaskDialog extends JDialog implements ITaskView, ActionListener {
    /**
     * Constant for actionCommand.
     */
    private static final String OK_ACTION = "OK";
    /**
     * Constant for actionCommand.
     */
    private static final String CANCEL_ACTION = "Cancel";
    /**
     * Container for components.
     */
    private JPanel contentPane;
    /**
     * Button to confirm.
     */
    private JButton buttonOK;
    /**
     * Button to cancel.
     */
    private JButton buttonCancel;
    /**
     * Field to display task's name.
     */
    private JTextField name_tf;
    /**
     *  Field to display task's description.
     */
    private JTextArea description_tf;
    /**
     *  Field to display task's date.
     */
    private JFormattedTextField dateField;
    /**
     * Field to display contacts.
     */
    private JTextArea contacts_tf;
    /**
     * Label for {@link @dateField}.
     */
    private JLabel date_label;
    /**
     * Controls this form.
     */
    ITasksController c;

    /**
     * Creates dialog and initializes {@link #c}.
     * @param c controller.
     */
    public TaskDialog(ITasksController c) {
        if(c != null) {
            this.c = c;
        }
    }
    /**
     * Configures {@link #dateField}.
     * Sets format.
     */
    private void configDateField() {
        MaskFormatter dateFormatter = null;
        try {
            dateFormatter = new MaskFormatter("##.##.#### ##:##");
            dateFormatter.setPlaceholderCharacter('_');

        } catch (ParseException e) {

        }
        DefaultFormatterFactory dateFormatterFactory = new
                DefaultFormatterFactory(dateFormatter);
        dateField.setFormatterFactory(dateFormatterFactory);
    }
    /**
     * Configures given button.
     * Adds ActionListener and sets actionCommand.
     * @param button button that will be configured.
     * @param action actionCommand.
     */
    private void configButton(AbstractButton button, String action) {
        button.addActionListener(this);
        button.setActionCommand(action);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand())
        {
            case OK_ACTION:
                dispose();
                break;
            case CANCEL_ACTION:
                dispose();
                break;
        }
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
    public void showError(String error, int component) {}

    @Override
    public void resetError(int component) {}

    @Override
    public void createView() {
        setContentPane(contentPane);

        setTitle("Task dialog");
        setIconImage(utils.Icon.getIcon());

        date_label.setText("<html>Date<br>(dd.mm.yyyy hh:mm)</html>");
        configDateField();

        configButton(buttonOK, OK_ACTION);
        configButton(buttonCancel, CANCEL_ACTION);

        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
    }

    @Override
    public void displayTaskName(String name) {
        name_tf.setText(name);
        name_tf.setEditable(false);
    }

    @Override
    public void displayTaskDesc(String desc) {
        description_tf.setText(desc);
        description_tf.setEditable(false);
    }

    @Override
    public void displayTaskDate(String date) {
        dateField.setValue(date);
        dateField.setEditable(false);
    }

    @Override
    public void displayTaskContacts(String contacts) {
        contacts_tf.setText(contacts);
        contacts_tf.setEditable(false);
    }
}
