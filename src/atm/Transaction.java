package atm;

import java.util.Date;

public class Transaction {
    private double amount;
    private Date timeHolder;
    private String memo;
    private Account inAccount;

    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timeHolder = new Date();
        this.memo = memo;
    }

    public Transaction(double amount, String memo, Account inAccount) {
        this(amount, inAccount);
        this.memo = memo;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getSummaryLine() {
        if (this.amount >= 0) {
            return String.format("%s : $%.02f : %s", this.timeHolder.toString(), this.amount, this.memo);
        } else {
            return String.format("%s : $%.02f : %s", this.timeHolder.toString(), -this.amount, this.memo);
        }
    }


}
