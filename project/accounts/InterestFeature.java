/**
 * @file InterestFeature.java
 * 
 * @brief interface for accounts that have interest
 * 
 * This is an interface class that will be given to accounts that will
 * be dealing with interest such as the SavingAccount and CreditAccount classes
 */
package project.accounts;

public interface InterestFeature {
    /**
     * @brief will calculate the interest an account will have
     * 
     * Will be defined in the derived class
     */
    double interestAmount();
}