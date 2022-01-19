package business.trip_finder;

import java.math.BigDecimal;
import java.util.Objects;

import business.trip_finder.orderer.OrderingStrategyType;
import business.trip_finder.rater.RatingStrategyType;

public class TripParameters {

	private BigDecimal minPrice = BigDecimal.valueOf(1000.00);

	private BigDecimal maxPrice = BigDecimal.valueOf(2000.00);

	// nullable
	private Double comfort = 2.5;

	// can be empty ?
	private String keywords = "";

	private Integer nbDays = 2;
	
	private String filterBy = "GLOBAL";

	public TripParameters() {
		
	}

	public TripParameters(BigDecimal minPrice, BigDecimal maxPrice, Double comfort, String keywords, Integer nbDays, String filterBy) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.comfort = comfort;
		this.keywords = keywords;
		this.nbDays = nbDays;
		this.filterBy = filterBy;
	}

	/**
	 * Checks if all parameters corresponds to the domain.
	 * <p>
	 * Example : {@code minPrice < maxPrice}
	 * 
	 * @throws IllegalArgumentException, NullPointerException
	 */
	public void validateParameters() throws IllegalArgumentException, NullPointerException {
		// Check nulls
		Objects.requireNonNull(minPrice, "minPrice should get a value");
		Objects.requireNonNull(maxPrice, "maxPrice should get a value");
		Objects.requireNonNull(nbDays, "nbDays should get a value");
		
		// More domain-related checks
		// minPrice <= maxPrice
		if (minPrice.compareTo(maxPrice) == 1) {
			throw new IllegalArgumentException("maximum price must be greater or equal than minimum price");
		}
		
		// 0 <= comfort <= 5
		if (comfort != null && (comfort < 0.0 || comfort > 5.0)) {
			throw new IllegalArgumentException("Comfort rate must be between 0 and 5");
		}
		
		// Not a negative number of days, or a too high value
		if (nbDays <= 0) {
			throw new IllegalArgumentException("The number of days must be at least be 1");
		}
		
		if (nbDays > 20) {
			throw new IllegalArgumentException("The number of days must not be too high (max : 20)");
		}
		
		// filterBy value must correspond to any RatingStrategyType value
		// This will throw an exception if it is not the case
		OrderingStrategyType value = OrderingStrategyType.valueOf(filterBy);
	}
	
	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getComfort() {
		return comfort;
	}

	public void setComfort(Double comfort) {
		this.comfort = comfort;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getNbDays() {
		return nbDays;
	}

	public void setNbDays(Integer nbDays) {
		this.nbDays = nbDays;
	}

	public String getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}
}
