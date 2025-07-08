package view;

import controller.SupplierController;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Supplier;

public class SupplierView {

    public static void show(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));

        TableView<Supplier> table = new TableView<>();
        TableColumn<Supplier, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()));

        TableColumn<Supplier, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<Supplier, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getContact()));

        table.getColumns().addAll(idCol, nameCol, contactCol);

        ObservableList<Supplier> suppliers = SupplierController.getAllSuppliers();
        table.setItems(suppliers);

        TextField nameField = new TextField();
        nameField.setPromptText("Supplier Name");

        TextField contactField = new TextField();
        contactField.setPromptText("Contact Info");

        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");

        addBtn.setOnAction(e -> {
            String name = nameField.getText();
            String contact = contactField.getText();
            SupplierController.addSupplier(name, contact);
            table.setItems(SupplierController.getAllSuppliers());
        });

        deleteBtn.setOnAction(e -> {
            Supplier selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                SupplierController.deleteSupplier(selected.getId());
                table.setItems(SupplierController.getAllSuppliers());
            }
        });

        root.getChildren().addAll(table, nameField, contactField, addBtn, deleteBtn);

        stage.setScene(new Scene(root, 450, 350));
        stage.setTitle("Supplier Management");
        stage.show();
    }
}
