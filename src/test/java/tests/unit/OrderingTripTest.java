package tests.unit;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import business.trip.Day;
import business.trip.Excursion;
import business.trip.Ride;
import business.trip.Trip;
import business.trip.places.Hotel;
import business.trip.places.Position;
import business.trip.places.Site;
import business.trip.transports.AquaticTransport;
import business.trip.transports.LandTransport;
import business.trip_finder.orderer.OrderingStrategyType;
import business.trip_finder.orderer.TripOrdererImpl;

public class OrderingTripTest {
	private final Hotel hotel1 = new Hotel("Hotel 1", new Position(-17.6394, -149.4229), BigDecimal.valueOf(59.99), List.of());
	private final Hotel hotel2 = new Hotel("Hotel 2", new Position(-17.6394, -149.4229), BigDecimal.valueOf(59.99), List.of());

	private final Site site1 = new Site("site 1", new Position(-17.72566362669506, -149.3196272879307), BigDecimal.ONE, true, false);
	private final Site site2 = new Site("site 2", new Position(-17.677911831785384, -149.40700292313335), BigDecimal.ONE, true, false);
	private final Site site3 = new Site("site 3", new Position(-17.665317633718615, -149.3091559454819), BigDecimal.ONE, true, false);

	private final LandTransport autobus = new LandTransport("Autobus", BigDecimal.valueOf(0.10), 50, 3.1);
	private final LandTransport foot = new LandTransport("Feet", BigDecimal.valueOf(0), 7,2.0);
	private final AquaticTransport boat = new AquaticTransport("Boat", BigDecimal.valueOf(0.20), 70,4.6);

	private final List<Ride> rides = List.of(
			new Ride(hotel1, site1, boat),
			new Ride(site1, site2, autobus),
			new Ride(site2, hotel2, foot)
			);

	private final List<Ride> rides2 = List.of(
			new Ride(hotel1, site2, autobus),
			new Ride(site3, site1, foot),
			new Ride(site1, hotel2, boat)
			);

	private final List<Ride> rides3 = List.of(
			new Ride(hotel1, site1, boat),
			new Ride(site1, site2, autobus),
			new Ride(site2, hotel1, foot)
			);

	private final List<Excursion> excursions = List.of(
			new Excursion(hotel1,hotel2,rides)
			);

	private final List<Excursion> excursions2 = List.of(
			new Excursion(hotel1,hotel2,rides2)
			);

	private final List<Excursion> excursions3 = List.of(
			new Excursion(hotel1,hotel1,rides3)
			);

	private final List<Day> days = List.of( 
			new Day(excursions, 1),
			new Day(excursions2, 2),
			new Day(excursions3, 3)
			);

	private final List<Day> days2 = List.of( 
			new Day(excursions2, 1),
			new Day(excursions3, 2),
			new Day(excursions, 3)
			);

	private final List<Day> days3 = List.of( 
			new Day(excursions3, 1),
			new Day(excursions, 2),
			new Day(excursions2, 3)
			);

	private final List<Trip> trips = List.of(
			new Trip(days),
			new Trip(days2)
			);

	private final List<Trip> trips2 = List.of(
			new Trip(days2),
			new Trip(days3)
			);

	private final List<Trip> trips3 = List.of(
			new Trip(days3),
			new Trip(days2),
			new Trip(days)
			);
	@Test
	public void sortedListTripsComAsc() {
		TripOrdererImpl toi = new TripOrdererImpl();
		List<Trip> orderedList = toi.orderTrips(trips3, OrderingStrategyType.COMFORT_ASCENDING);
		//assertTripsEqual(orderedList, List.of());
		double comfort_days = days.get(0).getComfort() + days.get(1).getComfort() + days.get(2).getComfort();
		double comfort_days2 = days2.get(0).getComfort() + days2.get(1).getComfort() + days2.get(2).getComfort();
		double comfort_days3 = days3.get(0).getComfort() + days3.get(1).getComfort() + days3.get(2).getComfort();
		System.out.println("comfort_days: " + comfort_days);
		System.out.println("comfort_days2: " + comfort_days2);
		System.out.println("comfort_days3: " + comfort_days3);
	}
	
	@Test
	public void sortedListTripsComDesc() {
		TripOrdererImpl toi = new TripOrdererImpl();
		List<Trip> orderedList = toi.orderTrips(trips3, OrderingStrategyType.COMFORT_DECSCENDING);
		assertTripsEqual(orderedList, List.of());
	}
	
	@Test
	public void sortedListTripsPriceAsc() {
		TripOrdererImpl toi = new TripOrdererImpl();
		List<Trip> orderedList = toi.orderTrips(trips3, OrderingStrategyType.PRICE_ASCENDING);
		assertTripsEqual(orderedList, List.of());
	}
	
	@Test
	public void sortedListTripsPriceDesc() {
		TripOrdererImpl toi = new TripOrdererImpl();
		List<Trip> orderedList = toi.orderTrips(trips3, OrderingStrategyType.PRICE_DECSCENDING);
		assertTripsEqual(orderedList, List.of());
	}
	
	@Test
	public void sortedListTripsGlobal() {
		TripOrdererImpl toi = new TripOrdererImpl();
		List<Trip> orderedList = toi.orderTrips(trips3, OrderingStrategyType.GLOBAL);
		assertTripsEqual(orderedList, List.of());
	}
	
	private void assertTripsEqual(List<Trip> orderedTrips, List<Trip> expectedTrips) {
		for(int index = 0; index < orderedTrips.size(); index++) {
			Trip orderedTrip = orderedTrips.get(index);
			Trip expectedTrip = expectedTrips.get(index);
			
			assertEquals(orderedTrip.getPrice(), expectedTrip.getPrice());
		}
	}
}
