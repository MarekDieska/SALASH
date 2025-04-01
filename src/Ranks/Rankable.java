package Ranks;

/**
 * obligates the rank classes which implement this interface to return a rank string
 * @author Marek Dieska
 */

public interface Rankable {
	/**
	 * returns rank on request
	 * @return rank string from requested rank class
	 */
    String getRank();
}