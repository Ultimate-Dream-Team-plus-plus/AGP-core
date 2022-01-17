package business.trip;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import business.trip.places.Hotel;

public class Excursion {
	private Hotel departureHotel;
	private Hotel arrivalHotel;
	private List<Ride> rides;
	private BigDecimal price;
	private double comfort;
	private double totalDistance;
	
	public Excursion(Hotel departureHotel, Hotel arrivalHotel, List<Ride> rides, BigDecimal price, double comfort,
			double totalDistance) throws IllegalArgumentException{
		super();
		Objects.requireNonNull(departureHotel, "Objet 'departureHotel' ne doit pas être vide.");
		Objects.requireNonNull(arrivalHotel, "Objet 'arrivalHotel' ne doit pas être vide.");
		Objects.requireNonNull(price, "Objet 'price' ne doit pas être vide.");
		if(departureHotel.getName() == arrivalHotel.getName()) {
			Objects.requireNonNull(rides, "Objet 'rides' ne doit pas être vide.");
			if(rides.isEmpty()) {
				throw new IllegalArgumentException("Liste de 'rides' vide pour une excursion entre deux même hôtels, impossible.");
			}
		}
		this.departureHotel = departureHotel;
		this.arrivalHotel = arrivalHotel;
		this.rides = rides;
		this.price = price;
		this.comfort = rides.stream().mapToDouble(Ride::getComfort).sum();
		this.totalDistance = rides.stream().mapToDouble(Ride::getDistance).sum();
	}
	
	/*private double calculateTotalDistance(List<Ride> rides) {
		double totalDistance = 0.0;
		for(Ride ride : rides) {
			totalDistance += ride.getDistance();
		}
		return totalDistance;
	}*/
	
	/*private double calculateComfort(List<Ride> rides) {
		double comfort = 0.0;
		for(Ride ride: rides) {
			comfort += ride.getComfort();
		}
		return comfort / rides.size();
	}*/
	
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
