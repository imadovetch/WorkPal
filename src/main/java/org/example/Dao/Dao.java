package org.example.Dao;

import org.example.config.PostgreSQLConnection;
import org.example.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dao {

    private final Connection connection;

    public Dao() {
        this.connection = PostgreSQLConnection.getInstance().getConnection();
    }

    // Method to fetch data from the database
    public List<HashMap<String, Object>> fetchData(String table, String conditionColumn, String conditionValue) {
        List<HashMap<String, Object>> resultDataList = new ArrayList<>();
        String query;

        if (conditionValue == null) {
            query = "SELECT * FROM " + table;
        } else {
            query = "SELECT * FROM " + table + " WHERE " + conditionColumn + " = ?";
        }

        // Execute the query
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            if (conditionValue != null) {
                if (!conditionColumn.contains("creatorid")) {
                    stmt.setString(1, conditionValue);
                } else {
                    stmt.setInt(1, User.Main.getId());
                }
            }

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Loop through all the result set rows
            while (rs.next()) {
                HashMap<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValueResult = rs.getObject(i);
                    rowData.put(columnName, columnValueResult);
                }
                resultDataList.add(rowData); // Add each row to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultDataList;
    }

    public List<HashMap<String, Object>> fetchData(String table, String conditionColumn, int conditionValue) {
        List<HashMap<String, Object>> resultDataList = new ArrayList<>();
        String query;


            query = "SELECT * FROM " + table + " WHERE " + conditionColumn + " = ?";


        // Execute the query
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, conditionValue);

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Loop through all the result set rows
            while (rs.next()) {
                HashMap<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValueResult = rs.getObject(i);
                    rowData.put(columnName, columnValueResult);
                }
                resultDataList.add(rowData); // Add each row to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultDataList;
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
    public int deleteData(String table, String conditionColumn, int conditionValue) {
        String query = "DELETE FROM " + table + " WHERE " + conditionColumn + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, conditionValue);
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
    public int updateData(String table, HashMap<String, Object> data, String conditionColumn, int conditionValue) {
        StringBuilder setClause = new StringBuilder();

        data.forEach((key, value) -> setClause.append(key).append(" = ?,"));

        String query = "UPDATE " + table + " SET " + setClause.substring(0, setClause.length() - 1) + " WHERE " + conditionColumn + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            int index = 1;
            for (Object value : data.values()) {
                stmt.setObject(index++, value);
            }
            // Set the condition value
            stmt.setInt(index, conditionValue);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // custom
    public List<HashMap<String, Object>> fetchSubscriptionsBySpaceName(String spaceName) {
        List<HashMap<String, Object>> resultDataList = new ArrayList<>();
        String query = "SELECT a.* " +
                "FROM Abonnements a " +
                "JOIN Espaces e ON a.space_id = e.id " +
                "WHERE e.name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, spaceName);

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
                resultDataList.add(rowData); // Add each row to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultDataList;
    }
}
