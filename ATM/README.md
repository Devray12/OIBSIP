🏧 Java ATM System

A console-based ATM simulation project built in Java, demonstrating Object-Oriented Programming (OOP) concepts such as classes, objects, encapsulation, and file handling.
Users can log in, check transaction history, withdraw, deposit, transfer funds, and quit safely — just like a real ATM.

📜 Features

✅ User Authentication — Login using User ID and PIN
✅ Transaction History — View all past transactions with timestamps
✅ Withdraw & Deposit — Perform safe cash operations
✅ Transfer Funds — Send money between users
✅ Persistent Storage — Balances and transactions are saved to files
✅ Object-Oriented Design — Uses 5+ Java classes for modular design
✅ Simple Console UI — Easy to use and extend

🧩 Project Structure
Java-ATM-System/
│
├── src/
│   ├── Main.java
│   ├── Bank.java
│   ├── ATM.java
│   ├── User.java
│   ├── Transaction.java
│
├── data/
│   ├── users.txt        # (auto-generated; stores user info)
│   └── transactions/    # (auto-generated; stores user transaction history)
│
├── .gitignore
└── README.md

🧠 Classes Overview
Class Name	Responsibility
Main	Entry point of the application
Bank	Handles user authentication and manages all user data
ATM	Displays ATM menu and handles user choices
User	Represents a user’s details, balance, and transactions
Transaction	Represents a single transaction record (type, amount, timestamp)
⚙️ How It Works

The program starts and prompts for User ID and PIN.

On successful login, it displays the main ATM menu.

The user can choose from:

🧾 Transaction History

💸 Withdraw

💰 Deposit

🔁 Transfer

🚪 Quit

Balances and transaction logs are updated and stored in text files.

🖥️ Example Run
🏧 Welcome to the Java ATM System!
Enter User ID: 12345
Enter PIN: 1234

✅ Login successful!

========= ATM MENU =========
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
Choose an option: 3
Enter amount to deposit: $500
✅ Deposited $500 successfully!

Choose an option: 1
📜 Transaction History:
11-11-2025 02:15:30 | Deposit | $500

🧰 Requirements

Java Development Kit (JDK) 8 or above

Any Java IDE (e.g., IntelliJ IDEA, Eclipse, VS Code)

Console/Terminal access

🚀 Run Instructions
Option 1 — Using Command Line
# Compile all Java files
javac src/*.java

# Run the program
java -cp src Main

Option 2 — Using an IDE

Open the project folder in your IDE.

Ensure the src folder is marked as the source root.

Run Main.java.

📦 Data Storage

User data and transaction logs are stored in the /data directory:

data/
├── users.txt
└── transactions/
    ├── 12345.txt
    ├── 67890.txt


Each file is automatically created the first time a user logs in or performs a transaction.

🛠️ Future Enhancements

🔹 GUI-based ATM interface using JavaFX / Swing
🔹 Integration with databases (MySQL)
🔹 PIN reset and account creation functionality
🔹 Support for multiple bank branches or admin accounts
