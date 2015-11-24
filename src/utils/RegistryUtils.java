package utils;


import callback_impl.CallbackClientImpl;
import callback.ICallbackClient;
import callback.ICallbackServer;
import journal.IJournalManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Class for working with rmi registry and remotes objects.
 */
public class RegistryUtils {
    /**
     * Rmi registry.
     */
    private static Registry registry;
    /**
     * Remote server for registration clients.
     */
    private static ICallbackServer server;
    /**
     * Client for registration on server.
     */
    private static ICallbackClient client;
    /**
     * Remote journal manager.
     */
    private static IJournalManager manager;

    /**
     * Searches remote server.
     * @return remote server.
     */
    private static void getServerInstance() {
        getRegistryInstance();
        if(server == null)
        {
            try {
                server = (ICallbackServer) registry.lookup("IAuthorizationService");
            } catch (RemoteException | NotBoundException e) {
                server = null;
            }
        }
    }

    /**
     * Gets journal manager from {@link #registry} by specified login.
     * @param login login by which journal manager will be found in {@link #registry}.
     * @return journal manager.
     */
    public static IJournalManager getJournalManagerInstance(String login)
    {
        getRegistryInstance();
        if(manager == null)
        {
            try {
                manager = (IJournalManager) registry.lookup(login);
            } catch (RemoteException | NotBoundException e) {
                manager = null;
            }
        }
        return manager;
    }

    /**
     * Intializes {@link #registry}.
     */
    private static void getRegistryInstance() {
        if(registry == null)
        {
            try {
                registry = LocateRegistry.getRegistry("localhost", 7777);

            } catch (RemoteException e) {
                registry = null;
            }
        }
    }

    /**
     * Registers client with specified login and password on server.
     * @param login login.
     * @param pass password.
     * @return true if registration is successful, else - false.
     * @throws RemoteException
     * @see RemoteException
     */
    public static boolean registerClient(String login, String pass) throws RemoteException {
        getServerInstance();

        client = new CallbackClientImpl(login, pass);
        if(server != null) {
            return server.registerUserInterface(client);
        }
        else {
            return false;
        }

    }

    /**
     * Unregister {@link #client}.
     */
    public static void unregisterClient()
    {
        getServerInstance();
        if(client != null)
        {
            try {
                server.unregisterUserInterface(client);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getClientLogin() {
        try {
            return client.getLogin();
        } catch (RemoteException e) {
            return "";
        }
    }

    /**
     * Creates new user with specified login and password.
     * @param login login.
     * @param pass password.
     * @return true if new user was created, else - false.
     * @throws RemoteException
     */
    public static boolean newUser(String login, String pass) throws RemoteException {
        getServerInstance();
        if(server != null)
        {
            return server.newUser(new CallbackClientImpl(login, pass));
        }
        return false;
    }
}
