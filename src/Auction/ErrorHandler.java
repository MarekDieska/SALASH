package Auction;
import javax.swing.JOptionPane;

/**
 * This class pops out error messages
 * @author Marek Dieska
 */

public class ErrorHandler {
	/**
	 * this function shows an error message when called
	 * @param message the error message that is requested to be printed based on the error
	 */
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}