package business.trip;

import java.math.BigDecimal;
import java.util.List;

public class Trip {
	private List<Day> days;
	
	public Trip() {
		days = null;
	}

	public Trip(List<Day> days) {
		this.days = days;
	}
	
	public List<Day> getDays() {
		return days;
	}
	
	private BigDecimal computePrice() {
		return null;
	}

}
