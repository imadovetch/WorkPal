package org.example.GUI.AdminUI;

import org.example.repositories.implementations.AdminTasks.PaymentRepository;
import org.example.repositories.implementations.AdminTasks.TypesRepository;

import java.util.Scanner;

public class AdminMainUI {

    private static final Scanner scanner = new Scanner(System.in);

    public static void Display() {
        System.out.println("Welcome to the Admin UI");
        System.out.println("Please select an option:");
        System.out.println("1. Manage Traffic");
        System.out.println("2. Manage Payment Types");
        System.out.println("3. Manage Spaces Types");
        System.out.println("4. Manage Mailing Settings");
        System.out.println("5. Exit");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                manageTraffic();
                break;
            case "2":
                managePaymentTypes();
                break;
            case "3":
                manageSpacesTypes();
                break;
            case "4":
                manageMailingSettings();
                break;
            case "5":
                System.out.println("Exiting Admin UI...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                Display();
        }
    }

    private static void manageTraffic() {
        System.out.println("Managing traffic...");
    }

    private static void managePaymentTypes() {
        System.out.println("Managing payment types...");
        System.out.println("1. Add Payment Type");
        System.out.println("2. Delete Payment Type");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                addPaymentType();
                break;
            case "2":
                deletePaymentType();
                break;
            default:
                System.out.println("Invalid option. Returning to Admin UI.");
                Display();
        }
    }

    private static void addPaymentType() {
        System.out.println("Enter the new payment type:");
        String paymentType = scanner.next();
        int paymentMethod = new PaymentRepository().createPaymentMethod(paymentType);
    }

    private static void deletePaymentType() {
        System.out.println("Enter the payment type to delete:");
        String paymentType =scanner.next();
        int paymentMethod = new PaymentRepository().deletePaymentMethod(paymentType);
    }



    private static void manageSpacesTypes() {
        System.out.println("Managing space types...");
        System.out.println("1. Add Space Type");
        System.out.println("2. Delete Space Type");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addSpaceType();
                break;
            case 2:
                deleteSpaceType();
                break;
            default:
                System.out.println("Invalid option. Returning to Admin UI.");
                Display();
        }
    }

    private static void addSpaceType() {
        System.out.println("Enter the new space type:");
        String spaceType = scanner.next();
        int paymentMethod = new TypesRepository().createType(spaceType);
    }

    private static void deleteSpaceType() {
        System.out.println("Enter the space type to delete:");
        String spaceType = scanner.next();
        int paymentMethod = new TypesRepository().deleteType(spaceType);
    }





    private static void manageMailingSettings() {
        System.out.println("Managing mailing settings...");
    }
}
