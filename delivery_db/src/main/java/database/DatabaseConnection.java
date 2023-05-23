package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection singleInstance = new DatabaseConnection();
    private Connection connection;

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/delivery_db", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getSingleInstance() {
        return singleInstance;
    }

    public Connection getConnection() {
        return connection;
    }
}


