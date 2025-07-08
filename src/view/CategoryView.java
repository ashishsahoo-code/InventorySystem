package view;

import controller.CategoryController;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Category;

public class CategoryView {

    public static void show(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));

        TableView<Category> table = new TableView<>();
        TableColumn<Category, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()));

        TableColumn<Category, String> nameCol = new TableColumn<>("Category");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        table.getColumns().addAll(idCol, nameCol);

        ObservableList<Category> categories = CategoryController.getAllCategories();
        table.setItems(categories);

        TextField nameField = new TextField();
        nameField.setPromptText("Category Name");

        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");

        addBtn.setOnAction(e -> {
            String name = nameField.getText();
            CategoryController.addCategory(name);
            table.setItems(CategoryController.getAllCategories());
        });

        deleteBtn.setOnAction(e -> {
            Category selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                CategoryController.deleteCategory(selected.getId());
                table.setItems(CategoryController.getAllCategories());
            }
        });

        root.getChildren().addAll(table, nameField, addBtn, deleteBtn);

        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("Category Management");
        stage.show();
    }
}
