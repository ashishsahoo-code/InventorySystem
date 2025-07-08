package InventorySystem;

import javafx.application.Application;
import javafx.stage.Stage;
import util.DBInitializer;
import view.LoginView;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        DBInitializer.initialize();
        new LoginView().start(primaryStage);
    }
}
