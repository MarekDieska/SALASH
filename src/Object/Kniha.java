package Object;
import java.util.Random;

/**
 * the purpose of this class is to create a Book object in the auction, the object is then sold on the auction,
 * this class generates the overall condition, price and prints out the info about the object
 * @author Marek Dieska
 */
public class Kniha extends PredmetAukcie {
    private final String[] VYROBA = {"Original", "Podpisana", "Rucne pisana", "Ozdobena", "Tlacena"};
    private final String[] KONDICIA = {"nove", "ako nove", "velmi dobre zachovane", "dobre zachovane", "zachovane",
            "slabo zachovane", "velmi slabo zachovane", "mierne poskodene", "poskodene", "velmi poskodene"};
    private final String[] VYZOR = {"majstrovske dielo", "schopna ovplyvnit", "povinna literatura", "kupenia hodna", "obstojna", "nevyrazna"};
    private final double[] VYZOR_WEIGHTS = {0.05, 0.1, 0.15, 0.2, 0.2, 0.3};

    private String vyroba;
    private int cenaVyroby;
    private String kondicia;
    private String vyzor;
    

    /**
     * the book construcor, creates the book's condition by calling functions determining the state of the book
     */
    public Kniha() {
        super();
        this.vyzor = vyberNahodnyVyzor();
        this.vyroba = vyberNahodnuVyrobu();
        this.kondicia = vyberNahodnuKondiciu();
        this.cenaVyroby = getCenaVyroby(this.vyroba);
        int totalCena = (int) (this.cenaVyroby * getCenaKondicie(this.kondicia) * getCenaVyzoru(vyzor));
        setCena(totalCena);
    }
    
    /**
     * chooses a random appearance of the book from the array of appearances, the appearance also has weights, so 
     * some weights are more common than the other
     * @return the appearance of the book
     */
    private String vyberNahodnyVyzor() {
        Random random = new Random();
        double totalWeight = 0.0;
        for (double weight : VYZOR_WEIGHTS) {
            totalWeight += weight;
        }
        double randomValue = random.nextDouble() * totalWeight;
        for (int i = 0; i < VYZOR.length; i++) {
            randomValue -= VYZOR_WEIGHTS[i];
            if (randomValue <= 0.0) {
                return VYZOR[i];
            }
        }
        return VYZOR[VYZOR.length - 1];
    }
    
    /**
     * chooses a random condition of the book from the array of conditions
     * @return random condition as a string
     */
    private String vyberNahodnuKondiciu() {
        Random random = new Random();
        int index = random.nextInt(KONDICIA.length);
        return KONDICIA[index];
    }

    /**
     * chooses a random type of creation of the book from the array of conditions
     * @return random type of creation as a string
     */
    private String vyberNahodnuVyrobu() {
        Random random = new Random();
        int index = random.nextInt(VYROBA.length);
        return VYROBA[index];
    }
    

    /**
     * this function determines the price of the book based on the type of creation of the book
     * @param vyroba the type of creation
     * @return the price of creating the book
     */
    private int getCenaVyroby(String vyroba) {
    	int defaultCVyroby = switch(vyroba) {
    	case "Original" -> 6000;
    	case "Podpisana" -> 2000;
    	case"Rucne pisana" -> 1500;
    	case "Ozdobena" -> 800;
    	case "Tlacena" -> 100;
        default -> 0;
    	};
    	return defaultCVyroby;
    }
    
    /**
     * determines the coeficient that the actual price will be multiplied by
     * @param vyzor the appearance of the book
     * @return the coeficient by which the actual price will be multiplied by
     */
    private int getCenaVyzoru(String vyzor)
    {
    	int cenaVyzoru = switch(vyzor) {
	    	case "majstrovske dielo" -> 10;
	    	case "schopna ovplyvnit" -> 6;
	    	case "povinna literatura" -> 5;
	    	case "kupenia hodna" -> 3;
	    	case "obstojna" -> 2;
	    	case "nevyrazna" -> 1;
	    	default -> 0;
    	};	
    	return cenaVyzoru;
    }

    /**
     * this function prints the info about the book
     */
    @Override
    public String vypisInfo() {
        return "Typ: Kniha\n" +
               "Styl vyroby: " + vyroba + "\n" +
               "Cena vyroby: " + cenaVyroby + "\n" +
               "Kondicia: " + kondicia + "\n" +
               "Vyzor: " + vyzor + "\n" +
               "Cena: ???" + /*getCena() +*/ "\n";
    }
}