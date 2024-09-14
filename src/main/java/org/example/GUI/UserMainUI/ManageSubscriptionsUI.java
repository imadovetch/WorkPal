package org.example.GUI.UserMainUI;

import org.example.repositories.implementations.ManagerTasks.ServiceRepository;
import org.example.repositories.implementations.UserTasks.FavorisRepository;
import org.example.services.implementations.Helpers;

import java.util.Scanner;

public class ManageSubscriptionsUI {

    private static final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("Manage Subscriptions");
            System.out.println("1. See service");
            System.out.println("2. See favorite");
            System.out.println("3. Cancel service");
            System.out.println("4. Back to Main Menu");

            int choice = UserMainUI.getChoice();

            switch (choice) {
                case 1:
                    chooseSubscriptionPlan();
                    break;
                case 2:
                    renewOrUpdateSubscription();
                    break;
                case 3:
                    cancelSubscription();
                    break;
                case 4:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void chooseSubscriptionPlan() {
        System.out.println("Choosing a Spaceid...");
        new Helpers().ShowData("Espace");
        int spaceid = getIntInput();
        new ServiceRepository().ShowspaceServices(spaceid);
        // Logic to choose and subscribe to a plan
    }

    private void renewOrUpdateSubscription() {
        System.out.println("show favoris...");
        new FavorisRepository().showFavoris();
        // Logic to renew or update the subscription
    }

    private void cancelSubscription() {
        System.out.println("cancel service by id...");
        int serviceid = getIntInput();
        new ServiceRepository().hateService(serviceid);
        // Logic to cancel a subscription
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
}
