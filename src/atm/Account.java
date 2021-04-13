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

}
