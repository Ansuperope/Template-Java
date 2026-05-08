/**
 * @file Main.java
 * @brief simulates a banking system
 * 
 * @details
 * Will create 3 accounts:
 *  1. Checking
 *  2. Savings
 *  3. Credit
 * 
 * And store them polymorphically to a banking system / our manage class
 * It will preform a variety of functions such as withdrawing
 * 
 * As it preforms 
 */
package project;

//Abstract base class
import project.accounts.BankAccount;

//Derived classes
import project.accounts.CheckingAccount;
import project.accounts.CreditAccount;
import project.accounts.SavingAccount;

//Manager class
import project.system.BankingSystem;

//Exception classes
import project.system.ErrorAccountNotFound;
import project.system.ErrorLowFunds;
import project.system.ErrorInvalidTransactionAmount;

public class Main {

    public static void main(String[] args) {
        //Creates an instance of the manager class to manage all BankAccount objects
        BankingSystem bank = new BankingSystem();

        //Create account objects for each type of account
        BankAccount checking = new CheckingAccount(1000.0, "Aspen");
        BankAccount savings = new SavingAccount(2000.0, "Hobs", 0.03, 2);
        BankAccount credit = new CreditAccount(0.0, "Christian", 0.05, 1);

        //Store accounts polymorphically
        bank.addAccount(checking);
        bank.addAccount(savings);
        bank.addAccount(credit);

        //Display all current BankAccount objects
        System.out.println("=== Initial Account Information ===");
        bank.displayAccountInfo();

        //Testing functionality of the BankingSystem class
        System.out.println("=== Performing Transactions ===");
        try {
            // OUTPUT BEFORE TRANSFER
            System.out.println("Before Tansactions:");
            System.out.println("\tAspen before: $" + bank.findAccount("Aspen").getBalance());
            System.out.println("\tHobs before: $" + bank.findAccount("Hobs").getBalance());

            // DEPOSITE
            bank.depositToAccount("Aspen", 250.0);
            System.out.println("\nAspen deposit $250:");
            System.out.println("\tAspen: $" + bank.findAccount("Aspen").getBalance());

            // WITHDRAW
            bank.withdrawFromAccount("Hobs", 300.0);
            System.out.println("\nHobs withdraw $300:");
            System.out.println("\tHobs: $" + bank.findAccount("Hobs").getBalance());

            // TRANSFER MONEY
            System.out.println("\nAspen transfering $200 to Hobs");
            bank.transferMoney("Aspen", "Hobs", 200.0);
            System.out.println("\tAspen after: $" + bank.findAccount("Aspen").getBalance());
            System.out.println("\tHobs after: $" + bank.findAccount("Hobs").getBalance());

            //For CreditAccount, withdraw = borrow, deposit = pay back
            System.out.println("\nBefore Tansactions:");
            System.out.println("\tChristian before: $" + bank.findAccount("Christian").getBalance());

            // WITHDRAW
            bank.withdrawFromAccount("Christian", 500.0);
            System.out.println("\nChristian withdraw $500");
            System.out.println("\tChristian: $" + bank.findAccount("Christian").getBalance());

            // DEPOSIT
            bank.depositToAccount("Christian", 200.0);
            System.out.println("\nChristian deposit $200");
            System.out.println("\tChristian: $" + bank.findAccount("Christian").getBalance());


        } catch (ErrorAccountNotFound | ErrorInvalidTransactionAmount | ErrorLowFunds e) {
            System.out.println("Transaction error: " + e.getMessage());
        }

        //Display all current BankAccount objects after transactions
        System.out.println("\n=== Updated Account Information ===");
        bank.displayAccountInfo();

        //Testing custom exception classes
        System.out.println("=== Demonstrating Exceptions ===");

        //Exception 1: Account not found
        try {
            // OUTPUT ALL ACCOUNT OWNERS
            System.out.print("Account Owners: ");
            for (BankAccount account : bank.getAccounts()) {
                System.out.print(account.getAccountOwner() + " ");
            }
            // WITHDRAW
            System.out.println("\nDeposit $100 to Avin");
            bank.depositToAccount("Avin", 100.0);

        } catch (ErrorAccountNotFound | ErrorInvalidTransactionAmount e) {
            System.out.println("Caught ErrorAccountNotFound: " + e.getMessage());
        }

        //Exception 2: Insufficient funds
        try {
            System.out.println("\nAspen: $" + bank.findAccount("Aspen").getBalance());
            System.out.println("Withdraw $5000 from Aspen");
            bank.withdrawFromAccount("Aspen", 5000.0);
        } catch (ErrorAccountNotFound | ErrorInvalidTransactionAmount | ErrorLowFunds e) {
            System.out.println("Caught ErrorLowFunds: " + e.getMessage());
        }

        //Exception 3: Invalid transaction amount
        try {
            System.out.println("\nHobs: $" + bank.findAccount("Hobs").getBalance());
            System.out.println("Deposit -$100 to Hobs");
            bank.depositToAccount("Hobs", -100.0);
        } catch (ErrorAccountNotFound | ErrorInvalidTransactionAmount e) {
            System.out.println("Caught ErrorInvalidTransactionAmount: " + e.getMessage());
        }
    }
}