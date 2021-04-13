package atm;

import java.util.Scanner;

public class ATM {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        Bank bank1 = new Bank("MDI");

        User user1 = bank1.addUser("Alex", "Baris", "1234");

        Account account1 = new Account("Checking", user1, bank1);
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
        int choice;
        do {
            System.out.printf("Welcome %s, please select ATM operation\n", user.getFirstName());
            System.out.println("  Press [1] Transaction history");
            System.out.println("  Press [2] Withdraw");
            System.out.println("  Press [3] Deposit");
            System.out.println("  Press [4] Transfer");
            System.out.print("  Press [5] Exit\nEnter choice: ");
            choice = scanner.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please choose 1-5");

            }
        } while (choice < 1 || choice > 5);

        switch (choice) {
            case 1 -> ATM.showTxnHistory(user, scanner);
            case 2 -> ATM.withdraw(user, scanner);
            case 3 -> ATM.deposit(user, scanner);
            case 4 -> ATM.transfer(user, scanner);
            case 5 -> scanner.nextLine();
        }

        if (choice != 5) {
            ATM.printUserMenu(user, scanner);
        }
    }

    public static void showTxnHistory(User user, Scanner scanner) {
        int acc;
        do {
            System.out.printf("Enter the number (1-%d) of the account : ", user.numAccounts());
            acc = scanner.nextInt();
            if (acc < 0 || acc > user.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (acc < 0 || acc > user.numAccounts());

        user.printAccountTxnHistory(acc);
    }

    public static void transfer(User user, Scanner scanner) {
        int fromAcc;
        int toAcc;
        double amount;
        double accBalance;

        do {
            System.out.printf("Enter the number (1-%d) of the account to transfer from: ", user.numAccounts());
            fromAcc = scanner.nextInt() - 1;
            if (fromAcc < 0 || fromAcc > user.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcc < 0 || fromAcc > user.numAccounts());

        accBalance = user.getAccBalance(fromAcc);

        do {
            System.out.printf("Enter the number (1-%d) of the account to transfer to: ", user.numAccounts());
            toAcc = scanner.nextInt() - 1;
            if (toAcc < 0 || toAcc > user.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcc < 0 || toAcc > user.numAccounts());

        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", accBalance);
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than 0.");
            } else if (amount > accBalance) {
                System.out.println("Invalid amount.");
            }
        } while (amount < 0 || amount > accBalance);

        user.addAccTxn(fromAcc, -1 * amount, String.format("Transfer to account %s", user.getAccUUID(toAcc)));
        user.addAccTxn(toAcc, amount, String.format("Transfer to account %s", user.getAccUUID(fromAcc)));
    }

    public static void withdraw(User user, Scanner scanner) {
        int fromAcc;
        String memo;
        double amount;
        double accBalance;

        do {
            System.out.printf("Enter the number (1-%d) of the account to withdraw from: ", user.numAccounts());
            fromAcc = scanner.nextInt() - 1;
            if (fromAcc < 0 || fromAcc > user.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcc < 0 || fromAcc > user.numAccounts());

        accBalance = user.getAccBalance(fromAcc);

        do {
            System.out.printf("Enter the amount to withdraw (max $%.02f): $", accBalance);
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than 0.");
            } else if (amount > accBalance) {
                System.out.println("Invalid amount.");
            }
        } while (amount < 0 || amount > accBalance);

        scanner.nextLine();
        System.out.print("Enter a memo: ");
        memo = scanner.nextLine();

        user.addAccTxn(fromAcc, -1 * amount, memo);
    }

    public static void deposit(User user, Scanner scanner) {
        int toAcc;
        String memo;
        double amount;
        double accBalance;

        do {
            System.out.printf("Enter the number (1-%d) of the account to deposit in: ", user.numAccounts());
            toAcc = scanner.nextInt() - 1;
            if (toAcc < 0 || toAcc > user.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcc < 0 || toAcc > user.numAccounts());

        accBalance = user.getAccBalance(toAcc);

        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", accBalance);
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than 0.");
            }

        } while (amount < 0);

        scanner.nextLine();
        System.out.print("Enter a memo: ");
        memo = scanner.nextLine();

        user.addAccTxn(toAcc, amount, memo);
    }

}
