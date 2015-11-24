package observer;
/**
 * Interface of observer.
 * Notifications come to observer from observable object.
 */
public interface Observer {
    /**
     * Invoked when changes occurred.
     */
    void update(String login);
}
