package org.example;

import java.util.Scanner;

public class Login {

    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    private String storedFirstName;
    private String storedLastName;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9\\s]).{8,}$";
        return password.matches(regex);
    }

    public boolean checkCellPhoneNumber(String phoneNumber) {
        String regex = "^\\+\\d{1,3}\\d{1,10}$";
        return phoneNumber.matches(regex);
    }

    public String registerUser(String username, String password, String cellNumber,
                               String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username " +
                    "contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password " +
                    "contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        storedUsername = username;
        storedPassword = password;
        storedCellNumber = cellNumber;
        storedFirstName = firstName;
        storedLastName = lastName;
        return "Registration successful. Welcome " + firstName + " " + lastName + ".";
    }

    public boolean loginUser(String username, String password) {
        return storedUsername != null && storedPassword != null &&
                storedUsername.equals(username) && storedPassword.equals(password);
    }

    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + storedFirstName + ", " + storedLastName +
                    " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public String getStoredUsername() { return storedUsername; }
    public String getStoredPassword() { return storedPassword; }
    public String getStoredCellNumber() { return storedCellNumber; }
    public String getStoredFirstName() { return storedFirstName; }
    public String getStoredLastName() { return storedLastName; }

    public static boolean performLogin(Scanner scanner, Login userLogin) {
        System.out.println("\n=== Login ===");
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter username: ");
            String inputUsername = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String inputPassword = scanner.nextLine().trim();

            if (userLogin.loginUser(inputUsername, inputPassword)) {
                System.out.println(userLogin.returnLoginStatus(true));
                return true;
            } else {
                attempts++;
                System.out.println("Login failed. " + (MAX_ATTEMPTS - attempts) + " attempts remaining.");
            }
        }
        System.out.println(userLogin.returnLoginStatus(false));
        return false;
    }
}