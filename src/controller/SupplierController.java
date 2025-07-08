package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Supplier;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SupplierController {

    public static ObservableList<Supplier> getAllSuppliers() {
        ObservableList<Supplier> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM suppliers";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Supplier(rs.getInt("id"), rs.getString("name"), rs.getString("contact")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void addSupplier(String name, String contact) {
        String sql = "INSERT INTO suppliers(name, contact) VALUES(?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, contact);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteSupplier(int id) {
        String sql = "DELETE FROM suppliers WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
