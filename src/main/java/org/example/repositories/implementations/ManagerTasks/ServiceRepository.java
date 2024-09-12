package org.example.repositories.implementations.ManagerTasks;

import org.example.Dao.Dao;

import java.util.HashMap;

public class ServiceRepository extends Dao {
    public int createservice(String name,String description) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);

        data.put("creatorid", 1);
        data.put("description", description);

        return insertData("services", data);
    }

    public int deleteservice(String name) {
        return deleteData("services", "name", name);
    }

    public void GetManagerservices(){
        System.out.println(222);
        try {

            System.out.println(fetchData("services","creatorid", "1"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
