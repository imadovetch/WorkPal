package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {

    private final String url = "jdbc:postgresql://localhost:5432/WorkPal";
    private final String user = "postgres";
    private final String password = "1111";

    private static PostgreSQLConnection instance;
    private Connection connection;

    private PostgreSQLConnection() {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PostgreSQLConnection getInstance() {
        if (instance == null) {
            instance = new PostgreSQLConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
