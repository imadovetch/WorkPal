package org.example.GUI.UserMainUI;

import org.example.repositories.implementations.ManagerTasks.SpacesRepository;
import org.example.repositories.implementations.UserTasks.FavorisRepository;
import org.example.repositories.implementations.UserTasks.ReservationsRepository;
import org.example.services.implementations.Helpers;

import java.util.Scanner;

public class ReserveSpacesUI {

    private static final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\n=== Reserve Spaces ===");
            System.out.println("1. Search Available Spaces");
            System.out.println("2. Reserve a Space");
            System.out.println("3. Save Favorite Spaces");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = getChoice();

            switch (choice) {
                case 1:
                    searchAvailableSpaces();
                    break;
                case 2:
                    reserveSpace();
                    break;

                case 3:
                    saveFavoriteSpaces();
                    break;

                case 4:
                    return; // Exit to Main Menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void searchAvailableSpaces() {
        System.out.println("\nSearch by:");
        System.out.println("1. Date");
        System.out.println("2. Name");
        System.out.print("Select an option: ");

        int choice = getChoice();

        switch (choice) {
            case 1:
                System.out.print("Enter the date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                new Helpers().searchByAll("Espace", "date", date);
                break;
            case 2:
                System.out.print("Enter the name of the space: ");
                String name = scanner.nextLine();
                new Helpers().searchByAll("Espace", "name", name);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                searchAvailableSpaces();
        }
    }

    private void reserveSpace() {
        System.out.print("\nEnter the ID of the space you want to reserve: ");
        new Helpers().ShowData("Espace");
        int spaceId = getIntInput();

        System.out.println("\nReserve by:");
        System.out.println("1. Single Day");
        System.out.println("2. Subscription Plan");
        System.out.print("Select an option: ");

        int choice = getChoice();

        switch (choice) {
            case 1:

                new ReservationsRepository().addReservation(spaceId, 0);
                System.out.println("Space reserved for a single day.");
                break;
            case 2:
                new SpacesRepository().fetchData("Abonnments", "spaceid", spaceId);
                System.out.print("Enter the subscription plan ID you want to choose: ");
                int planId = getIntInput();
                new ReservationsRepository().addReservation(spaceId, planId);
                System.out.println("Space reserved with subscription plan.");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                reserveSpace(); // Retry if invalid input
        }
    }

    private void viewReservedSpaceDetails() {
        System.out.print("\nEnter the reservation name to view details: ");
        String reservationName = scanner.nextLine();
        System.out.println("Viewing details for reservation: " + reservationName);
    }
    private void cancelRev(int id) {
        System.out.print("\nEnter the reservation name to view details: ");
        String reservationName = scanner.nextLine();
        System.out.println("Viewing details for reservation: " + reservationName);
    }

    private void saveFavoriteSpaces() {
        new Helpers().ShowData("Espace");
        System.out.print("\nEnter the id of the space to save as a favorite: ");
        int spaceName = getIntInput();
        new FavorisRepository().AddtoFavoris(spaceName);
        System.out.println("Saved " + spaceName + " as a favorite space.");
    }


    private int getIntInput() {
        while (true) {
            try {
                System.out.print("Enter a number: ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private int getChoice() {
        return getIntInput(); // Reuse the getIntInput method for menu choices
    }
}
