package org.example.services.implementations;

import org.example.config.PostgreSQLConnection;
import org.example.services.interfaces.MigrationServiceInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MigrationServiceImp implements MigrationServiceInterface {

    // Helper method to execute all migrations in one SQL query
    public void migrateAllTables() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = PostgreSQLConnection.getInstance().getConnection();
            statement = connection.createStatement();

            // Combine all table creation queries into a single query
            String createAllTablesSQL =
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id SERIAL PRIMARY KEY, " +
                            "email VARCHAR(255) NOT NULL UNIQUE, " +
                            "name VARCHAR(255)  UNIQUE, " +
                            "password VARCHAR(255) NOT NULL, " +
                            "phone VARCHAR(20)); " +

                            "CREATE TABLE IF NOT EXISTS admins (" +

                            ") INHERITS (users); " +

                            "CREATE TABLE IF NOT EXISTS members (" +

                            ") INHERITS (users); " +

                            "CREATE TABLE IF NOT EXISTS managers (" +

                            "PRIMARY KEY (id) " +  // Explicit primary key for managers
                            ") INHERITS (users); " +

                            "CREATE TABLE IF NOT EXISTS PaymentMethods (" +
                            "id SERIAL PRIMARY KEY, " +
                            "name VARCHAR(255) NOT NULL); " +

                            "CREATE TABLE IF NOT EXISTS Espacetypes (" +
                            "id SERIAL PRIMARY KEY, " +
                            "name VARCHAR(255) NOT NULL UNIQUE); " +

                            "CREATE TABLE IF NOT EXISTS Espace (" +
                            "id SERIAL PRIMARY KEY, " +
                            "typeid INT NOT NULL, " +
                            "creatorid INT NOT NULL, " +
                            "description VARCHAR(255) NOT NULL, " +
                            "name VARCHAR(255) NOT NULL UNIQUE, " +
                            "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                            "CONSTRAINT fk_creator FOREIGN KEY (creatorid) REFERENCES managers(id) ON DELETE CASCADE, " +  // Corrected foreign key reference
                            "CONSTRAINT fk_type FOREIGN KEY (typeid) REFERENCES Espacetypes(id) ON DELETE CASCADE); " +

                            "CREATE TABLE IF NOT EXISTS services (" +
                            "id SERIAL PRIMARY KEY, " +
                            "creatorid INT NOT NULL, " +
                            "description VARCHAR(255) NOT NULL, " +
                            "name VARCHAR(255) NOT NULL UNIQUE, " +
                            "CONSTRAINT fk_creator_service FOREIGN KEY (creatorid) REFERENCES managers(id) ON DELETE CASCADE); " +

                            "CREATE TABLE IF NOT EXISTS Abonnments (" +
                            "id SERIAL PRIMARY KEY, " +
                            "spaceId INT NOT NULL, " +
                            "description VARCHAR(255) NOT NULL, " +
                            "name VARCHAR(255) NOT NULL UNIQUE, " +
                            "price VARCHAR(255) NOT NULL, " +
                            "CONSTRAINT fk_space FOREIGN KEY (spaceId) REFERENCES Espace(id) ON DELETE CASCADE); " +

                            "CREATE TABLE IF NOT EXISTS Reservations (" +
                            "id SERIAL PRIMARY KEY, " +
                            "userid INT NOT NULL, " +
                            "spaceid INT NOT NULL, " +
                            "type VARCHAR(255) , " +
                            "abonnmentid INT ); " +

                            "CREATE TABLE IF NOT EXISTS Favoris (" +
                            "id SERIAL PRIMARY KEY, " +
                            "userid INT NOT NULL, " +
                            "spaceid INT NOT NULL); " +

                            "CREATE TABLE IF NOT EXISTS HateServices (" +
                            "id SERIAL PRIMARY KEY, " +
                            "userid INT NOT NULL, " +
                            "serviceid INT NOT NULL); " +

                            "CREATE TABLE IF NOT EXISTS EspaceServices (" +
                            "id SERIAL PRIMARY KEY, " +
                            "spaceId INT NOT NULL, " +
                            "serviceId INT NOT NULL, " +
                            "CONSTRAINT fk_space_service FOREIGN KEY (spaceId) REFERENCES Espace(id) ON DELETE CASCADE, " +
                            "CONSTRAINT fk_service FOREIGN KEY (serviceId) REFERENCES services(id) ON DELETE CASCADE);";

            // Execute the combined SQL query
            statement.execute(createAllTablesSQL);
            System.out.println("All tables created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void Main() {
        migrateAllTables();
    }
}
