package org.example;

import org.example.Login;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();

        System.out.println("=== User Registration ===");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        String username;
        String password;
        String cellNumber;
        String registrationMessage;

        while (true) {
            System.out.print("Enter username (must contain '_' and be ≤5 characters): ");
            username = scanner.nextLine().trim();
            System.out.print("Enter password (≥8 chars, 1 uppercase, 1 digit, 1 special char): ");
            password = scanner.nextLine().trim();
            System.out.print("Enter cell number (with international code, e.g., +27831234567): ");
            cellNumber = scanner.nextLine().trim();

            registrationMessage = loginSystem.registerUser(username, password, cellNumber, firstName, lastName);
            System.out.println(registrationMessage);

            if (registrationMessage != null && registrationMessage.startsWith("Registration successful")) {
                break;
            } else {
                System.out.println("Please re-enter all details.\n");
            }
        }

        System.out.println("\nRegistration complete. You may now log in.");
        boolean loggedIn = Login.performLogin(scanner, loginSystem);

        if (!loggedIn) {
            System.out.println("Maximum login attempts exceeded. Exiting.");
        }

        scanner.close();
    }
}