package business.trip.places;

public class Hotel extends Position {
	private int nbPrestation;

	public Hotel() {
		super();
		this.nbPrestation = 0;
	}

	public Hotel(double latitude, double longitude) {
		super(latitude, longitude);
	}

	public double computeConfort() {
		//TODO Compute confort algorithm
		return 0.0;
	}
	
	public int getNbPrestation() {
		return nbPrestation;
	}
	
}
