package org.example.repositories.implementations.ManagerTasks;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.example.Dao.Dao;
import org.example.entities.Types;
import org.example.entities.User;
import org.example.repositories.implementations.AdminTasks.TypesRepository;
import org.example.services.implementations.Helpers;

public class SpacesRepository extends Dao {

    private Helpers helper = new Helpers();

    // Create a new space
    public int createSpace(String name, String typeName, String description) {
        TypesRepository typesRepo = new TypesRepository();
        typesRepo.showTypes();

        // Retrieve type ID based on the type name
        Integer typeId = typesRepo.getTypeIdByName(typeName);

        if (typeId == null) {
            System.out.println("Type does not exist. Please provide a valid Type.");
            return -1;
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("typeid", typeId); // Use type ID instead of type name
        data.put("creatorid", 2);
        System.out.println();
        data.put("description", description);

        try {
            return insertData("Espace", data);
        } catch (Exception e) {
            System.out.println("Error creating space: " + e.getMessage());
            return -1;
        }
    }

    // Get all spaces created by a manager (current user)
    public void getManagerSpaces() {
        try {
            List<HashMap<String, Object>> spaces = fetchData("espace", "creatorid", String.valueOf(User.Main.getId()));
            if (spaces.isEmpty()) {
                System.out.println("No spaces found for the manager.");
            } else {
                spaces.forEach(space -> System.out.println(space));
            }
        } catch (Exception e) {
            System.out.println("Error fetching manager spaces: " + e.getMessage());
        }
    }

    // Delete a space
    public int deleteSpace(String name) {
        try {
            Optional<String> validSpace = helper.checkValidity("Espace", name);
            if (validSpace.isPresent()) {
                return deleteData("Espace", "name", name);
            } else {
                System.out.println("Error: The space does not belong to the user with ID " + User.Main.getId() + " or does not exist.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error deleting space: " + e.getMessage());
            return -1;
        }
    }

    // Modify a space
    public int modifySpace(String name, String description) {
        try {
            Optional<String> validSpace = helper.checkValidity("Espace", name);
            if (validSpace.isPresent()) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("description", description);
                return updateData("Espace", data, "name", name);
            } else {
                System.out.println("Error: The space does not belong to the user with ID " + User.Main.getId() + " or does not exist.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error modifying space: " + e.getMessage());
            return -1;
        }
    }

    // Assign a service to a space
    public int assignService(int spaceId, int serviceId) {
        // Verify that the spaceId exists
        try {
            System.out.println("wseel1");

            List<HashMap<String, Object>> spaces = fetchData("Espace", "id", spaceId);
            List<HashMap<String, Object>> service = fetchData("services", "id", serviceId);

            if (spaces.isEmpty() || service.isEmpty()) {
                System.out.println("Error: The space or service with ID " + spaceId + " does not exist.");
                return -1;
            }
            System.out.println("wseel2");

            HashMap<String, Object> data = new HashMap<>();
            data.put("spaceid", spaceId);
            data.put("serviceid", serviceId);

            return insertData("EspaceServices", data);
        } catch (Exception e) {
            System.out.println("Error assigning service: " + e.getMessage());
            return -1;
        }
    }

    public static void runTest() {
        SpacesRepository spacesRepo = new SpacesRepository();
//
//        // Test creating a space
////        System.out.println("Creating space:");
//    int createResult = spacesRepo.createSpace("Cozy Corner", "Meeting Room", "A cozy and comfortable workspace.");
////        System.out.println("Create result: " + createResult);
//
//        // Test fetching spaces
//        System.out.println("\nFetching manager spaces:");
//        spacesRepo.getManagerSpaces();
//
//        // Test modifying a space
//        System.out.println("\nModifying space:");
//        int modifyResult = spacesRepo.modifySpace("Cozy Corner", "Updated description for cozy workspace.");
//        System.out.println("Modify result: " + modifyResult);
//
//        // Test deleting a space
//        System.out.println("\nDeleting space:");
//        int deleteResult = spacesRepo.deleteSpace("Cozy Corner");
//        System.out.println("Delete result: " + deleteResult);

        // Test assigning a service to a space
        System.out.println("\nAssigning service to space:");
        int assignServiceResult = spacesRepo.assignService(8, 3); // Example spaceId and serviceId
        System.out.println("Assign service result: " + assignServiceResult);
    }

    public static void main(String[] args) {
        runTest();
    }
}
