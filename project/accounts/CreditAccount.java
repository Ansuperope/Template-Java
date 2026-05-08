/**
 * @file CreditAccount.java
 * @brief credit account, account for buying things
 */
package project.accounts;
import project.system.ErrorInvalidTransactionAmount;
import project.system.ErrorLowFunds;


public class CreditAccount extends BankAccount implements InterestFeature {
    //Maximum credit account limit
    private static final double MAX_LIMIT = 5000.0;

    private boolean paidCredit;       //True if credit has been fully paid
    private double interestPercent;   //Interest rate on borrowed amount
    private int timePass;             //How much time has passed

    //Default constructor
    public CreditAccount() {
        super();
        this.paidCredit = true;
        this.interestPercent = 0.03;
        this.timePass = 1;
    }

    //Parameterized constructor
    public CreditAccount(double amount, String name, double interestPercent, int timePass) {
        super(amount, name);
        this.paidCredit = (amount == 0);
        this.interestPercent = interestPercent;
        this.timePass = timePass;
    }

    //Getters
    public boolean getPaidCredit() { return paidCredit; }
    public double getInterestPercent() { return interestPercent; }
    public int getTimePass() { return timePass; }
    public double getMaxLimit() { return MAX_LIMIT; }

    //Setters
    public void setPaidCredit(boolean paidCredit) { this.paidCredit = paidCredit; }
    public void setInterestPercent(double interestPercent) { this.interestPercent = interestPercent; }
    public void setTimePass(int timePass) { this.timePass = timePass; }

    /**
     * @brief Determines if a penalty should be applied
     * @return true if did not pay credit / get a fee, false if paid credit
     */
    public boolean applyPenalty() {
        return !paidCredit;
    }

    /**
     * @brief Calculates penalty fee
     * @return returns penatlty fee
     */
    public double calcPenalty() {
        if (applyPenalty()) {
            return getBalance() * 0.02;
        }
        // No fee
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
        //Error: Cannot pay back negative amounts
        if (amount <= 0) {
            throw new ErrorInvalidTransactionAmount();
        }

        //Error: Cannot pay back more credit than is owed
        if (amount > getBalance()) {
            throw new ErrorInvalidTransactionAmount("Payment exceeds outstanding credit balance.");
        }

        setBalance(getBalance() - amount);
        paidCredit = (getBalance() == 0);
    }

    /**
     * @brief Withdraw money
     * @param amount to withdraw 
     */
    @Override
    public void withdraw(double amount) throws ErrorInvalidTransactionAmount, ErrorLowFunds {
        //Error: Cannot borrow negative amounts
        if (amount <= 0) {
            throw new ErrorInvalidTransactionAmount();
        }

        //Error: Cannot borrow if the credit limit would be exceeded
        if (getBalance() + amount > MAX_LIMIT) {
            throw new ErrorLowFunds("Cannot exceed the credit limit.");
        }

        setBalance(getBalance() + amount);
        paidCredit = false;
    }
}