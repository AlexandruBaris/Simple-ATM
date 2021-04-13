package demo;

import repository.Account;

public class Test {
    public static void main(String[] args) {
        Account account = new Account("123456789123", 1234, 0.0);
        account.deposit("-12");
    }
}
