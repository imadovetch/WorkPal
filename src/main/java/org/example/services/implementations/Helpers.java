package org.example.services.implementations;

import org.example.Dao.Dao;
import org.example.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Helpers extends Dao {

    public Optional<String> findByName(String table,String name) {
        List<HashMap<String, Object>> result = fetchData(table, "name", name);

        if (!result.isEmpty()) {
            for (HashMap<String, Object> map : result) {
                if (map.containsKey("name")) {
                    return Optional.of(map.get("name").toString());
                }
            }
        }

        return Optional.empty();
    }
    public  Optional<String> checkValidity(String table, String name) {
        List<HashMap<String, Object>> result = fetchData(table, "name", name);

        if (!result.isEmpty()) {
            for (HashMap<String, Object> map : result) {
                if (map.containsKey("creatorid") && map.get("creatorid").equals(User.Main.getId())) {
                    return Optional.ofNullable(map.get("name")).map(Object::toString);
                }
            }
        }

        return Optional.empty();
    }
    public  Optional<String> checkValidity(String table, int id) {
        List<HashMap<String, Object>> result = fetchData(table, "id", id);

        if (!result.isEmpty()) {
            for (HashMap<String, Object> map : result) {
                if (map.containsKey("creatorid") && map.get("creatorid").equals(User.Main.getId())) {
                    return Optional.ofNullable(map.get("name")).map(Object::toString);
                }
            }
        }

        return Optional.empty();
    }
    public  Optional<String> searchByAll(String table,String Key,String value) {
        List<HashMap<String, Object>> result = fetchData(table, Key, value);

        if (result.isEmpty()) {
            System.out.println("No results found.");
            return Optional.empty();
        }


        System.out.println("Search Results:");
        for (HashMap<String, Object> row : result) {
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println();
        }


        return Optional.of("Results displayed above.");
    }
    public  Optional<String> ShowData(String table) {
        List<HashMap<String, Object>> result = fetchData(table, null, null);

        if (result.isEmpty()) {
            System.out.println("No results found.");
            return Optional.empty();
        }


        System.out.println("Search Results:");
        for (HashMap<String, Object> row : result) {
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println();
        }


        return Optional.of("Results displayed above.");
    }

}
