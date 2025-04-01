package Observer;

import Strategy.Bot;

/**
 * the observer interface, when called, it updates the player balance and, checks if bot bid
 * @author Marek Dieska
 */
public interface Observer {
	/**
	 * this function changes the player balance on the screen
	 */
    void update();

    /**
     * this function is called when bot accepts a bid and determines his behaviour this is the view part of the code, this is the main GUI, the main window of the program
     * @param bot the bot in the auction who has his maximum limit that he is willing to pay
     * @param payment the amount that the player put in the payment input field
     */
	void onBidAccepted(Bot bot, int payment);
}