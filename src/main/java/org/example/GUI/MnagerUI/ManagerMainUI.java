package org.example.GUI.MnagerUI;

import org.example.repositories.implementations.AdminTasks.TypesRepository;
import org.example.repositories.implementations.ManagerTasks.AbonnmentRepository;
import org.example.repositories.implementations.ManagerTasks.ServiceRepository;
import org.example.repositories.implementations.ManagerTasks.SpacesRepository;

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
                    manageSpaces();
                    break;
                case 2:
                    manageAbonnements();
                    break;
                case 3:
                    manageServices();
                    break;
                case 4:
                    manageReservations();
                    break;
                case 5:
                    System.out.println("Exiting Manager UI...");
                    return; // Properly exit
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageSpaces() {
        while (true) {
            System.out.println("Managing Spaces");
            System.out.println("1. Add Space");
            System.out.println("2. Delete Space");
            System.out.println("3. Modify Space");
            System.out.println("4. Back to Main Menu");

            int choice = getChoice();

            switch (choice) {
                case 1:
                    System.out.println("Adding a new space...");

                    System.out.print("Enter a description for the space: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter the name of the space: ");
                    String name = scanner.nextLine();
                    new TypesRepository().ShowTypes();
                    System.out.print("Enter the type of the space: ");
                    String type = scanner.nextLine();

                    int spaceCreation = new SpacesRepository().createspace(name, type, description);
                    if (spaceCreation > 0) {
                        System.out.println("Space created successfully.");
                    } else {
                        System.out.println("Failed to create space.");
                    }
                    break;
                case 2:

                    System.out.print("Enter the name of the space to delete: ");
                    new SpacesRepository().GetManagerSpaces();
                    name = scanner.nextLine();
                    spaceCreation = new SpacesRepository().deletespace(name);
                    if (spaceCreation > 0) {
                        System.out.println("Space deleted successfully.");
                    } else {
                        System.out.println("Failed to delete space.");
                    }
                    break;
                case 3:
                    System.out.println("Modifying a space...");

                        new SpacesRepository().GetManagerSpaces();


                    System.out.print("Enter the name of the space to modify: ");
                    name = scanner.nextLine();

                    System.out.print("Enter the new description: ");
                    description = scanner.nextLine();

                    spaceCreation = new SpacesRepository().modifyspace(name, description);
                    if (spaceCreation > 0) {
                        System.out.println("Space modified successfully.");
                    } else {
                        System.out.println("Failed to modify space.");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageAbonnements() {
        while (true) {
            System.out.println("Managing Abonnements");
            System.out.println("1. Add Abonnement");
            System.out.println("2. Delete Abonnement");
            System.out.println("3. Back to Main Menu");

            int choice = getChoice();

            switch (choice) {
                case 1:
                    System.out.println("Adding a new abonnement...");
                    System.out.print("Enter the name of the Space ID: ");
                    String spaceId = scanner.nextLine();

                    System.out.print("Enter the price of the Abonnement: ");
                    String price = scanner.nextLine();

                    System.out.print("Enter a description for the Abonnement: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter the name of the Abonnement: ");
                    String name = scanner.nextLine();

                    int result = new AbonnmentRepository().createAbonnment(name, price, description, Integer.parseInt(spaceId));
                    if (result > 0) {
                        System.out.println("Abonnement created successfully.");
                    } else {
                        System.out.println("Failed to create abonnement.");
                    }
                    break;
                case 2:
                    System.out.print("Enter the name of the Abonnement to delete: ");
                    new AbonnmentRepository().GetManagerAbonments();
                    String delName = scanner.nextLine();
                    result = new AbonnmentRepository().deleteAbonnment(delName);
                    if (result > 0) {
                        System.out.println("Abonnement deleted successfully.");
                    } else {
                        System.out.println("Failed to delete abonnement.");
                    }
                    break;
                case 3:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageServices() {
        while (true) {
            System.out.println("Managing Services");
            System.out.println("1. Add Service");
            System.out.println("2. Assign Service");
            System.out.println("3. Delete Service");
            System.out.println("4. Back to Main Menu");

            int choice = getChoice();

            switch (choice) {
                case 1:
                    System.out.println("Adding a new service...");
                    System.out.print("Enter the name of the service: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter a description for the service: ");
                    String description = scanner.nextLine();

                    int result = new ServiceRepository().createservice(name, description);
                    if (result > 0) {
                        System.out.println("Service created successfully.");
                    } else {
                        System.out.println("Failed to create service.");
                    }
                    break;
                case 2:
                    System.out.println("Assigning a service...");
                    System.out.println("Your Services...");
                    new ServiceRepository().GetManagerservices();
                    System.out.println("Your Spaces...");
                    new SpacesRepository().GetManagerSpaces();

                    System.out.println("Enterspaceid...");  //   validation later
                    int spaceId = scanner.nextInt();
                    System.out.println("Enterserviceid...");
                    int serviceId = scanner.nextInt();
                    result = new SpacesRepository().AssignService(spaceId, serviceId);

                    break;
                case 3:
                    System.out.println("Deleting a service...");
                    System.out.print("Enter the name of the service: ");
                    String delname = scanner.nextLine();

                    result = new ServiceRepository().deleteservice(delname);
                    break;
                case 4:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageReservations() {
        while (true) {
            System.out.println("Managing Reservations");
            System.out.println("1. Show Reservations");
            System.out.println("2. Manipulate Reservation");
            System.out.println("3. Back to Main Menu");

            int choice = getChoice();

            switch (choice) {
                case 1:
                    System.out.println("Showing reservations...");
                    break;
                case 2:
                    System.out.println("Manipulating a reservation...");
                    break;
                case 3:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int getChoice() {
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
