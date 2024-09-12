package org.example.repositories.implementations.AdminTasks;

import org.example.Dao.Dao;
import org.example.entities.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TypesRepository extends Dao {

    public int createType(String name) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);

        return insertData("Espacetypes", data);
    }

    public int deleteType(String name) {
        return deleteData("Espacetypes", "name", name);
    }
    public void ShowTypes() {

        List<HashMap<String, Object>> results = (List<HashMap<String, Object>>) fetchData("Espacetypes",null,null);;

        if (Types.Types == null) {
            Types.Types = new ArrayList<>();
        } else {
            Types.Types.clear();
        }

        for (HashMap<String, Object> row : results) {
            Types type = new Types();
            type.setId((Integer) row.get("id"));
            type.setName((String) row.get("name"));
            System.out.println(row.get("name"));
            Types.Types.add(type);
        }

    }



}
