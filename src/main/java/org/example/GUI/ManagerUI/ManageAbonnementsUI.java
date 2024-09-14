package org.example.GUI.ManagerUI;

import org.example.repositories.implementations.ManagerTasks.AbonnmentRepository;

import java.util.Scanner;

public class ManageAbonnementsUI {

    private static final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("Managing Abonnements");
            System.out.println("1. Add Abonnement");
            System.out.println("2. Delete Abonnement");
            System.out.println("3. Back to Main Menu");

            int choice = ManagerMainUI.getChoice();

            switch (choice) {
                case 1:
                    addAbonnement();
                    break;
                case 2:
                    deleteAbonnement();
                    break;
                case 3:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addAbonnement() {
        System.out.println("Adding a new abonnement...");
        System.out.print("Enter the Space ID: ");
        int spaceId = getIntInput();

        System.out.print("Enter the price of the Abonnement: ");
        String price = scanner.nextLine();

        if (!isValidPrice(price)) {
            System.out.println("Invalid price format. Please enter a valid price.");
            return;
        }

        System.out.print("Enter a description for the Abonnement: ");
        String description = scanner.nextLine();

        System.out.print("Enter the name of the Abonnement: ");
        String name = scanner.nextLine();

        int result = new AbonnmentRepository().createAbonnment(name, description, price, spaceId);
        if (result > 0) {
            System.out.println("Abonnement created successfully.");
        } else {
            System.out.println("Failed to create abonnement.");
        }
    }

    private void deleteAbonnement() {
        System.out.print("Enter the name of the Abonnement to delete: ");
        new AbonnmentRepository().getManagerAbonnments();
        String name = scanner.nextLine();
        int result = new AbonnmentRepository().deleteAbonnment(name);
        if (result > 0) {
            System.out.println("Abonnement deleted successfully.");
        } else {
            System.out.println("Failed to delete abonnement.");
        }
    }

    private int getIntInput() {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return input;
    }

    private boolean isValidPrice(String price) {
        return price.matches("^\\d+(\\.\\d{2})?$"); // Simple regex for validating price
    }
}
