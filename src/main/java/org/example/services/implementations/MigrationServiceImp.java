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
    public void migrateEspace() throws SQLException {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Espace (" +
                "id SERIAL PRIMARY KEY, " +
                "Type VARCHAR(255) NOT NULL, " +
                "creatorid INT NOT NULL, " +
                "description VARCHAR(255) NOT NULL, " +
                "name VARCHAR(255) NOT NULL" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'Espace' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void migrateServices() throws SQLException {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS services (" +
                "id SERIAL PRIMARY KEY, " +
                "creatorid INT NOT NULL, " +
                "description VARCHAR(255) NOT NULL, " +
                "name VARCHAR(255) NOT NULL" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'Espace' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void migrateAbonnment() throws SQLException {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Abonnments (" +
                "id SERIAL PRIMARY KEY, " +
                "spaceId INT NOT NULL, " +
                "description VARCHAR(255) NOT NULL, " +
                "name VARCHAR(255) NOT NULL," +
                "price VARCHAR(255) NOT NULL" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'Espace' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void EspaceService() throws SQLException {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS EspaceServices (" +
                "id SERIAL PRIMARY KEY, " +
                "spaceId INT NOT NULL, " +
                "serviceid INT NOT NULL " +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'Espace' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Main() throws SQLException {
        this.migrateUsersTable();
        this.migratePaymentMethodsTable();
        this.migrateEspacetypesTable();
        this.migrateEspace();
        this.migrateAbonnment();
        this.migrateServices();
        this.EspaceService();
    }


}
