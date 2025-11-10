package ATM;

import java.util.*;
import java.io.*;

public class Bank {
    private HashMap<String, User> users = new HashMap<>();
    private final String DATA_DIR = "data";

    public Bank() {
        loadUsers();
    }

    public void startATM() {
        Scanner sc = new Scanner(System.in);
        System.out.println("🏧 Welcome to the Java ATM System!");

        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();

        User currentUser = users.get(userId);

        if (currentUser == null) {
            System.out.println("\n⚙️ No user found with ID " + userId);
            System.out.print("Would you like to create a new account? (yes/no): ");
            String choice = sc.nextLine().trim().toLowerCase();

            if (choice.equals("yes")) {
                System.out.print("Set your 4-digit PIN: ");
                String pin = sc.nextLine();

                currentUser = new User(userId, pin, 0.0);
                users.put(userId, currentUser);
                saveUser(currentUser);

                System.out.println("✅ Account created successfully!");
            } else {
                System.out.println("👋 Exiting system. Goodbye!");
                sc.close();
                return;
            }
        } else {
            System.out.print("Enter PIN: ");
            String pin = sc.nextLine();

            if (!currentUser.validatePin(pin)) {
                System.out.println("\n❌ Incorrect PIN. Access denied.");
                sc.close();
                return;
            }
            System.out.println("\n✅ Login successful!");
        }

        ATM atm = new ATM(currentUser, users);
        atm.showMenu();
        saveUser(currentUser);
        sc.close();
    }

    // Load existing user data from files
    private void loadUsers() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) dir.mkdir();

        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
        if (files == null) return;

        for (File file : files) {
            try (Scanner reader = new Scanner(file)) {
                String id = file.getName().replace(".txt", "");
                String pin = "0000";
                double balance = 0;
                ArrayList<Transaction> transactions = new ArrayList<>();

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (line.startsWith("PIN:")) {
                        pin = line.split(":")[1].trim();
                    } else if (line.startsWith("BALANCE:")) {
                        balance = Double.parseDouble(line.split(":")[1].trim());
                    } else if (line.startsWith("TRANSACTION:")) {
                        String[] parts = line.substring("TRANSACTION:".length()).trim().split("\\|");
                        if (parts.length == 3)
                            transactions.add(new Transaction(parts[1].trim(), Double.parseDouble(parts[2].trim()), parts[0].trim()));
                    }
                }

                User u = new User(id, pin, balance);
                u.setTransactionHistory(transactions);
                users.put(id, u);
            } catch (Exception e) {
                System.out.println("⚠️ Failed to load user file: " + file.getName());
            }
        }
    }

    // Save a single user’s data to file
    public void saveUser(User user) {
        try {
            File dir = new File(DATA_DIR);
            if (!dir.exists()) dir.mkdir();

            File file = new File(dir, user.getUserId() + ".txt");
            PrintWriter writer = new PrintWriter(file);

            writer.println("PIN: " + user.getPin());
            writer.println("BALANCE: " + user.getBalance());
            for (Transaction t : user.getTransactionHistory()) {
                writer.println("TRANSACTION: " + t.getRaw());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("⚠️ Error saving data for " + user.getUserId());
        }
    }
}
