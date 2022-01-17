package business.trip.places;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel extends Place {

	private double comfort;
	private int nbPrestation;
	private List<String> prestations = new ArrayList<>();

	public Hotel(String name, Position position, BigDecimal price, float comfort, List<String> prestations) throws IllegalArgumentException{
		super(name, Objects.requireNonNull(position, "Objet 'position' ne doit pas être null"), 
				Objects.requireNonNull(price, "Objet 'price' ne doit pas être null"), true, false);
		
		if(price.compareTo(new BigDecimal(0)) == -1) {
			throw new IllegalArgumentException("Prix incorrect, valeur inférieure à 0.");
		}
		
		if(name.isBlank()) {
			throw new IllegalArgumentException("Nom d'hotel est vide.");
		}
		
		this.nbPrestation = prestations.size();
		this.prestations = prestations;
		this.comfort = comfort;
	}

	public int getNbPrestation() {
		return nbPrestation;
	}

	public List<String> getPrestations() {
		return prestations;
	}

	public double getComfort() {
		return comfort;
	}
}
