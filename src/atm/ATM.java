package atm;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ATM {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        Bank bank1 = new Bank("MDI");

        User user1 = bank1.addUser("Alex", "Baris", "1234");

        Account account1 = new Account("Test", user1, bank1);
        user1.addAccount(account1);
        bank1.addAccount(account1);

        User currentUser;

        while (true) {
            currentUser = ATM.mainMenuPrompt(bank1, scanner);
            ATM.printUserMenu(currentUser, scanner);
        }

    }

    public static User mainMenuPrompt(Bank bank, Scanner scanner) {

        String userID;
        String pin;
        User authUser;

        do {

            System.out.printf("\n\nWelcome to %s\n\n", bank.getName());
            System.out.print("Enter User ID: ");
            userID = scanner.nextLine();
            System.out.print("Enter pin: ");
            pin = scanner.nextLine();

            authUser = bank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID or pin, please try again.");
            }

        } while (authUser == null);

        return authUser;
    }

    public static void printUserMenu(User user, Scanner scanner) {
        user.printAccountsSummary();
        String choice;
        do {
            System.out.printf("Welcome %s, please select ATM operation", user.getFirstName());
            System.out.println("\tPress [1] Transaction history");
            System.out.println("\tPress [2] Deposit");
            System.out.println("\tPress [3] Withdraw");
            System.out.println("\tPress [4] Transfer");
            System.out.print("\tPress [5] Exit\nEnter choice: ");
            choice = scanner.nextLine();

            if (Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > 5 &&
                    Pattern.matches("[12345]", choice)) {
                System.out.println("Invalid choice. Please choose 1-5");

            }
        } while (Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > 5 &&
                Pattern.matches("[12345]", choice));

        switch (choice) {
            case "1" -> ATM.showTxnHistory(user, scanner);
            case "2" -> ATM.withdraw(user, scanner);
            case "3" -> ATM.deposit(user, scanner);
            case "4" -> ATM.transfer(user, scanner);
        }

        if (Integer.parseInt(choice) != 5) {
            ATM.printUserMenu(user, scanner);
        }
    }

    public static void showTxnHistory(User user, Scanner scanner) {

    }
}
