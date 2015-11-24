package view.forms;

import controller.interfaces.ILoginController;
import listeners.CustomKeyListener;
import utils.Constants;
import view.interfaces.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI for user registration.
 */
public class RegistrationForm extends JDialog implements IView, ActionListener, CustomKeyListener {
    /**
     * Constant for actionCommand of {@link #buttonOK}.
     */
    public static final String SUBMIT = "submit";
    /**
     * Constant for actionCommand of {@link #buttonCancel}.
     */
    public static final String CANCEL = "cancel";
    /**
     * Container for components of this form.
     */
    private JPanel contentPane;
    /**
     * Button to confirm registration.
     */
    private JButton buttonOK;
    /**
     * Button to cancel registration.
     */
    private JButton buttonCancel;
    /**
     * Field for entering login of new user.
     */
    private JTextField loginTextField;
    /**
     * Field for entering password of new user.
     */
    private JPasswordField passwordField;
    /**
     * Field for entering confirmation of the password of new user.
     */
    private JPasswordField confirmPasswordField;
    /**
     * Component to display error message
     * if login is empty.
     */
    private JLabel login_err_label;
    /**
     * Component to display error message
     * if password is empty.
     */
    private JLabel pass_err_label;
    /**
     * Component to display error message
     * if password and confirmation of password are not the same
     * and also if registration failed.
     */
    private JLabel conf_pass_err_label;
    /**
     * Controls this form.
     */
    private ILoginController controller;
    /**
     * Creates form.
     * @param controller controller.
     */
    public RegistrationForm(ILoginController controller) {
        this.controller = controller;
    }

    @Override
    public void createView() {
        setContentPane(contentPane);
        setTitle("Registration");
        setIconImage(utils.Icon.getIcon());

        conf_pass_err_label.setForeground(Color.red);
        login_err_label.setForeground(Color.red);
        pass_err_label.setForeground(Color.red);

        passwordField.addKeyListener(this);
        confirmPasswordField.addKeyListener(this);

        setModal(true);

        buttonOK.addActionListener(this);
        buttonOK.setActionCommand(SUBMIT);
        buttonCancel.setActionCommand(CANCEL);
        buttonCancel.addActionListener(this);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
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
            case Constants.LOGIN:
                showLoginError(error);
                break;
            case Constants.PASS:
                showPassError(error);
                break;
            case Constants.CONFIRM_PASS:
                showConfirmPassError(error);
                break;
        }
    }
    public void resetError(int component)
    {
        switch (component)
        {
            case Constants.LOGIN:
                resetLoginError();
                break;
            case Constants.PASS:
                resetPassError();
                break;
            case Constants.CONFIRM_PASS:
                resetConfirmPassError();
                break;
        }
    }

    /**
     * Resets error message in {@link #login_err_label} and changes appearance of {@link #loginTextField}.
     */
    public void resetLoginError() {
        loginTextField.setBorder(UIManager.getBorder("TextField.border"));
        login_err_label.setText("");
        pack();

    }
    /**
     * Resets error message in {@link #pass_err_label} and changes appearance of {@link #passwordField}.
     */
    public void resetPassError() {
        passwordField.setBorder(UIManager.getBorder("TextField.border"));
        pass_err_label.setText("");
        pack();
    }
    /**
     * Resets error message in {@link #conf_pass_err_label} and changes appearance of {@link #confirmPasswordField}.
     */
    public void resetConfirmPassError() {
        confirmPasswordField.setBorder(UIManager.getBorder("TextField.border"));
        conf_pass_err_label.setText("");
        pack();
    }
    /**
     * Sets error message in {@link #pass_err_label} and changes appearance of {@link #passwordField}.
     */
    public void showPassError(String error) {
        passwordField.setBorder(BorderFactory.createLineBorder(Color.red));
        pass_err_label.setText(error);
        pack();
    }
    /**
     * Sets error message in {@link #login_err_label} and changes appearance of {@link #loginTextField}.
     */
    public void showLoginError(String error) {
        loginTextField.setBorder(BorderFactory.createLineBorder(Color.red));
        login_err_label.setText(error);
        pack();
    }
    /**
     * Sets error message in {@link #conf_pass_err_label} and changes appearance of {@link #confirmPasswordField}.
     */
    public void showConfirmPassError(String error) {
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.red));
        conf_pass_err_label.setText(error);
        pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case SUBMIT:
                controller.register(loginTextField.getText(),
                        passwordField.getPassword(),
                        confirmPasswordField.getPassword());
                break;
            case CANCEL:
                close();
                break;

        }
    }
}
