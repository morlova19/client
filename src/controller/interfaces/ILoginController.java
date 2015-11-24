package controller.interfaces;

import observer.Observer;

/**
 * Controller for authorization and registration.
 */
public interface ILoginController extends IController{
    /**
     * Authorizes user with specified login and password.
     * @param login user's login.
     * @param pass user's password.
     */
    void authorize(String login, char[] pass);
    /**
     * Registers new user with specified login and password.
     * @param login user's login.
     * @param pass user's password.
     * @param confirmPass confirmation of user's password.
     */
    void register(String login, char[] pass, char[] confirmPass);
    /**
     * Opens dialog for registration.
     */
    void open_registration_dialog();
    /**
     * Registers observer.
     * @param observer observer.
     */
    void registerObserver(Observer observer);
}
