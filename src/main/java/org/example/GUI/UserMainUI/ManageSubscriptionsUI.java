package org.example.GUI.UserMainUI;

import java.util.Scanner;

public class ManageSubscriptionsUI {

    private static final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("Manage Subscriptions");
            System.out.println("1. Choose/Subscribe to a Plan");
            System.out.println("2. Renew or Update Subscription");
            System.out.println("3. Cancel Subscription");
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
        System.out.println("Choosing a subscription plan...");
        // Logic to choose and subscribe to a plan
    }

    private void renewOrUpdateSubscription() {
        System.out.println("Renewing or updating subscription...");
        // Logic to renew or update the subscription
    }

    private void cancelSubscription() {
        System.out.println("Canceling subscription...");
        // Logic to cancel a subscription
    }
}
