package view.interfaces;

import java.awt.event.ActionListener;

/**
 * Basic interface of view.
 */
public interface IView extends ActionListener {
    /**
     * Opens view.
     */
    void open();

    /**
     * Closes view.
     */
    void close();

    /**
     * Displays error message in specified component of view.
     * @param error error message.
     * @param component constant that defines view component.
     */
    void showError(String error, int component);

    /**
     * Resets error message in specified component.
     * @param component constant that defines view component.
     * @see utils.Constants
     */
    void resetError(int component);

    /**
     * Creates view.
     */
    void createView();

    /**
     * Clears view.
     */
    void clearView();
}
