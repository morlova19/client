package utils;

/**
 * Class for constants.
 */
public final class Constants {
    /**
     * Constant that defines state of view for displaying completed tasks.
     */
    public static final int COMPLETED = 1;
    /**
     * Constant that defines state of view for displaying current tasks.
     */
    public static final int CURRENT = 2;

    /**
     * Constant that defines component of view for displaying error in this component.
     * Error message will be displayed in this component if login is not correct.
     */
    public static final int LOGIN = 1;
    /**
     * Constant that defines component of view for displaying error in this component.
     * Error message will be displayed in this component if password is not correct.
     */
    public static final int PASS = 2;
    /**
     * Constant that defines component of view for displaying error in this component.
     * Error message will be displayed in this component if confirmation of password is not correct.
     */
    public static final int CONFIRM_PASS = 3;
    /**
     * Constant that defines component of view for displaying error in this component.
     * Error message will be displayed in this component if date of task is not correct.
     */
    public static final int DATE = 4;
    /**
     * Constant that defines component of view for displaying error in this component.
     * Error message will be displayed in this component if name of task is not correct.
     */
    public static final int NAME = 5;

    /**
     * Error message that will be displayed in {@link #CONFIRM_PASS} if authorization fails.
     */
    public static final String INCORRECT_LOGIN_OR_PASS = "Incorrect login or password";
    /**
     * Error message that will be displayed in {@link #CONFIRM_PASS} if registration fails or
     * in {@link #PASS} if authorization fails because of remote exception.
     */
    public static final String CANNOT_AUTHORIZE = "Cannot authorize, trying again later.";
    /**
     * Error message that will be displayed in {@link #LOGIN} if login is not correct.
     */
    public static final String LOGIN_NOT_EMPTY = "Login cannot be empty";
    /**
     * Error message that will be displayed in {@link #PASS} if password is not correct.
     */
    public static final String PASS_NOT_EMPTY = "Pass cannot be empty";
    /**
     * Error message that will be displayed in {@link #CONFIRM_PASS} if password and confirmation of password are not the same.
     */
    public static final String NOT_SAME_PASS = "Passwords are not the same";
    /**
     * Error message that will be displayed in {@link #CONFIRM_PASS}
     * when user tries to register with login that is already occupied by another user.
     */
    public static final String USER_ALREADY_EXISTS = "User already exists";
    /**
     * Error message that will be displayed in {@link #DATE} if date is not correct.
     */
    public static final String INCORRECT_DATE = "Enter correct date";
    /**
     * Error message that will be displayed in {@link #NAME} if name is not correct.
     */
    public static final String INCORRECT_NAME = "Name cannot be empty";

    public static final String PLEASE_SELECT_TASK = "Select task";
}
