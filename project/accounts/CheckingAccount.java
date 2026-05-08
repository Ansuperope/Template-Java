/**
 * @file CheckingAccount.java
 * @brief 
 */
package project.accounts;
import project.system.ErrorInvalidTransactionAmount;
import project.system.ErrorLowFunds;

public class CheckingAccount extends BankAccount {
    private static final int MAX_WITHDRAWALS = 5;   //Withdrawal limit for a checking account
    private int numWithdrawals;                     //Number of withdrawals on this account

    //Default constructor
    public CheckingAccount() {
        super();
        this.numWithdrawals = 0;
    }

    //Parameterized constructor
    public CheckingAccount(double amount, String name) {
        super(amount, name);
        this.numWithdrawals = 0;
    }

    //Getter
    public int getNumWithdrawals() { return numWithdrawals; }
    public int getMaxWithdrawals() { return MAX_WITHDRAWALS; }

    //Setter
    public void setNumWithdrawals(int numWithdrawals) { this.numWithdrawals = numWithdrawals; }

    /**
     * @Checks whether the account reached max withdrawals
     * @return true if at or over max amount, false if not at max
     */
    public boolean atMaxWithdrawal() { 
        return numWithdrawals >= MAX_WITHDRAWALS; 
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
        
        //Error: Cannot withdraw if at the withdrawal limit
        if (atMaxWithdrawal()) {
            throw new ErrorLowFunds("Maximum number of withdrawals reached for checking account.");
        }
        
        //Error: Cannot withdraw if attempting to withdraw more than the account balance
        if (amount > getBalance()) {
            throw new ErrorLowFunds("Not enough funds in checking account.");
        }

        setBalance(getBalance() - amount);
        numWithdrawals++;
    }
}