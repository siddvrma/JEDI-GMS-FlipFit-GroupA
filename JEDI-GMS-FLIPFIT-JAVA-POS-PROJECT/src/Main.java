import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, String> userCredentials = new HashMap<>();

    public static void main(String[] args) {
        int mainChoice;

        do {
            System.out.println("Welcome to the FlipFitApp");
            System.out.println("Enter the choice:");
            System.out.println("1.) Login");
            System.out.println("2.) Registration of the Customer (SignUp)");
            System.out.println("3.) Update Password");
            System.out.println("4.) Exit");

            mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1:
                    loginMenu();
                    break;
                case 2:
                    registrationMenu();
                    break;
                case 3:
                    updatePasswordMenu();
                    break;
                case 4:
                    System.out.println("Exiting FlipFitApp. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (mainChoice != 4);

        scanner.close();
    }

    private static void loginMenu() {
        int loginChoice;

        do {
            System.out.println("Login Menu");
            System.out.println("1.) Perform Login");
            System.out.println("2.) Exit to Main Menu");

            loginChoice = scanner.nextInt();

            switch (loginChoice) {
                case 1:
                    performLogin();
                    break;
                case 2:
                    System.out.println("Exiting to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (loginChoice != 2);
    }

    private static void performLogin() {
        System.out.println("Enter the UserName:");
        String userName = scanner.next();

        System.out.println("Enter the Password:");
        String password = scanner.next();

        if (userCredentials.containsKey(userName) && userCredentials.get(userName).equals(password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    private static void registrationMenu() {
        int registrationChoice;

        do {
            System.out.println("Registration Menu");
            System.out.println("1.) Perform Registration");
            System.out.println("2.) Exit to Main Menu");

            registrationChoice = scanner.nextInt();

            switch (registrationChoice) {
                case 1:
                    performRegistration();
                    break;
                case 2:
                    System.out.println("Exiting to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (registrationChoice != 2);
    }

    private static void performRegistration() {
        System.out.println("Enter First Name:");
        String firstName = scanner.next();

        System.out.println("Enter Last Name:");
        String lastName = scanner.next();

        System.out.println("Enter Email:");
        String email = scanner.next();

        System.out.println("Enter UserName:");
        String userName = scanner.next();

        System.out.println("Enter Password:");
        String password = scanner.next();

        userCredentials.put(userName, password);
        System.out.println("Registration successful!");
    }

    private static void updatePasswordMenu() {
        int updatePasswordChoice;

        do {
            System.out.println("Update Password Menu");
            System.out.println("1.) Perform Password Update");
            System.out.println("2.) Exit to Main Menu");

            updatePasswordChoice = scanner.nextInt();

            switch (updatePasswordChoice) {
                case 1:
                    performPasswordUpdate();
                    break;
                case 2:
                    System.out.println("Exiting to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (updatePasswordChoice != 2);
    }

    private static void performPasswordUpdate() {
        System.out.println("Enter UserName:");
        String userName = scanner.next();

        if (userCredentials.containsKey(userName)) {
            System.out.println("Enter Current Password:");
            String currentPassword = scanner.next();

            String storedPassword = userCredentials.get(userName);

            if (currentPassword.equals(storedPassword)) {
                System.out.println("Enter New Password:");
                String newPassword = scanner.next();

                userCredentials.put(userName, newPassword);
                System.out.println("Password updated successfully!");
            } else {
                System.out.println("Incorrect current password. Password update failed.");
            }
        } else {
            System.out.println("User not found. Password update failed.");
        }
    }
}
