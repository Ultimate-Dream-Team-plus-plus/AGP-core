package business.trip_finder.orderer.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import business.trip.Trip;
import business.trip_finder.orderer.OrderingStrategy;

public class GlobalStrategy implements OrderingStrategy{

	public GlobalStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Trip> orderTrip(List<Trip> trips) {
		Map<Double, Trip> map = new HashMap<Double, Trip>();
		double price, comfort, rate;
		double maxPrice = trips.stream().max(Comparator.comparing(Trip::getPrice)).get().getPrice().doubleValue();

		for(Trip trip: trips) {
			price = trip.getPrice().doubleValue();
			comfort = trip.getComfort();
			rate = (price / maxPrice) * 0.5 + (comfort / 5) * 0.5;
			map.put(rate, trip);
		}
		
		/*map.entrySet()
		.stream()
		.sorted(Map.Entry.<Double, Trip>comparingByKey())
		.forEach(System.out::println);*/
		
		map = map.entrySet()
				.stream()
				.sorted(Map.Entry.<Double, Trip>comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> newValue, LinkedHashMap::new));
		
		List<Trip> orderedTrips = new ArrayList<Trip>(map.values());
				
		return orderedTrips;
	}

}
