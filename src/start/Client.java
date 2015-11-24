package start;

import controller.classes.MainController;
import controller.interfaces.IController;

public class Client {

    public static void main(String[] args) {
            IController mainController = new MainController();
            mainController.start();
    }
}
