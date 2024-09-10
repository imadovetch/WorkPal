package org.example.Dao;

import org.example.config.PostgreSQLConnection;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Dao {

    private final Connection connection;

    public Dao() {
        this.connection = PostgreSQLConnection.getInstance().getConnection();
    }

    // Method to fetch data from the database
    public HashMap<String, Object> fetchData(String table, String conditionColumn, String conditionValue) {
        HashMap<String, Object> resultData = new HashMap<>();
        String query;

        // Prepare the SQL query based on whether a condition is present or not
        if (conditionValue == null) {
            query = "SELECT * FROM " + table;
        } else {
            query = "SELECT * FROM " + table + " WHERE " + conditionColumn + " = ?";
        }

        // Execute the query
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            if (conditionValue != null) {
                stmt.setString(1, conditionValue);
            }

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                HashMap<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValueResult = rs.getObject(i);
                    rowData.put(columnName, columnValueResult);
                }
                resultData.putAll(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultData;
    }


    public int insertData(String table, HashMap<String, Object> data) {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        data.forEach((key, value) -> {
            columns.append(key).append(",");
            values.append("?,");
        });

        String query = "INSERT INTO " + table + " (" + columns.substring(0, columns.length() - 1) + ") VALUES (" + values.substring(0, values.length() - 1) + ")";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            int index = 1;
            for (Object value : data.values()) {
                stmt.setObject(index++, value);
            }
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int deleteData(String table, String conditionColumn, String conditionValue) {
        String query = "DELETE FROM " + table + " WHERE " + conditionColumn + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, conditionValue);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int updateData(String table, HashMap<String, Object> data, String conditionColumn, String conditionValue) {
        StringBuilder setClause = new StringBuilder();

        data.forEach((key, value) -> setClause.append(key).append(" = ?,"));

        String query = "UPDATE " + table + " SET " + setClause.substring(0, setClause.length() - 1) + " WHERE " + conditionColumn + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            int index = 1;
            for (Object value : data.values()) {
                stmt.setObject(index++, value);
            }
            // Set the condition value
            stmt.setString(index, conditionValue);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
