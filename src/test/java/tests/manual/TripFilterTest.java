package tests.manual;

import java.math.BigDecimal;
import java.util.List;

import business.trip.places.Hotel;
import business.trip.places.Site;
import business.trip_finder.filter.FilterParameters;
import business.trip_finder.filter.TripFilter;
import business.trip_finder.filter.TripFilterImpl;
import business.trip_finder.repository.PlacesUnion;
import business.trip_finder.repository.TripRepository;
import mocks.dao.MockHotelDao;
import mocks.dao.MockSiteDao;
import mocks.dao.MockTransportDao;

public class TripFilterTest {

	public static final double PRICE_MIN = 0;
	public static final double PRICE_MAX = 1000;
	public static final int NB_DAYS = 2;
	public static final int NB_EXCURSION = 1;

	public static void main(String[] args) {
		TripRepository tripRepository = new TripRepository(new MockHotelDao(), new MockSiteDao(),
				new MockTransportDao());

		List<Hotel> hotels = tripRepository.findAllHotels();
		List<Site> sites = tripRepository.findRelevantSites("");

		System.out.println(sites.toString());

		PlacesUnion pu1 = new PlacesUnion(hotels, sites);
		System.out.println(pu1.toString());

		FilterParameters fp1 = new FilterParameters(NB_DAYS, NB_EXCURSION, BigDecimal.valueOf(PRICE_MIN),
				BigDecimal.valueOf(PRICE_MAX));
		System.out.println(fp1.toString());

		TripFilter tf = new TripFilterImpl();
		PlacesUnion puRes = tf.filterPlaces(fp1, pu1);
		System.out.println(puRes.toString());
	}

}
