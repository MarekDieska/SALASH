package Observer;

import Serialization.OverallProfit;
import Serialization.OverallProfitSerializer;

/**
 * this class holds the information about the player's balance and overall profit
 * @author Marek Dieska
 */

public class Hrac {
    private int balance;
	private int overallProfit;
	private int finalProfit;

	/**
	 * the player constructor, sets the actual balance of the player
	 * @param balance player's actual balance
	 */
    public Hrac(int balance) {
        this.balance = balance;
    }

    /**
     * this function gets the player's actual balance
     * @return actual player's balance
     */
    public int getBalance() {
        return balance;
    }
    
    /**
     * this is part of the serialization, it loads the saved overall profit as the actual overall profit
     * @param savedProfit player's serialized overall profit
     */
    public void setOverallProfit(int savedProfit) {
        overallProfit = savedProfit;
    }
    
    /**
     * adds the profit that player just made to his actual overall profit 
     * @param op just made profit by the player after buying an object in the auction
     */
    public void addOverallProfit(int op) {
    	overallProfit += op;
    }
    
    /**
     * this function returns the actual overall profit
     * @return the actual overall profit
     */
    public int getOverallProfit() {
    	return overallProfit;
    }
    
    /**
     * this function adds the object price to the player's balance
     * @param amount the auction object's price
     */
    public void addBalance(int amount) {
        balance += amount;
    }

    /**
     * this function determines the change in the player's balance
     * @param amount how much player paid for the object in auction
     * @param profit how much the bought item cost
     * @param op profit made from the object
     * @return the difference between the object price and the amount that player paid
     */
    public int deductBalance(int amount, int profit, OverallProfit op) {
        balance -= amount;
        balance += profit;
        finalProfit = profit - amount;
        op.setProfit(finalProfit);
        addOverallProfit(finalProfit);
        OverallProfitSerializer.serializeOverallProfit(getOverallProfit());
        return finalProfit;
    }
}