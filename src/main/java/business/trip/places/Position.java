package business.trip.places;

public class Position {
	private double latitude;
	private double longitude;
	
	public Position() {
		this.latitude = 0.0;
		this.longitude = 0.0;
	}
	
	public Position(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	private double computeDistance(Position other) {
		//TODO ComputeDistance algorithm
		return 0.0;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	
}
