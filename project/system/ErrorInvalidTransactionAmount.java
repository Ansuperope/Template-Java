/**
 * @file ErrorInvalidTransactionAmount.java
 * @brief error message for when user 
 */
package project.system;

public class ErrorInvalidTransactionAmount extends Exception {
    /**
     * @param message error message to output
     * @brief ouptuts error message when sees exception
     */
    public ErrorInvalidTransactionAmount(String message) {
        super(message);
    }

    /**
     * @brief exception message
     */
    public ErrorInvalidTransactionAmount() {
        super("Transaction amount must be greater than 0.0");
    }
}