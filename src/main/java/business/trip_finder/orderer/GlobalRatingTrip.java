package business.trip_finder.orderer;

import business.trip.Trip;

public class GlobalRatingTrip {
	private double rate;
	private Trip trip;
	
	public GlobalRatingTrip(double rate, Trip trip) {
		this.rate = rate;
		this.trip = trip;
	}

	public double getRate() {
		return rate;
	}

	public Trip getTrip() {
		return trip;
	}
	
}
