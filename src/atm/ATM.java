package atm;

import java.util.Scanner;

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

    }
}
