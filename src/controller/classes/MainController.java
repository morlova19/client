package controller.classes;

import controller.interfaces.IController;
import controller.interfaces.ILoginController;
import controller.interfaces.ITasksController;
import journal.IJournalManager;
import model.IModel;
import model.Model;
import observer.Observer;
import utils.RegistryUtils;

/**
 * Controller that connect two parts of app.
 * First part is registration and authorization.
 * The second - working with tasks.
 * When this controller starts the first part will be started.
 * If first part is finished invokes method {@link #update(String)} and second part will be started.
 */
public class MainController implements IController, Observer {
    @Override
    public void start() {
        ILoginController lc = new LoginController();
        lc.registerObserver(this);
        lc.start();
    }

    @Override
    public void close() {
        RegistryUtils.unregisterClient();
        System.exit(1);
    }

    @Override
    public void update(String login) {
        IJournalManager manager = RegistryUtils.getJournalManagerInstance(login);
        if (manager != null) {
            IModel model = new Model(manager);
            ITasksController tc = new TasksController(model);
            tc.start();
        }
    }
}
