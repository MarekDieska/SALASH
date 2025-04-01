package Strategy;

/**
 * this function is the part of strategy design pattern and it calculates the bot limit that the bot is willing to pay
 * @author Marek Dieska
 */

interface BotLimitStrategy {
	/**
	 * this function calculates the amount that he bot is willing to pay, it obligates the bot to calculate his max limit
	 * that he is willing to pay
	 * @param itemPrice the price of the item
	 * @return maximum limit that the bot will pay
	 */
    int calculateLimit(int itemPrice);
}