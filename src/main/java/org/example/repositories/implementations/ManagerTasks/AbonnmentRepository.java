package org.example.repositories.implementations.ManagerTasks;

import org.example.Dao.Dao;

import java.util.HashMap;

public class AbonnmentRepository extends Dao {

    public int createAbonnment(String name, String description, String price, int spaceid) {
        try {
            HashMap<String, Object> data = new HashMap<>();
            data.put("name", name);
            data.put("spaceId", spaceid);
            data.put("description", description);
            data.put("price", price);

            return insertData("Abonnments", data);
        } catch (Exception e) {
            System.out.println("Error while creating abonnment: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public int deleteAbonnment(String name) {
        try {
            return deleteData("Abonnments", "name", name);
        } catch (Exception e) {
            System.out.println("Error while deleting abonnment: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public void GetManagerAbonments() {
        System.out.println(222);
        try {
            System.out.println(fetchData("Abonnments", null, null));
        } catch (Exception e) {
            System.out.println("Error while fetching abonnments: " + e.getMessage());
            e.printStackTrace(); 
        }
    }
}
