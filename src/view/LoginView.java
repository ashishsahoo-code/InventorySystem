package view;

import controller.AuthController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();

        Button loginBtn = new Button("Login");
        Label message = new Label();

        loginBtn.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            if (AuthController.login(username, password)) {
                DashboardView.show(stage);
            } else {
                message.setText("Invalid credentials!");
            }
        });

        root.getChildren().addAll(userLabel, userField, passLabel, passField, loginBtn, message);

        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}
