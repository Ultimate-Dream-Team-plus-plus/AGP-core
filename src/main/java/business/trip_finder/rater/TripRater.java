package business.trip_finder.rater;

import business.trip.Trip;

public interface TripRater {

	double rateTrip(Trip trip, RatingStrategyType type);
}
