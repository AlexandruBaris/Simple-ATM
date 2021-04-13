package repository;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Account {
    Scanner scanner = new Scanner(System.in);
    private int pin;
    private double balance;
    DecimalFormat money = new DecimalFormat("'$'###,##0.00");
    private String cardNumber;


    public Account(String cardNumber, int pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double withdraw(String amount) {
        if (Double.parseDouble(amount) > balance && Pattern.matches("[0-9]+", amount)) {
            System.out.println("Invalid amount");
            withdraw(amount);
        }
        return balance -= Double.parseDouble(amount);
    }

    public double deposit(String amount) {
        if (Double.parseDouble(amount) < 20 && Pattern.matches("[0-9]+", amount)) {
            System.out.println("Invalid amount");
            deposit(amount);
        }
        return balance += Double.parseDouble(amount);
    }


}
