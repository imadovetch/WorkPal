package org.example.GUI;

import org.example.repositories.implementations.UserRepositoryImp;

import java.util.Scanner;

public class MainGUI {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome! Please select an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forgot Password");
            System.out.println("0. Exit");

            // Get user input
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // Process based on user choice
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    forgotPassword();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 0);
    }

    private void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- User Registration ---");
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        new UserRepositoryImp().register(email, phone, password);
        System.out.println("Registration successful for " + email + "!\n");
    }

    // Method for login
    private void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- User Login ---");
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        new UserRepositoryImp().login(email, password);
       // System.out.println("Login successful! Welcome back!\n");
    }

    private void forgotPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Forgot Password ---");
        System.out.print("Enter your registered email: ");
        String email = scanner.nextLine();
        new UserRepositoryImp().forgetPass(email);
        System.out.println("Password reset link has been sent to " + email + ".\n");
    }

    public static void main(String[] args) {
        new MainGUI().start();
    }
}
