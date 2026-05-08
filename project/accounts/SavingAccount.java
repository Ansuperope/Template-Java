/**
 * @file SavingAccount.java
 * @brief saving account class. Put money to save
 */
package project.accounts;
import project.system.ErrorInvalidTransactionAmount;
import project.system.ErrorLowFunds;

public class SavingAccount extends BankAccount implements InterestFeature {
    //The max amount of transactions a user can perform to get the benefit of low activity
    private static final int MAX_LOW_ACTIVITY = 10;

    private int transactions;         //Tracks the number of transactions on this account
    private boolean lowActivity;      //True if the user qualifies for low activity
    private double interestPercent;   //Percent interest on this savings account
    private int timePass;             //How much time has passed

    //Default constructor
    public SavingAccount() {
        super();
        this.transactions = 0;
        this.lowActivity = true;
        this.interestPercent = 0.02;
        this.timePass = 1;
    }

    //Parameterized constructor
    public SavingAccount(double amount, String name, double interestPercent, int timePass) {
        super(amount, name);
        this.transactions = 0;
        this.interestPercent = interestPercent;
        this.timePass = timePass;
        this.lowActivity = qualifyForLowActivity();
    }

    //Getters
    public int getTransactions() { return transactions; }
    public boolean getLowActivity() { return lowActivity; }
    public double getInterestPercent() { return interestPercent; }
    public int getTimePass() { return timePass; }
    public int getMaxLowActivity() { return MAX_LOW_ACTIVITY; }

    //Setters
    public void setTransactions(int transactions) {
        this.transactions = transactions;
        this.lowActivity = qualifyForLowActivity();
    }

    public void setLowActivity(boolean lowActivity) { this.lowActivity = lowActivity; }
    public void setInterestPercent(double interestPercent) { this.interestPercent = interestPercent; }
    public void setTimePass(int timePass) { this.timePass = timePass; }

    /**
     * @brief Checks whether the account qualifies for low activity benefits
     * @return true if user qualifies, false if user does NOT
     */
    public boolean qualifyForLowActivity() {
        return transactions <= MAX_LOW_ACTIVITY;
    }

    /**
     * @brief Calculates the extra money earned from low activity status
     * @return amount received from benefit
     */
    public double lowActivityBenefit() {
        if (qualifyForLowActivity()) {
            return getBalance() * 0.01;
        }
        // if not qualified
        return 0.0;
    }

    /**
     * @brief InterestFeature interface method
     * @return interest amount  
     */
    @Override
    public double interestAmount() {
        return getBalance() * interestPercent * timePass;
    }

    /**
     * @brief Desposit money into account
     * @param amount to deposit 
     */
    @Override
    public void deposit(double amount) throws ErrorInvalidTransactionAmount {
        //Error: Cannot deposit negative amounts
        if (amount <= 0) {
            throw new ErrorInvalidTransactionAmount();
        }

        setBalance(getBalance() + amount);
        transactions++;
        lowActivity = qualifyForLowActivity();
    }

    /**
     * @brief Withdraw money
     * @param amount to withdraw 
     */
    @Override
    public void withdraw(double amount) throws ErrorInvalidTransactionAmount, ErrorLowFunds {
        //Error: Cannot withdraw negative amounts
        if (amount <= 0) {
            throw new ErrorInvalidTransactionAmount();
        }

        //Error: Cannot withdraw if attempting to withdraw more than the account balance
        if (amount > getBalance()) {
            throw new ErrorLowFunds("Not enough funds in savings account.");
        }

        setBalance(getBalance() - amount);
        transactions++;
        lowActivity = qualifyForLowActivity();
    }
}