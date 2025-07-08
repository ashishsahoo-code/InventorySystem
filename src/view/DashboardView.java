package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardView {

    public static void show(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));

        Button productBtn = new Button("Manage Products");
        Button categoryBtn = new Button("Manage Categories");
        Button supplierBtn = new Button("Manage Suppliers");

        productBtn.setOnAction(e -> ProductView.show(stage));
        categoryBtn.setOnAction(e -> CategoryView.show(stage));
        supplierBtn.setOnAction(e -> SupplierView.show(stage));

        root.getChildren().addAll(productBtn, categoryBtn, supplierBtn);

        stage.setScene(new Scene(root, 400, 200));
        stage.setTitle("Dashboard");
        stage.show();
    }
}
