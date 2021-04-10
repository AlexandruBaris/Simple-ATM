package repository;

import java.util.ArrayList;
import java.util.List;

public class Accounts {
    private long cardNumber;
    private int pin;
    private double balance;
    private List<Operations> operations = new ArrayList<>();

    public Accounts(long cardNumber, int pin, double balance, List<Operations> operations) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.operations = operations;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
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
}
