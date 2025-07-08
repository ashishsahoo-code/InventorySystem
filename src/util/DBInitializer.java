package util;

import java.nio.file.*;
import java.sql.*;

public class DBInitializer {
    public static void initialize() {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = Files.readString(Path.of("db/schema.sql"));
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("PRAGMA foreign_keys = ON;");
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
