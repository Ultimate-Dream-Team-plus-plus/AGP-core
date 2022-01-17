package business.trip;

import java.math.BigDecimal;
import java.util.List;

public class Trip {
	private List<Day> days;
	private BigDecimal price;
	private double comfort;
	
	public Trip(List<Day> days) {
		this.days = days;
		this.price = computePrice(days);
		this.comfort = computeComfort(days);
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public List<Day> getDays() {
		return days;
	}
	
	public double getComfort() {
		return comfort;
	}

	private BigDecimal computePrice(List<Day> days) {
		return days.stream().map(Day::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	private double computeComfort(List<Day> days) {
		return days.stream().mapToDouble(Day::getComfort).sum() / days.size();
	}

}
