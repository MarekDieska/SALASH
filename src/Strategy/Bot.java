package Strategy;
import Observer.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this is the bot class, here are determined the bot max limits based on their difficulty and price of the object
 * sold on the auctions
 * @author Marek Dieska
 */

public class Bot {
    private static final String[] botTypes = {"Expert", "Intermediate", "Beginner"};

    private String name;
    private int payment;
    private int botLimit, cena;
    private List<Observer> observers;
    public BotLimitStrategy limitStrategy;
    private ActionListener bl;
    
    /**
     * bot class constructor
     * @param itemPrice the price of the item being sold in the auction
     */
    public Bot(int itemPrice) {
        Random rand = new Random();
        this.name = botTypes[rand.nextInt(botTypes.length)];
        this.cena = itemPrice;
        this.botLimit = createLimitStrategy();
        this.observers = new ArrayList<>();
    }
    
    class ExpertBot implements BotLimitStrategy {
    	private int botLimit;
    	/**
    	 * this function calculates the max limit for the expert bot
    	 * @param price of the object that is being sold in the auction
    	 */
    	@Override
        public int calculateLimit(int itemPrice) {
            Random rand = new Random();
            botLimit = (int) (itemPrice * (0.6 + rand.nextDouble() * 0.45));
            System.out.println(itemPrice);
            return botLimit;
        }
        /**
         * this function returns the max limit for the expert bot
         * @return maximum price that the bot is willing to pay for the object
         */
        public int getBotLimit() {
        	return botLimit;
        }
    }
    
    
    class IntermediateBot implements BotLimitStrategy {
    	private int botLimit;
    	/**
    	 * this function calculates the max limit for the intermediate bot
    	 * @param price of the object that is being sold in the auction
    	 */
    	@Override
        public int calculateLimit(int itemPrice) {
            Random rand = new Random();
            botLimit = (int) (itemPrice * (0.6 + rand.nextDouble() * 0.25));
            return botLimit;
        }
        /**
         * this function returns the max limit for the intermediate bot
         * @return maximum price that the bot is willing to pay for the object
         */
        public int getBotLimit() {
        	return botLimit;
        }
    }
    
    class BeginnerBot implements BotLimitStrategy {
    	private int botLimit;
    	/**
    	 * this function calculates the max limit for the beginner bot
    	 * @param price of the object that is being sold in the auction
    	 */
    	@Override
        public int calculateLimit(int itemPrice) {
            Random rand = new Random();
            botLimit = (int) (itemPrice * (0.5 + rand.nextDouble() * 0.25));
            return botLimit;
        }
        /**
         * this function returns the max limit for the beginner bot
         * @return maximum price that the bot is willing to pay for the object
         */
        public int getBotLimit() {
        	return botLimit;
        }
    }
    
    class UnknownBot implements BotLimitStrategy {
    	private int botLimit;
    	/**
    	 * this function calculates the max limit for the unknown bot, this function should never be called
    	 * @param price of the object that is being sold in the auction
    	 */
    	@Override
        public int calculateLimit(int itemPrice) {
            Random rand = new Random();
            botLimit = (int) (itemPrice * (10 + rand.nextDouble() * 1));
            return botLimit;
        }
        /**
         * this function returns the max limit for the unknown bot
         * @return maximum price that the bot is willing to pay for the object
         */
        public int getBotLimit() {
        	return botLimit;
        }
    }

    /**
     * this function returns the max limit of any bot that has been created
     * @return maximum amount that the bot is willing to pay
     */
    public int getBotLimit() {
        return botLimit;
    }

    /**
     * this function returns the difficulty of the bot
     * @return bot difficulty
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the payment offered by the bot and notifies the observers.
     * @param payment the payment offered by the bot
     */
    public void setPayment(int payment) {
        this.payment = payment;
        notifyObservers();
    }

    /**
     * Adds an observer to the bot.
     * @param observer the observer to be added
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    
    /**
     * Notifies all observers about the bid acceptance.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.onBidAccepted(this, payment);
        }
    }

    /**
     * calls the right functions to create the bot max limit that he is willing to pay
     * @return the max limit bot is willing to pay
     */
    private int createLimitStrategy() {
        switch (name) {
            case "Expert":
                ExpertBot expertBot = new ExpertBot();
                botLimit = expertBot.calculateLimit(cena);
                return botLimit;
            case "Intermediate":
                IntermediateBot intermediateBot = new IntermediateBot();
                botLimit = intermediateBot.calculateLimit(cena);
                return botLimit;
            case "Beginner":
                BeginnerBot beginnerBot = new BeginnerBot();
                botLimit = beginnerBot.calculateLimit(cena);
                return botLimit;
            default:
            	UnknownBot uBot = new UnknownBot();
                botLimit = uBot.calculateLimit(cena);
                return botLimit;
        }
    }

    
    /**
     * Sets the bid listener for the bot.
     * @param listener the bid listener to be set
     */
    public void setBidListener(ActionListener listener) {
        this.bl = listener;
    }

    /**
     * Checks if the current payment is lower than the bot's maximum limit.
     * If it is, triggers the bid listener.
     * @return 1 if the bid is accepted, 0 otherwise
     */
    public int checkBid() {
        if (payment <= botLimit) {
            if (bl != null) {
                ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "BidAccepted");
                bl.actionPerformed(event);
            }
            System.out.println(botLimit);
            return 1;
        }
        else {
            return 0;
        }
    }
}