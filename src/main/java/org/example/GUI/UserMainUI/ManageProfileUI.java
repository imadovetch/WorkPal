package org.example.GUI.UserMainUI;

import org.example.repositories.implementations.UserRepositoryImp;

import java.util.Scanner;

public class ManageProfileUI {

    private static final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("Manage Profile");
            System.out.println("1. Update Personal Information");

            System.out.println("3. Back to Main Menu");

            int choice = UserMainUI.getChoice();

            switch (choice) {
                case 1:
                    updatePersonalInformation();
                    break;

                case 3:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void updatePersonalInformation() {
        System.out.println("Updating personal information...");
        // Logic to update address and phone number

        System.out.print("Enter new password: ");
        String phoneNumber = scanner.nextLine();
        new UserRepositoryImp().updateUserPass(phoneNumber);
        System.out.println("Personal information updated successfully.");
    }

    private void addProfilePhoto() {
        System.out.println("Adding/updating profile photo...");
        // Logic to upload or update profile photo
        System.out.print("Enter the file path of the photo: ");
        String filePath = scanner.nextLine();
        // Call to repository or service to upload the photo
        System.out.println("Profile photo updated successfully.");
    }
}
