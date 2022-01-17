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

	public Ride(Place departure, Place arrival, Transport transport) {
		super();
		this.departure = Objects.requireNonNull(departure, "Object 'departure' cannot be null");
		this.arrival = Objects.requireNonNull(arrival, "Object 'arrival' cannot be null");
		this.transport = Objects.requireNonNull(transport, "Object 'transport' cannot be null");
		this.distance = departure.getPosition().computeDistance(arrival.getPosition());
		this.comfort = calculateComfort();
		this.price = calculatePrice();
	}

	private double calculateComfort() {
		// TODO : find this formula !
		return 0.0;
	}

	private BigDecimal calculatePrice() {
		// Do not take account of the price of the departure, as it is calculated by the
		// previous ride.
		BigDecimal arrivalPrice = arrival.getPrice();
		BigDecimal distancePrice = BigDecimal.valueOf(distance / transport.getPricePerKm().doubleValue()).setScale(2);
		return arrivalPrice.add(distancePrice);
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
