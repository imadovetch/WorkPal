package org.example.GUI.UserMainUI;

import java.util.Scanner;

public class UserMainUI {

    private static final Scanner scanner = new Scanner(System.in);

    public static void display() {
        while (true) {
            System.out.println("Welcome to the User Dashboard");
            System.out.println("Please select an option:");
            System.out.println("1. Manage Profile");
            System.out.println("2. Reserve Spaces");
            System.out.println("3. View and Manage Reservations");
            System.out.println("4. Manage Subscriptions");
            System.out.println("5. Exit");

            int choice = getChoice();

            switch (choice) {
                case 1:
                    new ManageProfileUI().display();
                    break;
                case 2:
                    new ReserveSpacesUI().display();
                    break;
                case 3:
                    new ManageReservationsUI().display();
                    break;
                case 4:
                    new ManageSubscriptionsUI().display();
                    break;
                case 5:
                    System.out.println("Exiting User Dashboard...");
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
