package atm;

import java.util.ArrayList;

public class Account {
    private String uuid;
    private User holder;
    private String name;
    private ArrayList<Transaction> transactions;

    public Account(String name, User holder, Bank bank) {
        this.name = name;
        this.holder = holder;
        this.uuid = bank.getNewAccountUUID();
        this.transactions = new ArrayList<>();

    }

    public String getUUID() {
        return this.uuid;
    }

    public String getSummaryLine() {

        double balance = this.getBalance();
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
        }
    }

    public double getBalance() {

        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

}
