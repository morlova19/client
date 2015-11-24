package controller.interfaces;

import journal.IJournalManager;
import to.TransferObject;

/**
 * Interface of controller.
 * TasksController provides methods to work with tasks and view.
 * Changes model and views.
 */
public interface ITasksController  extends IController{
    /**
     * Opens dialog to create new task.
     */
    void open_add_dialog();
    /**
     * Changes states of view.
     * @param newState new state.
     */
    void change_state(int newState);
    /**
     * Invokes when a button to add was clicked.
     * @param data object that contains parameters of new data.
     */
    void add(TransferObject data);
    /**
     * Invokes when a button to delete was clicked.
     * @param id identifier of the task.
     */
    void delete(int id);
    /**
     * Invokes to display the list of the tasks.
     */
    void load();
    /**
     * Invokes to display the details of the task.
     * @param id identifier of the task.
     */
    void show(int id);
    /**
     * Updates model.
     * @param manager new journal manager.
     */
    void updateModel(IJournalManager manager);
}
