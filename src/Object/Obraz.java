package Object;
import java.util.Random;

/**
 * @author Marek Dieska
 */

public class Obraz extends PredmetAukcie {
    private final String[] FARBY = {"olejove farby", "vysivany", "akrylove farby", "akvarelove farby", "pastelove farby"};
    private final String[] KONDICIA = {"nove", "ako nove", "velmi dobre zachovane", "dobre zachovane", "zachovane",
            "slabo zachovane", "velmi slabo zachovane", "mierne poskodene", "poskodene", "velmi poskodene"};
    private final String[] VYZOR = {"majstrovske dielo", "fascinujuci", "oslnujuci", "kupenia hodny", "obstojny", "nevyrazny"};
    private final double[] VYZOR_WEIGHTS = {0.05, 0.1, 0.15, 0.2, 0.2, 0.3};

    private String farba;
    private int cenaFarby;
    private String kondicia;
    private String vyzor;
    
    /**
     * the painting construcor, creates the painting's condition by calling functions determining the state of the painitng
	*/
    public Obraz() {
    	super();
        this.vyzor = vyberNahodnyVyzor();
        this.farba = vyberNahodnuFarbu();
        this.kondicia = vyberNahodnuKondiciu();
        this.cenaFarby = getCenaFarby(this.farba);
        int totalCena = (int) (this.cenaFarby * getCenaKondicie(this.kondicia) * getCenaVyzoru(vyzor));
        setCena(totalCena);
	}

    /**
     * chooses a random appearance of the painting from the array of appearances, the appearance also has weights, so 
     * some weights are more common than the other
     * @return the appearance of the painting
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
     * chooses a random condition of the painting from the array of conditions
     * @return random condition as a string
     */
    private String vyberNahodnuKondiciu() {
        Random random = new Random();
        int index = random.nextInt(KONDICIA.length);
        return KONDICIA[index];
    }

    /**
     * chooses a random painting style from the array of painting styles
     * @return the style of the painting as a string
     */
    private String vyberNahodnuFarbu() {
        Random random = new Random();
        int index = random.nextInt(FARBY.length);
        return FARBY[index];
    }
    

    /**
     * this function returns the price of the painting based on the style of painting used
     * @param farba the painting style of the painting
     * @return the price of creating the painitng
     */
    private int getCenaFarby(String farba) {
    	int cenaFarby = switch(farba) {
	    	case "olejove farby" -> 2000;
	    	case "vysivany" -> 1200;
	    	case "akrylove farby" -> 600;
	    	case "akvarelove farby" -> 300;
	    	case "pastelove farby" -> 100;
	        default -> 0;
    	};
        return cenaFarby;
    }
    
    /**
     * based on the appearance this function appoints a multiplier that will multiply the actual price
     * @param vyzor the appearance of the painting
     * @return the multiplier of the actual price
     */
    private int getCenaVyzoru(String vyzor) {
    	int cenaKond = switch(vyzor) {
	    	case "majstrovske dielo" -> 10;
	    	case "fascinujuci" -> 6;
	    	case "oslnujuci" -> 5;
	    	case "kupenia hodny" -> 3;
	    	case "obstojny" -> 2;
	    	case "nevyrazny" -> 1;
			default -> 0;
    	};
    	return cenaKond;
    }

    /**
     * prints the info about the randomly generated painting
     */
    @Override
    public String vypisInfo() {
        return "Typ: Obraz\n" +
               "Styl vyroby: " + farba + "\n" +
               "Cena vyroby: " + cenaFarby + "\n" +
               "Kondicia: " + kondicia + "\n" +
               "Vyzor: " + vyzor + "\n" +
               "Cena: ???" + /*getCena() +*/ "\n";
    }
}