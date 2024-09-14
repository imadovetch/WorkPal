package org.example.repositories.implementations.ManagerTasks;

import org.example.Dao.Dao;
import org.example.entities.User;

import java.util.HashMap;
import java.util.List;

public class ServiceRepository extends Dao {

    private static final int CURRENT_USER_ID = User.Main.getId(); // Replace with dynamic user ID retrieval if necessary

    public int createService(String name, String description) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("creatorid", CURRENT_USER_ID);
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
            if (isServiceExists(name)) {
                return deleteData("services", "name", name);
            } else {
                System.out.println("Error: The service with name " + name + " does not exist.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error deleting service: " + e.getMessage());
            return -1;
        }
    }

    public void getManagerServices() {
        try {
            List<HashMap<String, Object>> services = fetchData("services", "creatorid", String.valueOf(CURRENT_USER_ID));
            if (services.isEmpty()) {
                System.out.println("No services found for the manager.");
            } else {
                services.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching manager services: " + e.getMessage());
        }
    }

    private boolean isServiceExists(String name) {
        try {
            List<HashMap<String, Object>> services = fetchData("services", "name", name);
            return !services.isEmpty();
        } catch (Exception e) {
            System.out.println("Error checking service existence: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        ServiceRepository serviceRepo = new ServiceRepository();

        System.out.println("Creating service:");
        int createResult = serviceRepo.createService("Premium Service", "A high-quality premium service.");
        System.out.println("Create result: " + createResult);

        System.out.println("Fetching manager services:");
        serviceRepo.getManagerServices();

//        System.out.println("Deleting service:");
//        int deleteResult = serviceRepo.deleteService("Premium Service");
//        System.out.println("Delete result: " + deleteResult);
//
//        System.out.println("Fetching services after deletion:");
//        serviceRepo.getManagerServices();
    }
}
