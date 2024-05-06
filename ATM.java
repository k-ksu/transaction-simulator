import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM {
    private Bank bank;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public void runSimulation() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ATM Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton authenticateButton = new JButton("Authenticate User");
            JButton exitButton = new JButton("Exit");

            JPanel panel = new JPanel();
            panel.add(authenticateButton);
            panel.add(exitButton);

            frame.getContentPane().add(panel);
            frame.setSize(300, 150);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            authenticateButton.addActionListener(e -> authenticateUser());
            exitButton.addActionListener(e -> System.exit(0));
        });
    }

    private void authenticateUser() {
        JFrame authFrame = new JFrame("Authentication");
        authFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField accountNumberField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        JButton authenticateButton = new JButton("Authenticate");

        JPanel authPanel = new JPanel();
        authPanel.add(new JLabel("Account Number:"));
        authPanel.add(accountNumberField);
        authPanel.add(new JLabel("Password:"));
        authPanel.add(passwordField);
        authPanel.add(authenticateButton);

        authFrame.getContentPane().add(authPanel);
        authFrame.setSize(300, 150);
        authFrame.setLocationRelativeTo(null);
        authFrame.setVisible(true);

        authenticateButton.addActionListener(e -> {
            int accountNumber = Integer.parseInt(accountNumberField.getText());
            String password = new String(passwordField.getPassword());

            Account userAccount = bank.authenticateUser(accountNumber, password);

            if (userAccount != null) {
                JOptionPane.showMessageDialog(null, "Authentication successful! Welcome, " + userAccount.getUserName() + ".");
                performUserOperations(userAccount);
                authFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Authentication failed. Invalid account number or password.");
            }
        });
    }

    private void performUserOperations(Account userAccount) {
        JFrame userFrame = new JFrame("User Operations");
        userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton viewBalanceButton = new JButton("View Balance");
        JButton makeTransactionButton = new JButton("Make Transaction");
        JButton logoutButton = new JButton("Logout");
        JButton viewTransactionsButton = new JButton("Transactions");

        JPanel userPanel = new JPanel();
        userPanel.add(viewBalanceButton);
        userPanel.add(makeTransactionButton);
        userPanel.add(logoutButton);
        userPanel.add(viewTransactionsButton);

        userFrame.getContentPane().add(userPanel);
        userFrame.setSize(300, 150);
        userFrame.setLocationRelativeTo(null);
        userFrame.setVisible(true);

        viewBalanceButton.addActionListener(e -> viewBalance(userAccount));
        makeTransactionButton.addActionListener(e -> makeTransaction(userAccount));
        logoutButton.addActionListener(e -> userFrame.dispose());
        viewTransactionsButton.addActionListener(e -> getAllTransactions(userAccount));
    }

    private void getAllTransactions(Account userAccount) {
        StringBuilder message = userAccount.displayArrayList(userAccount.getListOfTransactions());
        JOptionPane.showMessageDialog(null, "Your transactions: \n" + message);
    }
    private void viewBalance(Account userAccount) {
        JOptionPane.showMessageDialog(null, "Your current balance: $" + userAccount.getBalance());
    }

    private void makeTransaction(Account userAccount) {

        JFrame transactionFrame = new JFrame("Make Transaction");
        transactionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField targetAccountField = new JTextField(10);
        JTextField amountField = new JTextField(10);
        JButton makeTransactionButton = new JButton("Make Transaction");

        JPanel transactionPanel = new JPanel();
        transactionPanel.add(new JLabel("Target Account Number:"));
        transactionPanel.add(targetAccountField);
        transactionPanel.add(new JLabel("Amount: $"));
        transactionPanel.add(amountField);
        transactionPanel.add(makeTransactionButton);

        transactionFrame.getContentPane().add(transactionPanel);
        transactionFrame.setSize(300, 150);
        transactionFrame.setLocationRelativeTo(null);
        transactionFrame.setVisible(true);

        makeTransactionButton.addActionListener(e -> {
            int targetAccountNumber = Integer.parseInt(targetAccountField.getText());
            double amount = Double.parseDouble(amountField.getText());

            if (bank.makeTransaction(userAccount, targetAccountNumber, amount)) {
                viewBalance(userAccount);
                transactionFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Transaction failed. Please check your inputs and try again.");
            }
        });
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        ATM atm = new ATM(bank);
        atm.runSimulation();
    }
}
