package org.example.GUI.ManagerUI;

import org.example.repositories.implementations.ManagerTasks.SpacesRepository;
import org.example.repositories.implementations.AdminTasks.TypesRepository;

import java.util.Scanner;

public class ManageSpacesUI {

    private static final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("Managing Spaces");
            System.out.println("1. Add Space");
            System.out.println("2. Delete Space");
            System.out.println("3. Modify Space");
            System.out.println("4. Back to Main Menu");

            int choice = ManagerMainUI.getChoice();

            switch (choice) {
                case 1:
                    addSpace();
                    break;
                case 2:
                    deleteSpace();
                    break;
                case 3:
                    modifySpace();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addSpace() {
        System.out.println("Adding a new space...");

        System.out.print("Enter a description for the space: ");
        String description = scanner.nextLine();

        System.out.print("Enter the name of the space: ");
        String name = scanner.nextLine();

        new TypesRepository().showTypes();
        System.out.print("Enter the type of the space: ");
        String type = scanner.nextLine();

        int result = new SpacesRepository().createSpace(name, type, description);
        if (result > 0) {
            System.out.println("Space created successfully.");
        } else {
            System.out.println("Failed to create space.");
        }
    }

    private void deleteSpace() {
        System.out.print("Enter the name of the space to delete: ");
        new SpacesRepository().getManagerSpaces();
        String name = scanner.nextLine();
        int result = new SpacesRepository().deleteSpace(name);
        if (result > 0) {
            System.out.println("Space deleted successfully.");
        } else {
            System.out.println("Failed to delete space.");
        }
    }

    private void modifySpace() {
        System.out.println("Modifying a space...");

        new SpacesRepository().getManagerSpaces();

        System.out.print("Enter the name of the space to modify: ");
        String name = scanner.nextLine();

        System.out.print("Enter the new description: ");
        String description = scanner.nextLine();

        int result = new SpacesRepository().modifySpace(name, description);
        if (result > 0) {
            System.out.println("Space modified successfully.");
        } else {
            System.out.println("Failed to modify space.");
        }
    }
}
