import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    public int getAccountNumber() {
        return accountNumber;
    }


    public int accountNumber;

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    private String userName;
    private double balance;
    private String password;

    public ArrayList<String> getListOfTransactions() {
        return listOfTransactions;
    }

    private ArrayList<String> listOfTransactions;


    public Account(int accountNumber, String userName, double balance, String password, ArrayList<String> listOfTransactions) {
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.balance = balance;
        this.password = password;
        this.listOfTransactions = listOfTransactions;
    }

    public boolean makeTransaction(double amount, Account targetAccount) {
        // Check if the sender has sufficient balance
        if (amount > 0 && amount <= this.balance) {
            // Deduct the amount from the sender's balance
            this.balance -= amount;

            // Add the amount to the receiver's balance
            targetAccount.balance += amount;

            // Log the transaction
            logTransaction(this, targetAccount, amount);
            targetAccount.listOfTransactions.add("Get a transaction of " + amount + "$ from " + this.userName);
            listOfTransactions.add("Make a transaction of " + amount + "$ to " + targetAccount.userName);
            System.out.println("Transaction successful!");
            System.out.println("Sender: " + this.userName + ", Balance: " + this.balance);
            System.out.println("Receiver: " + targetAccount.userName + ", Balance: " + targetAccount.balance);

            return true; // Transaction successful
        } else {
            System.out.println("Transaction failed. Insufficient balance.");
            return false; // Transaction failed
        }
    }

    private void logTransaction(Account sender, Account receiver, double amount) {
        // Log the transaction details to a file
        String logFilePath = "transactions.txt"; // Adjust the file name as needed

        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String logEntry = String.format("%s - %s transferred %.2f to %s%n",
                    timestamp.format(formatter), sender.getUserName(), amount, receiver.getUserName());

            writer.write(logEntry);
            System.out.println("Transaction logged.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public StringBuilder displayArrayList(ArrayList<String> arrayList) {
        StringBuilder message = new StringBuilder();

        // Append each element to the StringBuilder
        for (String element : arrayList) {
            message.append(element).append("\n");
        }
        return message;
    }
}
