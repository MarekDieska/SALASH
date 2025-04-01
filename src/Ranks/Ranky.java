package Ranks;

/**
 * this class determines the player rank based on his so far made overall profit
 * @author Marek Dieska
 */

public class Ranky {
	/**
	 * this function determines which string the rank classes should return based on the player's
	 * so far made profit
	 * @param balance the overall profit made so far
	 * @return string of the rank based on his overall profit made
	 */
    public String getRank(double balance) {
        if (balance >= 25000) {
            return new Baca().getRank();
        } else if (balance >= 15000) {
            return new Tovaris().getRank();
        } else if (balance >= 5000) {
            return new Pastier().getRank();
        } else {
            return new Pomocnik().getRank();
        }
    }
}