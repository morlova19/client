package controller.classes;

import controller.interfaces.ITasksController;
import journal.IJournalManager;
import model.Model;
import utils.Constants;
import utils.DateUtils;
import journal.Task;
import model.IModel;
import to.TransferObject;
import utils.RegistryUtils;
import view.forms.NewTaskDialog;
import view.forms.TaskDialog;
import view.forms.TasksForm;
import view.interfaces.ITaskView;
import view.interfaces.ITasksView;

import javax.swing.*;
import java.util.ArrayList;

public class TasksController implements ITasksController {
    /**
     * Model that works with data.
     */
    private IModel model;

    /**
     * View for displaying tasks.
     */
    private ITasksView tasksView;

    /**
     * View for creating new task.
     */
    private ITaskView newTaskView;

    /**
     * View for displaying task's details.
     */
    private ITaskView taskView;

    /**
     * Current state of {@link #tasksView}.
     */
    private int current_state;

    /**
     * Instance of this controller.
     */
    private static ITasksController controller;
    /**
     * Array in which index this is number of the task in {@link #tasksView}
     * and value of this element in array this is identifier of this task.
     */
    private int[] pairs;
    /**
     * Constructs new controller.
     * Creates and displays GUI.
     * @param model model.
     */


    public TasksController(IModel model)  {
        if(model != null)
        {
            this.model = model;
        }
        controller = this;
    }

    public static ITasksController getInstance() {
        return controller;
    }

    @Override
    public void open_add_dialog() {
        newTaskView = new NewTaskDialog(this);
        newTaskView.createView();
        newTaskView.open();
    }

    @Override
    public void change_state(int newState) {
        current_state = newState;
        load();
    }

    @Override
    public void add(TransferObject data) {

        boolean isCorrectName = !data.getName().isEmpty();
        boolean isCorrectDate = DateUtils.isCorrect(data.getDate());

        if(isCorrectName && isCorrectDate) {
            newTaskView.resetError(Constants.DATE);
            newTaskView.resetError(Constants.NAME);
            Task t = new Task(data);
            model.add(t);
            newTaskView.close();
            load();
            return;
        }
        if(!isCorrectDate){
            newTaskView.showError(Constants.INCORRECT_DATE,Constants.DATE);
        }
        else {
            newTaskView.resetError(Constants.DATE);
        }

        if(!isCorrectName)
        {
            newTaskView.showError(Constants.INCORRECT_NAME,Constants.NAME);
        }
        else {
            newTaskView.resetError(Constants.NAME);
        }
    }
    @Override
    public void delete(int index) {

        if(index != -1) {
            int answer = tasksView.display_confirm_dialog();
            if (answer == JOptionPane.YES_OPTION) {
                model.delete(pairs[index]);
                load();
            }
        }
        else {
            tasksView.display_message_dialog(Constants.PLEASE_SELECT_TASK);
        }
    }
    @Override
    public void load() {
        if(current_state == Constants.COMPLETED)
        {
            tasksView.enable_add_button(false);

            pairs = new int[model.getCompletedTasks().size()];
            int[] index = {0};

           ArrayList<String> tasks_names = new ArrayList<>();
            model.getCompletedTasks().stream().forEach((task -> {
                        tasks_names.add(task.getName());
                        pairs[index[0]] =  task.getID();
                        index[0]++; }));

            tasksView.updateList(tasks_names);
            tasksView.open();
        }
        else {
            tasksView.enable_add_button(true);

            pairs = new int[model.getCurrentTasks().size()];
            int[] index = {0};
            ArrayList<String> tasks_names = new ArrayList<>();

            model.getCurrentTasks().stream().forEach((task -> {
                tasks_names.add(task.getName());
                pairs[index[0]] =  task.getID();
                index[0]++; }));

            tasksView.updateList(tasks_names);
            tasksView.open();
        }
    }
    @Override
    public void show(int index) {
        if(index != -1) {
            taskView = new TaskDialog(this);
            taskView.createView();
            Task t = model.get(pairs[index]);
            if (t != null) {
                taskView.displayTaskName(t.getName());
                taskView.displayTaskDesc(t.getDescription());
                taskView.displayTaskDate(DateUtils.format(t.getDate()));
                taskView.displayTaskContacts(t.getContacts());
                taskView.open();
            }
        }
    }

    @Override
    public void updateModel(IJournalManager manager) {
        this.model = new Model(manager);
        load();
    }

    @Override
    public void start() {
        tasksView = new TasksForm(this);
        tasksView.createView();
        current_state = Constants.CURRENT;
        load();
    }

    @Override
    public void close() {
        RegistryUtils.unregisterClient();
        System.exit(1);
    }
}
