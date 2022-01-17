package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import business.trip.places.Hotel;
import business.trip.places.Position;
import business.trip.places.Site;
import business.trip_finder.path_finder.BruteForcePathFinder;
import business.trip_finder.path_finder.PathFinder;
import business.trip_finder.path_finder.PlacesInput;
import business.trip_finder.path_finder.PlacesPath;

/**
 * From a collection of places, the exact same path must be found
 * 
 * @author Aldric Vitali Silvestre
 */
public class PathFinderTest {

	private final Hotel hotel1 = new Hotel("hotel 1", new Position(-17.694747550059795, -149.30387262201194), BigDecimal.ONE, 3.0, List.of());
	private final Hotel hotel2 = new Hotel("hotel 2", new Position(-17.612798763579153, -149.30250756193706), BigDecimal.ONE, 3.0, List.of());

	// Not ordered, in order to see if the algorithm do something
	// Right order : site 3 -> site 1 -> site 2
	private final List<Site> sites = List.of(
			new Site("site 1", new Position(-17.663651800317613, -149.30960750311587), BigDecimal.ONE, true, false),
			new Site("site 2", new Position(-17.644143300628134, -149.3114408741488), BigDecimal.ONE, true, false),
			new Site("site 3", new Position(-17.682284810066125, -149.30517685645302), BigDecimal.ONE, true, false));

	private PathFinder finder = new BruteForcePathFinder();

	@Test
	public void findRightPath2Hotels() {
		PlacesInput in = new PlacesInput(hotel1, sites, hotel2);
		PlacesPath path = finder.findPath(in);
		PlacesPath target = new PlacesPath(hotel1, List.of(sites.get(2), sites.get(0), sites.get(1)), hotel2);
		assertPathsEqual(path, target);
	}

	@Test
	public void findRightPath1Hotel() {
		PlacesInput in = new PlacesInput(hotel1, sites, hotel1);
		PlacesPath path = finder.findPath(in);
		PlacesPath target = new PlacesPath(hotel1, List.of(sites.get(2), sites.get(0), sites.get(1)), hotel1);
		assertPathsEqual(path, target);
	}

	private void assertPathsEqual(PlacesPath toTest, PlacesPath target) {
		assertEquals(target.getDepartureHotel(), toTest.getDepartureHotel());
		assertEquals(target.getArrivalHotel(), toTest.getArrivalHotel());

		assertThat(toTest.getSitesBetween(), Matchers.contains(target.getSitesBetween()));
	}
}
