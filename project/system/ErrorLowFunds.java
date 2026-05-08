/**
 * @file ErrorLowFunds.java
 * @brief exception class for when account doesn't have enough funds
 */
package project.system;

public class ErrorLowFunds extends Exception {
    /**
     * @param message error message to output
     * @brief ouptuts error message when sees exception
     */
    public ErrorLowFunds(String message){
        super(message);
    }

    /**
     * @brief error message to output
     */
    public ErrorLowFunds(){
        super("Insufficient funds to support operation");
    }
}