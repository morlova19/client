package view.interfaces;

/**
 * Interface of view that displays task's details.
 */
public interface ITaskView extends IView {
    /**
     * Displays task's name.
     * @param name name.
     */
    void displayTaskName(String name);

    /**
     * Displays task's description.
     * @param desc description.
     */
    void displayTaskDesc(String desc);

    /**
     * Displays task's date.
     * @param date date.
     */
    void displayTaskDate(String date);

    /**
     * Displays task's contacts.
     * @param contacts contacts.
     */
    void displayTaskContacts(String contacts);
}
