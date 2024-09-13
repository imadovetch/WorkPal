package org.example.services.implementations;

import org.example.Dao.Dao;
import org.example.entities.User;

import java.util.HashMap;
import java.util.List;
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
}
