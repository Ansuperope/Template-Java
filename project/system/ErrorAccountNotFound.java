/**
 * @file ErrorAccountNotFound.java
 * @brief exception class for when account is not found
 */
package project.system;

public class ErrorAccountNotFound extends Exception {
    /**
     * @param message error message to output
     * @brief ouptuts error message when sees exception
     */
    public ErrorAccountNotFound(String message){
        super(message);
    }

    /**
     * @brief exception message
     */
    public ErrorAccountNotFound(){
        super("Account not found");
    }
}