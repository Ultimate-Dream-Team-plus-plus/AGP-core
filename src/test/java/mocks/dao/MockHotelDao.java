package mocks.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import business.trip.places.Hotel;
import business.trip.places.Position;
import dao.HotelDao;

public class MockHotelDao implements HotelDao {

	private final List<Hotel> hotels = List.of(
			new Hotel("Hotel 1", new Position(-17.6394, -149.4229), BigDecimal.valueOf(59.99), 2, List.of()),
			new Hotel("Hotel 2", new Position(-16.6394, -148.2323), BigDecimal.valueOf(172.99), 3, List.of()),
			new Hotel("Hotel 3", new Position(-18.6394, -150.4229), BigDecimal.valueOf(30.0), 4, List.of()),
			new Hotel("Hotel 4", new Position(-17.0112, -149.1111), BigDecimal.valueOf(29.99), 5, List.of()),
			new Hotel("Hotel 5", new Position(-17.1111, -150.4229), BigDecimal.valueOf(333.33), 1, List.of()),
			new Hotel("Hotel 6", new Position(-17.3333, -148.4229), BigDecimal.valueOf(72.49), 1, List.of()));

	@Override
	public Iterator<Hotel> findAll() {
		return hotels.iterator();
	}

	@Override
	public Iterator<Hotel> findWithMinimum(double minimumComfort) {
		return hotels.stream()
				.filter(withMinComfort(minimumComfort))
				.iterator();
	}

	@Override
	public Iterator<Hotel> findWithMinimum(BigDecimal minimumPrice) {
		return hotels.stream()
				.filter(withMinPrice(minimumPrice))
				.iterator();
	}

	@Override
	public Iterator<Hotel> findWithMinimum(BigDecimal minimumPrice, double minimumComfort) {
		return hotels.stream()
				.filter(withMinComfort(minimumComfort))
				.filter(withMinPrice(minimumPrice))
				.iterator();
	}

	private Predicate<? super Hotel> withMinComfort(double minimumComfort) {
		return h -> h.getComfort() > minimumComfort;
	}

	private Predicate<? super Hotel> withMinPrice(BigDecimal minimumPrice) {
		return h -> h.getPrice().compareTo(minimumPrice) >= 0;
	}
}
