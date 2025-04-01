package Serialization;

import java.io.Serializable;

/**
 * this function counts the overall profit made and saves it to the variable that will be serialized if the save button
 * is pressed
 * @author Marek Dieska
 */

public class OverallProfit implements Serializable {
    private int profit = 0;

    /**
     * this is the overall profit made serializer
     * @param profit overall profit made so far also with the just made profit
     */
    public OverallProfit(int profit) {
        this.profit = profit;
    }

    /**
     * this function returns overall profit made also with the just made profit
     * @return overall profit made
     */
    public int getProfit() {
        return profit;
    }
    
    /**
     * adds the newly made profit to the overall profit made
     * @param newProfit newly made profit
     */
    public void setProfit(int newProfit) {
    	profit += newProfit;
    }
}