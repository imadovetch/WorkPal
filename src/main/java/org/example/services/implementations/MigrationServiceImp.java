package org.example.services.implementations;

import org.example.config.PostgreSQLConnection;
import org.example.services.interfaces.MigrationServiceInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MigrationServiceImp implements MigrationServiceInterface {


    public void migrateUsersTable() throws SQLException {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "email VARCHAR(255) NOT NULL UNIQUE, " +
                "password VARCHAR(255) NOT NULL, " +
                "phone VARCHAR(20), " + // Nullable phone field
                "role VARCHAR(50) NOT NULL" + // Role field
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'users' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to migrate the 'PaymentMethods' table
    public void migratePaymentMethodsTable() throws SQLException {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS PaymentMethods (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'PaymentMethods' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to migrate the 'Espacetypes' table
    public void migrateEspacetypesTable() throws SQLException {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Espacetypes (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL" + // Name of space type
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'Espacetypes' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void Main() throws SQLException {
        this.migrateUsersTable();
        this.migratePaymentMethodsTable();
        this.migrateEspacetypesTable();

    }


}
