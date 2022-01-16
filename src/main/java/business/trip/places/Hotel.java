package business.trip.places;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Hotel extends Place {

	private double comfort;
	private int nbPrestation;
	private List<String> prestations = new ArrayList<>();

	public Hotel() {
		super();
	}

	public Hotel(String name, Position position, BigDecimal price, boolean isSeaSided, List<String> prestations) {
		super(name, position, price, isSeaSided);
		this.nbPrestation = prestations.size();
		this.prestations = prestations;
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
