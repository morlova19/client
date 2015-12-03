package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote user interface.
 */
public interface IClient extends Remote {
    /**
     * Updates user's data.
     */
    void update() throws RemoteException;
    /**
     * Gets user's login.
     * @return login.
     * @throws RemoteException
     */
    String getLogin() throws RemoteException;
    /**
     *  Gets user's password.
     * @return password.
     * @throws RemoteException
     */
    String getPassword()throws RemoteException;
}
