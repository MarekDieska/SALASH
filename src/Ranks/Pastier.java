package Ranks;

/**
 * this class returns a string that will be written where rank is written, more precisely Pastier rank
 * this class implements class Rankable, that's why they return the rank string
 * @author Marek Dieska
 */

public class Pastier implements Rankable {
	
	/**
	 * this function returns the actual string "Pastier"
	 */
	public String getRank() {
		return "Pastier";
	}
}