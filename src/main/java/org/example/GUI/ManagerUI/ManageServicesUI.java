package org.example.GUI.ManagerUI;

import org.example.repositories.implementations.ManagerTasks.ServiceRepository;
import org.example.repositories.implementations.ManagerTasks.SpacesRepository;

import java.util.Scanner;

public class ManageServicesUI {

    private static final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("Managing Services");
            System.out.println("1. Add Service");
            System.out.println("2. Assign Service");
            System.out.println("3. Delete Service");
            System.out.println("4. Back to Main Menu");

            int choice = ManagerMainUI.getChoice();

            switch (choice) {
                case 1:
                    addService();
                    break;
                case 2:
                    assignService();
                    break;
                case 3:
                    deleteService();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addService() {
        System.out.println("Adding a new service...");
        System.out.print("Enter the name of the service: ");
        String name = scanner.nextLine();

        System.out.print("Enter a description for the service: ");
        String description = scanner.nextLine();

        int result = new ServiceRepository().createService(name, description);
        if (result > 0) {
            System.out.println("Service created successfully.");
        } else {
            System.out.println("Failed to create service.");
        }
    }

    private void assignService() {
        System.out.println("Assigning a service...");
        System.out.println("Your Services...");
        new ServiceRepository().getManagerServices();
        System.out.println("Your Spaces...");
        new SpacesRepository().getManagerSpaces();

        System.out.print("Enter the Space ID: ");
        int spaceId = ManagerMainUI.getChoice();

        System.out.print("Enter the Service ID: ");
        int serviceId = ManagerMainUI.getChoice();

        int result = new SpacesRepository().assignService(spaceId, serviceId);
        if (result > 0) {
            System.out.println("Service assigned successfully.");
        } else {
            System.out.println("Failed to assign service.");
        }
    }

    private void deleteService() {
        System.out.print("Enter the name of the service: ");
        String name = scanner.nextLine();

        int result = new ServiceRepository().deleteService(name);
        if (result > 0) {
            System.out.println("Service deleted successfully.");
        } else {
            System.out.println("Failed to delete service.");
        }
    }
}
