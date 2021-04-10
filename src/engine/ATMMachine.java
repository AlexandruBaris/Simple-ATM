package engine;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ATMMachine {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================================================");
        System.out.println("\tWelcome to this simple ATM machine");
        System.out.println("====================================================");
        boolean cardMatch = false;
        do {
            System.out.print("\nEnter your card number: ");
            String card = scanner.nextLine();
            if (card.length() == 16 && Pattern.matches("[0-9]+", card)) {
                cardMatch = true;
                boolean pinMatch = false;
                do {
                    System.out.print("\nEnter your pin number: ");
                    String pin = scanner.next();
                    if (pin.length() == 4 && Pattern.matches("[0-9]+", pin)) {
                        pinMatch = true;
                        String operation;
                        boolean correctOperation = false;
                        do {
                            System.out.println("\n\tPlease select ATM Transactions");
                            System.out.println("\tPress [1] Deposit");
                            System.out.println("\tPress [2] Withdraw");
                            System.out.println("\tPress [3] Balance Inquiry");
                            System.out.println("\tPress [4] Transfer");
                            System.out.print("\tPress [5] Exit\n\t>:");
                            operation = scanner.next();
                            if (Pattern.matches("[12345]", operation)) {
                                correctOperation = true;
                                switch (operation) {
                                    case "1" -> System.out.println();
                                    case "2" -> System.out.println();
                                    case "3" -> System.out.println();
                                    case "4" -> System.out.println();
                                    case "5" -> System.out.println("\n\tThank you for using this simple ATM Machine");
                                }

                            }
                        } while (!correctOperation);
                    } else {
                        System.out.println("Wrong pin number");
                    }
                } while (!pinMatch);

            } else {
                System.out.print("Please enter correct card number\n");
            }

        } while (!cardMatch);
    }
}
