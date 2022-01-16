package business.trip_finder.path_finder;

import java.util.List;

public class BruteForcePathFinder implements PathFinder {

	@Override
	public PlacesPath findPath(PlacesInput placesInput) {
		return new PlacesPath(null, List.of(), null);
	}

}
