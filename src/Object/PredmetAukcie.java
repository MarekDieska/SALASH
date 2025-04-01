package Object;
import java.util.Random;

/**
 * @author Marek Dieska
 */

public abstract class PredmetAukcie {
    private int cena;
    private String kondicia;

    /**
     * the constructor of the PredmetAukcie class, here the final coeficient of the objects condition is created
     */
    public PredmetAukcie() {
    }

    /**
     * this function returns price of the object in auction
     * @return price of the object
     */
    public int getCena() {
        return cena;
    }

    /**
     * this function determines the final price of the object in auction
     * @param totalCena final price of the object
     */
    public void setCena(int totalCena) {
        this.cena += totalCena;
    }

    /**
     * this function returns the condition of the object in auction
     * @return condition of the object
     */
    public String getKondicia() {
        return kondicia;
    }

    /**
     * sets the kondicia of the object for this function to know what it's working with
     * @param kondicia
     */
    public void setKondicia(String kondicia) {
        this.kondicia = kondicia;
    }
    
    /**
     * generates a random coeficient depending on the condition of the object
     * @param kondicia condition of the object
     * @return coeficient that the actual price will be multiplied by
     */
    public double getCenaKondicie(String kondicia) {
        Random random = new Random();
        double koefKondicie = switch(kondicia) {
        case "nove" -> random.nextDouble() * 0.1 + 0.9;
        case "ako nove" -> random.nextDouble() * 0.1 + 0.8;
        case "velmi dobre zachovane" -> random.nextDouble() * 0.1 + 0.7;
        case "dobre zachovane" -> random.nextDouble() * 0.1 + 0.6;
        case "zachovane" -> random.nextDouble() * 0.1 + 0.5;
        case "slabo zachovane" -> random.nextDouble() * 0.1 + 0.4;
        case "velmi slabo zachovane" -> random.nextDouble() * 0.1 + 0.3;
        case "mierne poskodene" -> random.nextDouble() * 0.1 + 0.2;
        case "poskodene" -> random.nextDouble() * 0.1 + 0.1;
        case "velmi poskodene" -> random.nextDouble() * 0.1 + 0.05;
		default -> 0;
        };
        System.out.println(koefKondicie);
        return koefKondicie;
    }

    /**
     * prints the info about the object
     * @return info about the object
     */
    public abstract String vypisInfo();	
}