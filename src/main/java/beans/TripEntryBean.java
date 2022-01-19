package beans;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import business.trip.Trip;
import business.trip_finder.TripParameters;
import business.trip_finder.best_trip_finder.BestTripFinder;
import mocks.MockBestTripFinder;


@ManagedBean
@SessionScoped
public class TripEntryBean {
	private Exception error;
	private List<Trip> bestTrips;
	private TripParameters tripParameters = new TripParameters();
	private BestTripFinder bestTripFinder=  new MockBestTripFinder();
	

	
	public TripEntryBean() {
	}
	
	public String search() {
		try {
			tripParameters.validateParameters();
		} catch (Exception e) {
			setError(e);
			return "error";
		}
		bestTrips = bestTripFinder.findBestTrips(tripParameters);
		return "result";
	}

	
	public Exception getError() {
		return error;
	}

	public void setError(Exception error) {
		this.error = error;
	}

	public List<Trip> getBestTrips() {
		return bestTrips;
	}

	public void setBestTrips(List<Trip> bestTrips) {
		this.bestTrips = bestTrips;
	}

	public TripParameters getTripParameters() {
		return tripParameters;
	}

	public void setTripParameters(TripParameters tripParameters) {
		this.tripParameters = tripParameters;
	}

	public BestTripFinder getBestTripFinder() {
		return bestTripFinder;
	}

	public void setBestTripFinder(BestTripFinder bestTripFinder) {
		this.bestTripFinder = bestTripFinder;
	}
	
	public BigDecimal getMinPrice() {
		return tripParameters.getMinPrice();
	}
	
	public void setMinPrice(BigDecimal minPrice) {
		tripParameters.setMinPrice(minPrice);
	}
	
	public BigDecimal getMaxPrice() {
		return tripParameters.getMaxPrice();
	}
	
	public void setMaxPrice(BigDecimal maxPrice) {
		tripParameters.setMaxPrice(maxPrice);
	}
	
	public Double getComfort() {
		return tripParameters.getComfort();
	}

	public void setComfort(Double comfort) {
		tripParameters.setComfort(comfort);
	}

	public String getKeywords() {
		return tripParameters.getKeywords();
	}

	public void setKeywords(String keywords) {
		 tripParameters.setKeywords(keywords);;
	}

	public Integer getNbDays() {
		return tripParameters.getNbDays();
	}

	public void setNbDays(Integer nbDays) {
		tripParameters.setNbDays(nbDays);
	}

	public String getFilterBy() {
		return tripParameters.getFilterBy();
	}

	public void setFilterBy(String filterBy) {
		tripParameters.setFilterBy(filterBy);
	}
	

}
