package org.example.repositories.implementations.UserTasks;

import org.example.Dao.Dao;
import org.example.entities.User;

import java.util.HashMap;

public class FavorisRepository extends Dao {
    private static final String TABLE_NAME = "Favoris";


    public  int AddtoFavoris(int id){
        try{
            HashMap<String, Object> FavorisData = new HashMap<>();
            FavorisData.put("userid", User.Main.getId());
            FavorisData.put("spaceid", id);
            return insertData(TABLE_NAME, FavorisData);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return -1;
        }




    }
}
