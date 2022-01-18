package business.trip;

import java.math.BigDecimal;
import java.util.List;

import business.trip.places.Hotel;

public class Day {
	private Hotel startHotel;
	private List<Excursion> excursions;
	private int date;
	private double comfort;
	private BigDecimal price;

	public Day(List<Excursion> excursions, int date) {
		super();
		this.excursions = excursions;
		this.startHotel = excursions.get(0).getDepartureHotel();
		this.date = date;
		this.comfort = excursions.stream()
				.mapToDouble(Excursion::getComfort)
				.sum();
		this.price = excursions.stream()
				.map(Excursion::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	/**
	 * A day with no excursions, only resting.
	 */
	public Day(Hotel hotel, int date) {
		this.excursions = List.of();
		this.startHotel = hotel;
		this.date = date;
		this.comfort = hotel.getComfort();
		this.price = hotel.getPrice();
	}

	public List<Excursion> getExcursions() {
		return excursions;
	}

	public int getDate() {
		return date;
	}

	public double getComfort() {
		return comfort;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Hotel getStartHotel() {
		return startHotel;
	}

}
