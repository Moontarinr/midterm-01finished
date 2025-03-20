package academy.javapro;

/**
 * CheckingAccount class extending the abstract Account class.
 * Features overdraft protection and transaction fees.
 */
public class CheckingAccount extends Account {
    private double overdraftLimit; 
    private static final double TRANSACTION_FEE = 1.5; // Fee per withdrawal

    /**
     * Constructor for creating a new checking account.
     *
     * @param accountNumber The account number
     * @param customerName The name of the account holder
     * @param initialBalance The initial balance
     * @param overdraftLimit The maximum allowed overdraft
     */
    public CheckingAccount(String accountNumber, String customerName, double initialBalance, double overdraftLimit) {
        super(accountNumber, customerName, initialBalance); // Call to the parent constructor
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Getter for overdraft limit.
     *
     * @return The overdraft limit
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * Sets a new overdraft limit.
     *
     * @param overdraftLimit The new overdraft limit
     */
    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit < 0) {
            System.out.println("Overdraft limit cannot be negative.");
        
            return;
        }
        this.overdraftLimit = overdraftLimit;
        System.out.println("Overdraft limit updated to $" + String.format("%.1f", overdraftLimit));
}
    

    /**
     * Overrides the withdraw method with checking account-specific rules.
     * Implements overdraft protection and applies transaction fees.
     */
    @Override
    public void withdraw(double amount) {
    
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        double totalDeduction = amount + TRANSACTION_FEE;
        double availableBalance = getBalance() + overdraftLimit;

        if (totalDeduction > availableBalance) {
            System.out.println("Insufficient funds. Overdraft limit exceeded.");
            return;
        }

        setBalance(getBalance() - totalDeduction);
        logTransaction("WITHDRAWAL", amount);
        logTransaction("FEE", TRANSACTION_FEE);

        System.out.println("Withdrew $" + amount + " from checking account");
        System.out.println("Transaction fee: $" + TRANSACTION_FEE);

        if (getBalance() < 0) {
        System.out.println("Account is in overdraft. Current balance: $" + String.format("%.2f", getBalance()));
    }
}
    
    /**
     * Overrides the displayInfo method to include checking account-specific information.
     */
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call to the parent method
        System.out.println("Account Type: Checking Account");
        System.out.println("Overdraft Limit: $" + String.format("%.2f", overdraftLimit));
        System.out.println("Transaction Fee: $" + String.format("%.2f", TRANSACTION_FEE));
    }
}
