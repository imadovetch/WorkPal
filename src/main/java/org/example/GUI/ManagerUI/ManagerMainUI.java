package org.example.GUI.ManagerUI;

import java.util.Scanner;

public class ManagerMainUI {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display() {
        while (true) {
            System.out.println("Welcome to the Manager UI");
            System.out.println("Please select an option:");
            System.out.println("1. Manage Spaces");
            System.out.println("2. Manage Abonnements");
            System.out.println("3. Manage Services");
            System.out.println("4. Manage Reservations");
            System.out.println("5. Exit");

            int choice = getChoice();

            switch (choice) {
                case 1:
                    new ManageSpacesUI().display();
                    break;
                case 2:
                    new ManageAbonnementsUI().display();
                    break;
                case 3:
                    new ManageServicesUI().display();
                    break;
                case 4:
                    new ManageReservationsUI().display();
                    break;
                case 5:
                    System.out.println("Exiting Manager UI...");
                    return; // Properly exit
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static int getChoice() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }
}
