import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.util.Random;

public class Bank {
    private List<Account> listOfClients;

    public Bank() {
        this.listOfClients = new ArrayList<>();
        generateAndWriteClientsToFile();
        initializeAccountsFromFile("clients.txt"); // Adjust the file name as needed
    }

    private void initializeAccountsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int accountNumber = Integer.parseInt(parts[0]);
                String name = parts[1];
                String password = parts[2];
                double balance = Double.parseDouble(parts[3]);
                ArrayList<String> listOfTransactions = new ArrayList<String>();
                Account account = new Account(accountNumber, name, balance, password, listOfTransactions);
                listOfClients.add(account);
            }

            System.out.println("Accounts initialized from file.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public Account authenticateUser(int accountNumber, String password) {
        for (Account account : listOfClients) {
            if (account.getAccountNumber() == accountNumber && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null; // Authentication failed
    }

    public boolean makeTransaction(Account sender, int targetAccountNumber, double amount) {
        Account receiver = getAccountByNumber(targetAccountNumber);

        if (receiver != null && sender.makeTransaction(amount, receiver)) {
            return true; // Transaction successful
        }

        return false; // Transaction failed
    }

    private Account getAccountByNumber(int accountNumber) {
        for (Account account : listOfClients) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null; // Account not found
    }

    public List<Account> getListOfClients() {
        return listOfClients;
    }

    public void readTransactionLog() {
        String logFilePath = "transactions.txt"; // Adjust the file name as needed

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;

            System.out.println("Transaction Log:");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateAndWriteClientsToFile() {
        try (FileWriter writer = new FileWriter("clients.txt")) {
            Random random = new Random();

            for (int i = 1; i <= 50; i++) {
                String name = "Client" + i;
                int accountNumber = 1000 + i; // Assuming unique account numbers starting from 1001
                String password = "pass" + i;
                double balance = 1000.0 + random.nextDouble() * 9000.0; // Random balance between 1000 and 10000
                ArrayList<String> listOfTransactions = new ArrayList<String>();
                Account account = new Account(accountNumber, name, balance, password, listOfTransactions);
                listOfClients.add(account);

                // Write client details to the file
                String clientDetails = accountNumber + "," + name + "," + password + "," + balance + "\n";
                writer.write(clientDetails);
            }

            System.out.println("Generated and wrote 50 clients to clients.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}