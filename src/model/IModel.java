package model;

import journal.Task;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The Model provides methods for working with data, such as
 * adding, deleting and changing data.
 */
public interface IModel {
    /**
     * Adds data.
     * @param task data for adding.
     */
    void add(Task task);
    /**
     * Deletes data.
     * @param id identifier of task.
     */
    void delete(int id);
    /**
     * Gets list of current tasks.
     * @return list of current tasks.
     */
    CopyOnWriteArrayList<Task> getCurrentTasks();
    /**
     * Gets list of completed tasks.
     * @return list of completed tasks.
     */
    CopyOnWriteArrayList<Task> getCompletedTasks();

    /**
     * Gets the task by identifier.
     * Returns null if the task was not found.
     * @param id identifier of the task.
     * @return task that was found.
     */
    Task get(int id);

}
