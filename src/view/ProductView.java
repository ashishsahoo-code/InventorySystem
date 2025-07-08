package view;

import controller.ProductController;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Product;
import util.PDFUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ProductView {

    public static void show(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(20));

        TableView<Product> table = new TableView<>();
        TableColumn<Product, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()));

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<Product, Number> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQuantity()));

        table.getColumns().addAll(idCol, nameCol, qtyCol);

        ObservableList<Product> products = ProductController.getAllProducts();
        table.setItems(products);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField qtyField = new TextField();
        qtyField.setPromptText("Quantity");

        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");
        Button exportBtn = new Button("Export PDF");

        addBtn.setOnAction(e -> {
            String name = nameField.getText();
            int qty = Integer.parseInt(qtyField.getText());
            ProductController.addProduct(name, qty);
            table.setItems(ProductController.getAllProducts());
        });

        deleteBtn.setOnAction(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                ProductController.deleteProduct(selected.getId());
                table.setItems(ProductController.getAllProducts());
            }
        });

        exportBtn.setOnAction(e -> {
            List<Product> productList = products.stream().collect(Collectors.toList());
            PDFUtil.exportProducts(productList, "products_report.pdf");
        });

        root.getChildren().addAll(table, nameField, qtyField, addBtn, deleteBtn, exportBtn);

        stage.setScene(new Scene(root, 500, 400));
        stage.setTitle("Product Management");
        stage.show();
    }
}
