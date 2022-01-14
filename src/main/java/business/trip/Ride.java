package business.trip;

import java.math.BigDecimal;

import business.trip.places.Place;
import business.trip.transports.Transport;

public class Ride {
	private Place departure;
	private Place arrival;
	private Transport transport;
	private BigDecimal price;
	private double distance;
	private double comfort;
	
	public Ride() {
		super();
		this.departure = null;
		this.arrival = null;
		this.transport = null;
		this.price = null;
		this.distance = 0.0;
		this.comfort = 0.0;
	}
	
	public Ride(Place departure, Place arrival, Transport transport, BigDecimal price, double distance,
			double comfort) {
		super();
		this.departure = departure;
		this.arrival = arrival;
		this.transport = transport;
		this.price = price;
		this.distance = distance;
		this.comfort = comfort;
	}

	public Place getDeparture() {
		return departure;
	}

	public Place getArrival() {
		return arrival;
	}

	public Transport getTransport() {
		return transport;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public double getDistance() {
		return distance;
	}

	public double getComfort() {
		return comfort;
	}

}
