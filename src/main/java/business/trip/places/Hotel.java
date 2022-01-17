package business.trip.places;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel extends Place {

	private double comfort;
	private int nbPrestation;
	private List<String> prestations;

	public Hotel(String name, Position position, BigDecimal price, double comfort, List<String> prestations) throws IllegalArgumentException{
		super(name, position, price, true, false);
		
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
