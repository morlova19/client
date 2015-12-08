package view.forms;

import controller.interfaces.ITasksController;
import journal.Task;
import to.TransferObject;
import utils.*;
import utils.Icon;
import view.interfaces.ITaskView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * GUI for creating new task.
 * Contains components for entering the name, description, date and contacts of new task.
 */
public class NewTaskDialog extends JDialog implements ITaskView {
    /**
     * Constant for actionCommand of {@link #buttonOK}.
     */
    private static final String OK_ACTION = "OK";
    /**
     * Constant for actionCommand of {@link @buttonCancel}.
     */
    private static final String CANCEL_ACTION = "Cancel";
    /**
     * Container for components.
     */
    private JPanel contentPane;
    /**
     * Button to confirm adding of new task.
     */
    private JButton buttonOK;
    /**
     * Button to cancel adding of new task.
     */
    private JButton buttonCancel;
    /**
     * Field for entering name of task.
     */
    private JTextField name_tf;
    /**
     * Field for entering description of task.
     */
    private JTextArea description_tf;
    /**
     * Field for entering date of execution of task.
     */
    private JFormattedTextField dateField;
    /**
     * Field for entering contacts.
     */
    private JTextArea contacts_tf;
    /**
     * Component to display error message
     * if date of the task is not correct.
     */
    private JLabel date_err_label;
    /**
     * Component to display error message
     * if name of the task is empty.
     */
    private JLabel name_err_label;
    /**
     * Label for {@link #dateField}.
     */
    private JLabel date_label;
    /**
     * Controls this form.
     */
    private ITasksController controller;
    /**
     * Creates dialog and initializes {@link #controller}.
     * @param controller controller.
     */
    public NewTaskDialog(ITasksController controller) {
        if(controller != null) {
            this.controller = controller;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case OK_ACTION:
                add();
                break;
            case CANCEL_ACTION:
                close();
                break;
        }
    }
    /**
     * Validates the entered data.
     * If the data is correct then invokes method
     * of the {@link #controller} and transfers to it the entered data.
     */
    private void add() {
        String name = name_tf.getText().trim();
        String desc = description_tf.getText().trim();
        String contacts = contacts_tf.getText().trim();

        Date date;
        date = DateUtil.parse((String)dateField.getValue());

        controller.add(createTransferObject(name, date, desc, contacts));
    }
    /**
     * Creates TransferObject with given parameters.
     * @param name the entered name.
     * @param date the entered date.
     * @param desc the entered description.
     * @param contacts the entered contacts.
     * @return object of TransferObject class.
     */
    private TransferObject createTransferObject(String name, Date date,
                                                String desc, String contacts) {
        TransferObject to = new TransferObject();
        to.setName(name);
        to.setDescription(desc);
        to.setDate(date);
        to.setContacts(contacts);
        return to;
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
        switch (component)
        {
            case Constants.NAME:
                configName(error,BorderFactory.createLineBorder(Color.red));
                break;
            case Constants.DATE:
                configDate(error,BorderFactory.createLineBorder(Color.red));
                break;
        }
    }

    @Override
    public void resetError(int component) {
        switch (component)
        {
            case Constants.NAME:
                configName("", UIManager.getBorder("TextField.border"));
                break;
            case Constants.DATE:
                configDate("", UIManager.getBorder("TextField.border"));
                break;
        }
    }
    /**
     * Changes appearance of the {@link #name_tf} and {@link #name_err_label}.
     * @param text text that will be set into {@link #name_err_label}.
     * @param border border that will be set into {@link #name_tf}.
     */
    private void configName(String text, Border border) {
        name_err_label.setText(text);
        name_err_label.setForeground(Color.red);
        name_tf.setBorder(border);
        pack();
    }
    /**
     * Changes appearance of the {@link #dateField} and {@link #date_err_label}.
     * @param text text that will be set into {@link #date_err_label}.
     * @param border border that will be set into {@link #dateField}.
     */
    private void configDate(String text, Border border) {
        date_err_label.setForeground(Color.red);
        date_err_label.setText(text);
        dateField.setBorder(border);
        pack();
    }

    @Override
    public void createView() {
        setContentPane(contentPane);
        setTitle("New task");
        setIconImage(Icon.getIcon());
        date_label.setText("<html>Date<br>(dd.mm.yyyy hh:mm)</html>");
        configButton(buttonOK, OK_ACTION);
        configButton(buttonCancel, CANCEL_ACTION);

        configDateField();

        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

    }

    @Override
    public void clearView() {}

    /**
     * Configures {@link #dateField}.
     * Sets date format and default value.
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

        String date = DateUtil.format(new Date(System.currentTimeMillis()));
        dateField.setValue(date);
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
    public void displayTaskName(String name) {}

    @Override
    public void displayTaskDesc(String desc) {}

    @Override
    public void displayTaskDate(String date) {}

    @Override
    public void displayTaskContacts(String contacts) {}
}
