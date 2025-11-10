package ATM;

import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private User user;
    private HashMap<String, User> users;
    private Scanner sc;
    private Bank bank; // added reference to Bank

    public ATM(User user, HashMap<String, User> users) {
        this.user = user;
        this.users = users;
        this.sc = new Scanner(System.in);
        this.bank = new Bank(); // for saving after each transaction
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n========= ATM MENU =========");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> showTransactionHistory();
                case 2 -> withdraw();
                case 3 -> deposit();
                case 4 -> transfer();
                case 5 -> System.out.println("👋 Thank you for using our ATM!");
                default -> System.out.println("Invalid option! Try again.");
            }
        } while (choice != 5);
    }

    private void showTransactionHistory() {
        System.out.println("\n📜 Transaction History:");
        if (user.getTransactionHistory().isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : user.getTransactionHistory()) {
                System.out.println(t);
            }
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: $");
        double amount = sc.nextDouble();
        if (user.withdraw(amount)) {
            System.out.println("✅ Withdrawn $" + amount + " successfully!");
            bank.saveUser(user);
        } else {
            System.out.println("❌ Insufficient balance!");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: $");
        double amount = sc.nextDouble();
        user.deposit(amount);
        System.out.println("✅ Deposited $" + amount + " successfully!");
        bank.saveUser(user);
    }

    private void transfer() {
        System.out.print("Enter recipient user ID: ");
        String recipientId = sc.next();
        System.out.print("Enter amount to transfer: $");
        double amount = sc.nextDouble();

        User recipient = users.get(recipientId);
        if (recipient == null) {
            System.out.println("❌ Invalid recipient ID!");
            return;
        }

        if (amount > user.getBalance()) {
            System.out.println("❌ Insufficient balance!");
            return;
        }

        user.transfer(recipient, amount);
        System.out.println("✅ Transferred $" + amount + " to user " + recipientId + " successfully!");
        bank.saveUser(user);
        bank.saveUser(recipient);
    }
}
