package org.example.repositories.implementations.ManagerTasks;

import org.example.Dao.Dao;
import org.example.services.implementations.Helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class AbonnmentRepository extends Dao {

    private Helpers helper = new Helpers();

    public int createAbonnment(String name, String description, String price, int spaceId) {

        Optional<String> validSpace = helper.checkValidity("Espace", spaceId);
        if (!validSpace.isPresent()) {
            System.out.println("Error: The space with ID " + spaceId + " does not exist.");
            return -1;
        }

        try {
            HashMap<String, Object> data = new HashMap<>();
            data.put("name", name);
            data.put("spaceid", spaceId); // Ensure field matches the new schema
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

    public void getManagerAbonnments() {
        try {
            List<HashMap<String, Object>> abonnments = fetchData("Abonnments", null, null);
            if (abonnments.isEmpty()) {
                System.out.println("No abonnments found.");
            } else {
                abonnments.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error while fetching abonnments: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void runTests() {
        AbonnmentRepository abonnmentRepo = new AbonnmentRepository();

        // Test creating an abonnment
        System.out.println("Creating abonnment:");
        int createResult = abonnmentRepo.createAbonnment("Gold Membership", "Premium access to all facilities.", "100.00", 8);
        System.out.println("Create result: " + createResult);

        // Test fetching abonnments
        System.out.println("Fetching abonnments:");
        abonnmentRepo.getManagerAbonnments();

        // Test deleting an abonnment
        System.out.println("Deleting abonnment:");
        int deleteResult = abonnmentRepo.deleteAbonnment("Gold Membership");
        System.out.println("Delete result: " + deleteResult);

        // Verify deletion
        System.out.println("Fetching abonnments after deletion:");
        abonnmentRepo.getManagerAbonnments();
    }

    public static void main(String[] args) {
        runTests();
    }
}
