package model;

import journal.IJournalManager;
import journal.Task;


import java.rmi.RemoteException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Part of taskmgr.
 */
public class Model implements IModel {

    private IJournalManager manager;

    public Model(IJournalManager manager) {
        this.manager = manager;
    }
    @Override
    public void add(Task task) {
        if(task != null) {
            try {
                manager.add(task);
            } catch (RemoteException e) {

            }
        }
    }
    @Override
    public void delete(int id) {
        try {
            manager.delete(id);
        } catch (RemoteException e) {

        }
    }
    @Override
    public CopyOnWriteArrayList<Task> getCurrentTasks() {
        try {
            return manager.getCurrentTasks();
        }
        catch (RemoteException e) {
            return null;
        }
    }
    public CopyOnWriteArrayList<Task> getCompletedTasks() {
        try {
            return manager.getCompletedTasks();
        }
        catch (RemoteException e) {
            return null;
        }
    }
    @Override
    public Task get(int id) {
        try {
            return manager.get(id);
        } catch (RemoteException e) {
            return null;
        }

    }
}
