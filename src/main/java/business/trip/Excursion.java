package business.trip;

import java.math.BigDecimal;
import java.util.List;

import business.trip.places.Hotel;

public class Excursion {
	private Hotel departureHotel;
	private Hotel arrivalHotel;
	private List<Ride> rides;
	private BigDecimal price;
	private double comfort;
	private double totalDistance;
	
	public Excursion() {
		super();
		this.departureHotel = null;
		this.arrivalHotel = null;
		this.rides = null;
		this.price = null;
		this.comfort = 0.0;
		this.totalDistance = 0.0;
	}
	
	public Excursion(Hotel departureHotel, Hotel arrivalHotel, List<Ride> rides, BigDecimal price, double comfort,
			double totalDistance) {
		super();
		this.departureHotel = departureHotel;
		this.arrivalHotel = arrivalHotel;
		this.rides = rides;
		this.price = price;
		this.comfort = comfort;
		this.totalDistance = totalDistance;
	}

	public Hotel getDepartureHotel() {
		return departureHotel;
	}

	public Hotel getArrivalHotel() {
		return arrivalHotel;
	}

	public List<Ride> getRides() {
		return rides;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public double getComfort() {
		return comfort;
	}

	public double getTotalDistance() {
		return totalDistance;
	}

	
}
