public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        // Populate the bank with sample accounts
        //bank.populateSampleAccounts();

        ATM atm = new ATM(bank);
        atm.runSimulation();
    }
}