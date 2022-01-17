package business.trip.transports;

import java.math.BigDecimal;

public abstract class Transport {

	private String name;
	private BigDecimal pricePerKm;
	private int speed;
	
	public Transport(String name, BigDecimal pricePerKm, int speed) {
		this.name = name;
		this.pricePerKm = pricePerKm;
		this.speed = speed;
	}
	
	public abstract boolean canCrossSea();

	public String getName() {
		return name;
	}

	public BigDecimal getPricePerKm() {
		return pricePerKm;
	}

	public int getSpeed() {
		return speed;
	}
	
}
