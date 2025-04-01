package Object;
import java.util.Random;

/**
 * @author Marek Dieska
 */

public class Socha extends PredmetAukcie {
    private final String[] MATERIALY = {"mramor", "bronz", "kamen", "drevo", "terakota"};
    private final String[] KONDICIA = {"nove", "ako nove", "velmi dobre zachovane", "dobre zachovane", "zachovane",
            "slabo zachovane", "velmi slabo zachovane", "mierne poskodene", "poskodene", "velmi poskodene"};
    private final String[] VYZOR = {"majstrovske dielo", "fascinujuca", "oslnujuca", "kupenia hodna", "obstojna", "nevyrazna"};
    private final double[] VYZOR_WEIGHTS = {0.05, 0.1, 0.15, 0.2, 0.2, 0.3};
    private String material;
    private int cenaMaterialu;
    private String kondicia;
    private String vyzor;

    /**
     * the statue construcor, creates the statue's condition by calling functions determining the state of the statue
     */
    public Socha() {
        super();
        this.vyzor = vyberNahodnyVyzor();
        this.material = vyberNahodnyMaterial();
        this.kondicia = vyberNahodnuKondiciu();
        this.cenaMaterialu = getCenaMaterialu(this.material);
        int totalCena = (int) (this.cenaMaterialu * getCenaKondicie(this.kondicia) * getCenaVyzoru(vyzor));
        setCena(totalCena);
    }
    
    /**
     * chooses a random appearance from the appearance array, the appearance also has weights, so 
     * some weights are more common than the other
     * @return the appearance of the statue as a string
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
     * chooses a random condition of the painting from the condition array
     * @return the condition of the statue
     */
    private String vyberNahodnuKondiciu() {
        Random random = new Random();
        int index = random.nextInt(KONDICIA.length);
        return KONDICIA[index];
    }

    /**
     * this function chooses a random material that the statue is made of from the array of the materials
     * @return the material that the statue is made of
     */
    private String vyberNahodnyMaterial() {
        Random random = new Random();
        int index = random.nextInt(MATERIALY.length);
        return MATERIALY[index];
    }
    
    /**
     * this function returns the price of the statue based on the material that the statue is made of
     * @param material the randomly created material that the statue is made of
     * @return price of the material
     */
    private int getCenaMaterialu(String material) {
    	int defaultCMaterialu = switch(material) {
	    	case "mramor" -> 6000;
	    	case "bronz" -> 3000;
	    	case "kamen" -> 2000;
	    	case "drevo" -> 800;
	    	case "terakota" -> 500;
	    	default -> 0;
    	};
    	return defaultCMaterialu;
    }
    
    /**
     * this function returns a multiplier that the actual price will be multiplied by based on the appearance of the statue
     * @param vyzor appearance of the statue
     * @return multiplier that the actual price will be multiplied by
     */
    private int getCenaVyzoru(String vyzor)
    {
    	int cenaVyzoru = switch(vyzor) {
    	case "majstrovske dielo" -> 10;
    	case "fascinujuca" -> 6;
    	case"oslnujuca" -> 5;
    	case"kupenia hodna" -> 3;
    	case "obstojna" -> 2;
    	case "nevyrazna" -> 1;
    	default -> 0;
    	};
    	return cenaVyzoru;
    }
    
    
    /**
     * prints the info about the randomly generated statue
     */
    @Override
    public String vypisInfo() {
        return "Typ: Socha\n" +
               "Material: " + material + "\n" +
               "Cena materialu: " + cenaMaterialu + "\n" +
               "Kondicia: " + kondicia + "\n" +
               "Vyzor: " + vyzor + "\n" +
               "Cena: ???" + /*getCena() +*/ "\n";
    }
}