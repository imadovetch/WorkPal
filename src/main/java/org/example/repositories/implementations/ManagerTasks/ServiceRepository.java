package org.example.repositories.implementations.ManagerTasks;

import org.example.Dao.Dao;
import org.example.entities.User;

import java.util.HashMap;

public class ServiceRepository extends Dao {

    public int createService(String name, String description) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("creatorid", User.Main.getId());
        data.put("description", description);

        try {
            return insertData("services", data);
        } catch (Exception e) {
            System.out.println("Error creating service: " + e.getMessage());
            return -1;
        }
    }

    public int deleteService(String name) {
        try {
            return deleteData("services", "name", name);
        } catch (Exception e) {
            System.out.println("Error deleting service: " + e.getMessage());
            return -1;
        }
    }

    public void getManagerServices() {
        try {
            System.out.println(fetchData("services", "creatorid", "1"));
        } catch (Exception e) {
            System.out.println("Error fetching manager services: " + e.getMessage());
        }
    }

    public static void main(String[] args){
        ServiceRepository serviceRepo = new ServiceRepository();

        // Test creating a service
        System.out.println("Creating service:");
        int createResult = serviceRepo.createService("Premium Service", "A high-quality premium service.");
        System.out.println("Create result: " + createResult);

        // Test fetching services
        System.out.println("Fetching manager services:");
        serviceRepo.getManagerServices();

        // Test deleting a service


        // Verify deletion
        System.out.println("Fetching services after deletion:");
        serviceRepo.getManagerServices();
    }

}
