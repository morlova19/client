package view.interfaces;

import java.util.ArrayList;
/**
 * Interface of view that displays tasks.
 */
public interface ITasksView extends IView {
    /**
     * Updates list of tasks on view.
     * @param tasks_names names of tasks.
     * @param indecies identifiers of tasks.
     */
    void updateList(ArrayList<String> tasks_names, int[] indecies);
    /**
     * Enables button to add or not.
     * @param b if true button will be enabled, else - not enabled.
     */
    void enable_add_button(boolean b);
    /**
     * Displays dialog to confirm.
     * @return answer of confirmation.
     */
    int display_confirm_dialog();
    /**
     * Displays dialog with specified message.
     * @param msg message to display.
     */
    void display_message_dialog(String msg);
}
