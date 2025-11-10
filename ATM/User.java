package ATM;

import java.util.ArrayList;

public class User {
    private String userId;
    private String pin;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public void addTransaction(String type, double amount) {
        transactionHistory.add(new Transaction(type, amount));
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(ArrayList<Transaction> list) {
        this.transactionHistory = list;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("Deposit", amount);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        addTransaction("Withdraw", amount);
        return true;
    }

    public void transfer(User recipient, double amount) {
        this.balance -= amount;
        recipient.balance += amount;
        addTransaction("Transfer to " + recipient.getUserId(), amount);
        recipient.addTransaction("Transfer from " + this.userId, amount);
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }
}
