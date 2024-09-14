package org.example.GUI.ManagerUI;

import java.util.Scanner;

public class ManageReservationsUI {

    private static final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("Managing Reservations");
            System.out.println("1. Show Reservations");
            System.out.println("2. Manipulate Reservation");
            System.out.println("3. Back to Main Menu");

            int choice = ManagerMainUI.getChoice();

            switch (choice) {
                case 1:
                    showReservations();
                    break;
                case 2:
                    manipulateReservation();
                    break;
                case 3:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showReservations() {
        System.out.println("Showing reservations...");
    }

    private void manipulateReservation() {
        System.out.println("Manipulating a reservation...");
    }
}
