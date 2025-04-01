package Observer;

/**
 * this class manages observers
 * @param observer serves for checking auction process: bot behaviour and changes in player's balance
 * @author Marek Dieska
 */
interface Subject {
	/**
	 * adds an observer to the observer design pattern
	 * @param observer checks the bot behaviour
	 */
    void addObserver(Observer observer);
    /**
     * removes an observer from the observer design pattern
     * @param observer checks the bot behaviour
     */
    void removeObserver(Observer observer);
    /**
     * notifies observers in the observer design pattern about changes in bot behaviour
     */
    void notifyObservers();
}