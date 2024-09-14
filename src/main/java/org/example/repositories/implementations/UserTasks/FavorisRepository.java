package org.example.repositories.implementations.UserTasks;

import org.example.Dao.Dao;
import org.example.entities.User;

import java.util.HashMap;
import java.util.List;

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
    public  void showFavoris(){
        try {
            List<HashMap<String, Object>> reservations = fetchData("Favoris", "userid", User.Main.getId());

            if (reservations.isEmpty()) {
                System.out.println("No results found.");
                return;
            }

            System.out.println("Reservation Details:");

            for (HashMap<String, Object> reservation : reservations) {

                int serviceid = (int) reservation.get("spaceid");


                List<HashMap<String, Object>> spaceDetails = fetchData("Espace", "id", serviceid);

                if (!spaceDetails.isEmpty()) {
                    HashMap<String, Object> space = spaceDetails.get(0);


                    System.out.println("Space ID: " + reservation.get("id"));
                    System.out.println("decription on: " + space.get("description"));
                    System.out.println("Space Name: " + space.get("name"));

                    System.out.println("----------------------------------");
                } else {
                    System.out.println("Space details not found for space ID: " + serviceid);
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching manager spaces: " + e.getMessage());
        }
    }
}
