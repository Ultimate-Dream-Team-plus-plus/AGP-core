package business.trip;

import java.math.BigDecimal;
import java.util.List;

public class Day {
	private List<Excursion> excursions;
	private int date;
	private double comfort;
	private BigDecimal price;

	public Day(List<Excursion> excursions, int date) {
		super();
		this.excursions = excursions;
		this.date = date;
		this.comfort = excursions.stream().mapToDouble(Excursion::getComfort).sum();
		this.price = excursions.stream().map(Excursion::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public List<Excursion> getExcursions() {
		return excursions;
	}

	public int getDate() {
		return date;
	}

	public double getComfort() {
		return comfort;
	}

	public BigDecimal getPrice() {
		return price;
	}

}
