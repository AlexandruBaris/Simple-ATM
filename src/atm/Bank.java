package atm;

import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public String getNewUserUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public String getNewAccountUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }


    public User addUser(String firstname, String lastname, String pin) {
        User newUser = new User(firstname, lastname, pin, this);
        this.users.add(newUser);

        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    public User userLogin(String userID, String pin) {
        for (User u : this.users) {
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

}
