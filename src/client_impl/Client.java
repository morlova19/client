package client_impl;

import client.IClient;
import controller.classes.TasksController;
import journal.IJournalManager;
import utils.RegistryUtils;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Client extends UnicastRemoteObject implements IClient,Serializable {
    private String login;
    private String pass;
    public Client(String login, String pass) throws RemoteException {
        super();
        this.login = login;
        this.pass = pass;
    }

    public void update() {
        IJournalManager manager;
        manager = RegistryUtils.getJournalManagerInstance(login);
        if (manager != null) {
            TasksController.getInstance().updateModel(manager);
        }
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return pass;
    }

}
