package business.trip.places;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Hotel extends Place {
	private int nbPrestation;
	private List<String> prestations = new ArrayList<>();
	
	public Hotel() {
		super();
		this.nbPrestation = 0;
	}

	public Hotel(String name, Position position, BigDecimal price, boolean isSeaSided, List<String> prestations) {
		super(name, position, price, isSeaSided);
		this.nbPrestation = prestations.size();
		this.prestations = prestations;
	}

	public double computeConfort() {
		//TODO Compute confort algorithm
		return 0.0;
	}
	
	public int getNbPrestation() {
		return nbPrestation;
	}

	public List<String> getPrestations() {
		return prestations;
	}
	
}
