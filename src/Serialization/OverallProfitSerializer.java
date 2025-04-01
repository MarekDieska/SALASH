package Serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * this function serializes the overall made profit from the auction, as the overall profit only the difference between
 * the object's price and the balance paid counts
 * @author Marek Dieska
 */

public class OverallProfitSerializer {

	/**
	 * this function serializes the overall profit made to overall_profit.out file
	 * @param profit overall profit made
	 */
    public static void serializeOverallProfit(int profit) {
        try {
            FileOutputStream fileOut = new FileOutputStream("overall_profit.out");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(profit);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * this function loads the overall profit that is saved in the overall_profit.out file
     * @return overall profit made or 0
     */
    public static int LoadOverallProfit() {
        try {
            FileInputStream fileIn = new FileInputStream("overall_profit.out");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            int profit = (int)in.readObject();
            in.close();
            return profit;
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}