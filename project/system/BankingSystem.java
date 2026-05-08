/**
 * @file BankingSystem.java
 * @brief banking system to manage all accounts
 */
package project.system;

import java.util.ArrayList;
import java.util.List;

import project.accounts.BankAccount;
import project.accounts.CreditAccount;
import project.accounts.InterestFeature;

public class BankingSystem {
    private List<BankAccount> accounts; // list of all accounts in system
    private int numberOfAccounts;       // number of accounts in system

    //Default constructor
    public BankingSystem() {
        this.accounts = new ArrayList<>();
        this.numberOfAccounts = 0;
    }

    //Getters
    public List<BankAccount> getAccounts() { return accounts; }
    public int getNumberOfAccounts() { return numberOfAccounts; }

    /**
     * @brief Add an account to system
     * @param account to add
     */
    public void addAccount(BankAccount account) {
        if (account != null) {
            accounts.add(account);
            numberOfAccounts++;
        }
    }

    /**
     * @brief Find an account by owner name. Throws an exception if no account under that name is found
     * @param ownerName account owner name to search
     */
    public BankAccount findAccount(String ownerName) throws ErrorAccountNotFound {
        for (BankAccount account : accounts) {
            if (account.getAccountOwner().equalsIgnoreCase(ownerName)) {
                return account;
            }
        }
        //Error: Account with ownerName not found
        throw new ErrorAccountNotFound("Account for owner \"" + ownerName + "\" not found.");
    }

    /**
     * @brief Deposit into an account
     * @param ownerName account to put money in 
     * @param amount amount to deposit
     */
    public void depositToAccount(String ownerName, double amount) throws ErrorAccountNotFound, ErrorInvalidTransactionAmount {
        BankAccount account = findAccount(ownerName);
        account.deposit(amount);
    }

    /**
     * @brief Withdraw from an account. Throws an exception if there are insufficient funds in an account
     * @param ownerName account to take money from 
     * @param amount amount to withdraw
     */
    public void withdrawFromAccount(String ownerName, double amount) throws ErrorAccountNotFound, ErrorLowFunds, ErrorInvalidTransactionAmount {
        BankAccount account = findAccount(ownerName);
        account.withdraw(amount);
    }

    /**
     * @brief Transfer money between two accounts. Throws an exception if there are insufficient funds in the source account
     * @param fromOwner account to take money from
     * @param toOwner account to give money to
     * @param amount amount to transfer
     */
    public boolean transferMoney(String fromOwner, String toOwner, double amount) throws ErrorAccountNotFound, ErrorLowFunds, ErrorInvalidTransactionAmount {
        BankAccount fromAccount = findAccount(fromOwner);
        BankAccount toAccount = findAccount(toOwner);

        //Error: Cannot transfer to or from CreditAccounts
        if (fromAccount instanceof CreditAccount || toAccount instanceof CreditAccount) {
            throw new ErrorInvalidTransactionAmount("Transfers involving credit accounts are not supported.");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        return true;
    }

    /**
     * @brief Display all account information in the banking system
     */
    public void displayAccountInfo() {
        for (BankAccount account : accounts) {
            System.out.println("Account type: " + account.getClass().getSimpleName());
            account.displayOwner();
            account.displayAmount();

            if (account instanceof InterestFeature) {
                InterestFeature interestAccount = (InterestFeature) account;
                System.out.println("Interest amount: $" + interestAccount.interestAmount());
            }

            System.out.println();
        }
    }
}