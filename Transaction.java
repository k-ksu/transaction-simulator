public class Transaction {
    private final int accountNumberFrom;
    private final int accountNumberTo;
    private final String nameFrom;
    private final String nameTo;
    private final int amountOfTransaction;
    public int getAccountNumberFrom() {
        return accountNumberFrom;
    }

    public int getAccountNumberTo() {
        return accountNumberTo;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public String getNameTo() {
        return nameTo;
    }

    public int getAmountOfTransaction() {
        return amountOfTransaction;
    }


    public Transaction(int accountNumberFrom, int accountNumberTo, String nameFrom, String nameTo, int amountOfTransaction) {
        this.accountNumberFrom = accountNumberFrom;
        this.accountNumberTo = accountNumberTo;
        this.nameFrom = nameFrom;
        this.nameTo = nameTo;
        this.amountOfTransaction = amountOfTransaction;
    }
}
