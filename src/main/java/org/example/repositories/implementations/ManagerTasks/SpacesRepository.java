package org.example.repositories.implementations.ManagerTasks;

import java.util.HashMap;
import org.example.Dao.Dao;
import org.example.entities.Types;
import org.example.entities.User;


public class SpacesRepository extends Dao {
    public int createspace(String name,String Type,String description) {
        boolean typeExists = Types.Types.stream()
                .anyMatch(t -> t.getName().equalsIgnoreCase(Type));

        if (!typeExists) {
            System.out.println("Type does not exist. Please provide a valid Type.");
            return -1;
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("Type", Type);
        data.put("creatorid", User.Main.getId());
        data.put("description", description);
        try {
            return insertData("Espace", data);
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        return 0;

    }
    public void GetManagerSpaces(){
        System.out.println(222);
        try {
        System.out.println(fetchData("Espace","creatorid", "1"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public int deletespace(String name) {
        return deleteData("Espace", "name", name);
    }
    public int modifyspace(String name, String description){
        HashMap<String, Object> data = new HashMap<>();
        data.put("description", description);
        return updateData("Espace",data,"name",name) ;
    }

    public int AssignService(int x,int y){
        HashMap<String, Object> data = new HashMap<>();
        data.put("spaceId", x);
        data.put("serviceid", y);
        return insertData("EspaceServices",data) ;
    }
}
