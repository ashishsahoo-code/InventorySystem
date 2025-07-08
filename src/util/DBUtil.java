package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;

public class DBUtil {

    private static final String DB_PATH = "db/inventory.db";

    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;

    // ✅ Used by all controllers to get database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // ✅ Optional: Ensure DB file and folders exist
    public static void ensureDatabase() {
        try {
            File dbFile = new File(DB_PATH);
            if (!dbFile.exists()) {
                File parentDir = dbFile.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }
                dbFile.createNewFile();

                // Optional: create basic schema here
                try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
                    String sql = """
                        CREATE TABLE IF NOT EXISTS users (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            username TEXT NOT NULL UNIQUE,
                            password TEXT NOT NULL
                        );

                        INSERT INTO users (username, password) VALUES ('admin', 'admin');

                        CREATE TABLE IF NOT EXISTS category (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL UNIQUE
                        );

                        CREATE TABLE IF NOT EXISTS supplier (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL,
                            contact TEXT
                        );

                        CREATE TABLE IF NOT EXISTS product (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL,
                            quantity INTEGER NOT NULL,
                            category_id INTEGER,
                            supplier_id INTEGER,
                            FOREIGN KEY (category_id) REFERENCES category(id),
                            FOREIGN KEY (supplier_id) REFERENCES supplier(id)
                        );
                    """;
                    stmt.executeUpdate(sql);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
