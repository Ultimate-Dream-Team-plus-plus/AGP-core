package mocks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.trip.Day;
import business.trip.Excursion;
import business.trip.Ride;
import business.trip.Trip;
import business.trip.places.Hotel;
import business.trip.places.Position;
import business.trip.places.Site;
import business.trip.transports.AquaticTransport;
import business.trip.transports.LandTransport;
import business.trip_finder.TripParameters;
import business.trip_finder.best_trip_finder.BestTripFinder;

public class MockBestTripFinder implements BestTripFinder {

	@Override
	public List<Trip> findBestTrips(TripParameters parameters) {		
		
		
		Hotel hotel1 = new Hotel("Hotel 1", new Position(-17.6394, -149.4229), BigDecimal.valueOf(59.99), true, List.of());
		Hotel hotel2 = new Hotel("Hotel 2", new Position(-17.6394, -149.4229), BigDecimal.valueOf(59.99), true, List.of());
		
		Site site1 = new Site("Site 1", new Position(-17.6394, -149.4229), BigDecimal.valueOf(59.99), true);
		Site site2 = new Site("Site 2", new Position(-16.6394, -148.2323), BigDecimal.valueOf(172.99), false);
		Site site3 = new Site("Site 3", new Position(-18.6394, -150.4229), BigDecimal.valueOf(30.0), false);
		
		LandTransport autobus = new LandTransport("Autobus", BigDecimal.valueOf(0.10), 50);
		LandTransport feet = new LandTransport("Feet", BigDecimal.valueOf(0), 7);
		AquaticTransport boat = new AquaticTransport("Boat", BigDecimal.valueOf(0.20), 70);
		
		
		List<Ride> rides = List.of(
				new Ride(hotel1, site1, boat, BigDecimal.valueOf(50), 5, 4.5),
				new Ride(site1, hotel2, autobus, BigDecimal.valueOf(40), 5, 4.0)
		);
		
		List<Excursion> excursions = List.of(
					new Excursion(hotel1,hotel2,rides,BigDecimal.valueOf(90.30),10,4.25)
				);
		
		List<Day> days = List.of( 
					new Day(excursions, 15, 4.25,BigDecimal.valueOf(210))
				);
		
		List<Trip> trips = List.of(
				new Trip(days),
				new Trip(days),
				new Trip(days)
 		);
		
		return trips;
	}

}
