package Ranks;

/**
 * this class returns a string that will be written where rank is written, more precisely Pomocnik rank
 * this class implements class Rankable, that's why they return the rank string
 * @author Marek Dieska
 */

public class Pomocnik implements Rankable {
	
	/**
	 * this function returns the actual string "Pomocnik"
	 */
	public String getRank() {
		return "Pomocnik";
	}
}