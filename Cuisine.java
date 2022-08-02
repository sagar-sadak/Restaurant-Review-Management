import java.io.Serializable;

public class Cuisine implements Serializable {
	// private variables
    private static final long serialVersionUID = 205L; 
    private String signatureDish;
    private String name;

    // constructor
    public Cuisine(String signatureDish, String name) {
        this.name = name;
        this.signatureDish = signatureDish;
    }

    // getter
    public String getName() {
        return name;
    }

    @Override
    //toString
    public String toString() {
        return name + " Cuisine\n" +
                "Signature Dish:\t" + signatureDish + '\n';
    }
}
