package controller.classes;

import controller.interfaces.ILoginController;
import observer.Observer;
import utils.Constants;
import utils.RegistryUtils;
import view.forms.AuthorizationForm;
import view.interfaces.IView;
import view.forms.RegistrationForm;

import java.rmi.RemoteException;

public class LoginController implements ILoginController {

    /**
     * View for authorization.
     */
    private IView aView;
    /**
     * View for registration.
     */
    private IView rView;
    /**
     * Observer that will be notified when authorization finishes.
     */
    private Observer observer;

    /**
     * Creates new instance.
     */
    public LoginController() {
        aView = new AuthorizationForm(this);
        rView = new RegistrationForm(this);
    }

    public void start() {
        aView.createView();
        aView.open();
    }
    public void authorize(String login, char[] pass){
        String l = login.trim();
        String p = String.copyValueOf(pass);
        boolean isCorrectLogin = !l.isEmpty();
        boolean isCorrectPass = !p.isEmpty();
        if(isCorrectLogin && isCorrectPass)
        {
            aView.resetError(Constants.LOGIN);
            aView.resetError(Constants.PASS);

            boolean isAuthorized;
            try {
                isAuthorized = RegistryUtils.registerClient(l, p);
                if (isAuthorized) {
                    aView.close();
                    observer.update(l);
                }
                else {
                    aView.showError(Constants.INCORRECT_LOGIN_OR_PASS, Constants.PASS);
                }

            } catch (RemoteException e) {
                aView.showError(Constants.CANNOT_AUTHORIZE, Constants.PASS);
            }
            return;
        }
        if(!isCorrectLogin)
        {
            aView.showError(Constants.LOGIN_NOT_EMPTY, Constants.LOGIN);
        }
        else {
            aView.resetError(Constants.LOGIN);
        }
        if(!isCorrectPass)
        {
            aView.showError(Constants.PASS_NOT_EMPTY, Constants.PASS);
        }
        else {
            aView.resetError(Constants.PASS);
        }
    }
    public void register(String login, char[] pass, char[] confirmPass) {
        String l = login.trim();
        String p = String.copyValueOf(pass);
        String cp = String.copyValueOf(confirmPass);
        boolean isCorrectLogin = !l.isEmpty();
        boolean isCorrectPass = !p.isEmpty();
        boolean isCorrectConfirmPass = p.equals(cp);
        if(isCorrectLogin && isCorrectPass & isCorrectConfirmPass)
        {
            rView.resetError(Constants.LOGIN);
            rView.resetError(Constants.PASS);
            rView.resetError(Constants.CONFIRM_PASS);

            boolean isRegistered ;
            try {
                isRegistered = RegistryUtils.newUser(login, p);
                if (isRegistered) {
                    rView.close();
                }
                else {
                    rView.showError(Constants.USER_ALSO_EXIST,Constants.CONFIRM_PASS);
                }
            } catch (RemoteException e) {
                rView.showError(Constants.CANNOT_AUTHORIZE, Constants.CONFIRM_PASS);
            }
            return;
        }
        if(!isCorrectLogin)
        {
            rView.showError(Constants.LOGIN_NOT_EMPTY, Constants.LOGIN);
        }
        else {
            rView.resetError(Constants.LOGIN);
        }
        if(!isCorrectPass)
        {
            rView.showError(Constants.PASS_NOT_EMPTY, Constants.PASS);
        }
        else {
            rView.resetError(Constants.PASS);
        }
        if(!isCorrectConfirmPass)
        {
            rView.showError(Constants.NOT_SAME_PASS, Constants.CONFIRM_PASS);
        }
        else {
            rView.resetError(Constants.CONFIRM_PASS);
        }

    }

    public void open_registration_dialog()
    {
        rView.createView();
        rView.open();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void close() {
        RegistryUtils.unregisterClient();
        System.exit(1);
    }

}
