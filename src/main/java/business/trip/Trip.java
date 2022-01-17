package business.trip;

import java.math.BigDecimal;
import java.util.List;

public class Trip {
	private List<Day> days;
	private BigDecimal price;
	
	public Trip(List<Day> days) {
		this.days = days;
		this.price = computePrice(days);
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public List<Day> getDays() {
		return days;
	}
	
	private BigDecimal computePrice(List<Day> days) {
		return days.stream().map(Day::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
