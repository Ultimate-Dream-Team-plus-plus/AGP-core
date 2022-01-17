package business.trip;

import java.math.BigDecimal;
import java.util.Objects;

import business.trip.places.Place;
import business.trip.transports.Transport;

public class Ride {
	private Place departure;
	private Place arrival;
	private Transport transport;
	private BigDecimal price;
	private double distance;
	private double comfort;
	
	public Ride(Place departure, Place arrival, Transport transport, BigDecimal price, double comfort) {
		super();
		Objects.requireNonNull(price, "Objet 'price' ne doit pas être null");
		Objects.requireNonNull(departure, "Objet 'departure' ne doit pas être null");
		Objects.requireNonNull(arrival, "Objet 'arrival' ne doit pas être null");
		Objects.requireNonNull(transport, "Objet 'transport' ne doit pas être null");

		if(price.compareTo(new BigDecimal(0)) < 0) {
			throw new IllegalArgumentException("Prix incorrect, valeur inférieure à 0.");
		}
		
		this.departure = departure;
		this.arrival = arrival;
		this.transport = transport;
		this.distance = departure.getPosition().computeDistance(arrival.getPosition());
		this.comfort = comfort;
		this.price = calculatePrice(this.distance, this.transport);
	}
	
	private BigDecimal calculatePrice(double distance, Transport transport) {
		return new BigDecimal(distance/transport.getPricePerKm().doubleValue());
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
