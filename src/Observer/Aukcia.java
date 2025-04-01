package Observer;
import java.util.ArrayList;
import java.util.List;

import Ranks.Ranky;
import Serialization.OverallProfit;
import Strategy.Bot;

/**
 * This class is the controller of the auction, most of the logic happening in the class AukciaGUI happens here.
 * @author Marek Dieska 
 */

public class Aukcia implements Subject {
    private Hrac hrac;
    private Ranky ranky;
    private ArrayList<Observer> observers;
    private List<Bot> bots; 
    private OverallProfit overallProfit;

    /**
     * Aukcia class constructor
     * @param op the player's overall profit made through the whole game
     * @param h the player's information
     */
    public Aukcia(OverallProfit op, Hrac h) {
        hrac = h;
        ranky = new Ranky();
        this.observers = new ArrayList<>();
        this.bots = new ArrayList<>();
        overallProfit = op;
    }

    /**
     * notifies the observers about the actual balance
     * @param playerBalance the actual player balance
     */
	public void setPlayerBalance(double playerBalance) {
        notifyObservers();
    }

	/**
	 * notifies the observers about the actual player rank
	 * @param playerRank actual player rank
	 */
    public void setPlayerRank(String playerRank) {
        notifyObservers();
    }

    /**
     * this function adds profit to player's balance
     * @param newBalance profit from the bought object
     * @return account balance
     */
    public int addBalance(int newBalance) {
        hrac.addBalance(newBalance);
        return hrac.getBalance();
    }

    /**
     * adds the made profit from the bought auction object to the player's balance
     * @param amount the amount that the player paid for the object
     * @param profit the price that the object costs
     * @return profit - amount
     */
    public int deductBalance(int amount, int profit) {
        return hrac.deductBalance(amount, profit, overallProfit);
    }

    /**
     * gets the player's actual balance
     * @return the player's actual balance
     */
    public int getPlayerBalance() {
        return hrac.getBalance();
    }

    /**
     * calculates the player's rank based on his overall profit made
     * @return player's rank
     */
    public String getPlayerRank() {
        return ranky.getRank(hrac.getOverallProfit());
    }

    /**
     * adds observers to the Observer design pattern
     * @param observer observes changes in player balance and bot behaviour
     */
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * removes observers from the Observer design pattern
     * @param observer observes changes in the player balance and bot behaviour
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    /**
     * notifies observers about the player balance and bot behaviour
     */
    public void notifyObservers() {
    	observers.forEach(Observer::update);
    }
    
    /**
     * adds bot to the bots array where bots are stored
     * @param bot basically the player's opponent in the auction
     */
    public void addBot(Bot bot) {
        bots.add(bot);
    }
    
    /**
     * returns the array of the bots
     * @return bots in the array
     */
    public List<Bot> getBots() {
        return bots;
    }
}