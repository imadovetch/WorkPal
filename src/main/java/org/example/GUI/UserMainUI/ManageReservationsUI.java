package org.example.GUI.UserMainUI;

import org.example.repositories.implementations.UserTasks.ReservationsRepository;

import java.util.Scanner;

public class ManageReservationsUI {

    private static final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("Manage Reservations");
            System.out.println("1. View Reservation History");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Back to Main Menu");

            int choice = UserMainUI.getChoice();

            switch (choice) {
                case 1:
                    viewReservationHistory();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewReservationHistory() {
        System.out.println("Viewing reservation history...");
        new ReservationsRepository().ShowReservations();
        // Logic to view past and future reservations
    }

    private void cancelReservation() {
        System.out.println("Canceling a reservation...");
        // Logic to cancel a reservation
        System.out.print("Enter reservation ID to cancel: ");
        String reservationId = scanner.nextLine();
        // Call to repository or service to cancel the reservation
        System.out.println("Reservation canceled successfully.");
    }
}
