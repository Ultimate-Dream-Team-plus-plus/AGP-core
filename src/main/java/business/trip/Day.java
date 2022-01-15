package business.trip;

import java.math.BigDecimal;
import java.util.List;

public class Day {
	private List<Excursion> excursions;
	private int date;
	private double comfort;
	private BigDecimal price;
	
	public Day() {
		super();
		this.excursions = null;
		this.date = 0;
		this.comfort = 0.0;
		this.price = null;
	}
	
	public Day(List<Excursion> excursions, int date, double comfort, BigDecimal price) {
		super();
		this.excursions = excursions;
		this.date = date;
		this.comfort = comfort;
		this.price = price;
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
