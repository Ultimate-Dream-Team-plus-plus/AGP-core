package business.trip.transports;

import java.math.BigDecimal;

public abstract class Transport {

	private String name;
	private BigDecimal pricePerKm;
	private int speed;
	private double comfort;
	
	public Transport(String name, BigDecimal pricePerKm, int speed, double comfort) {
		this.name = name;
		this.pricePerKm = pricePerKm;
		this.speed = speed;
		this.comfort = comfort;
		
		if(name.isBlank()) {
			throw new IllegalArgumentException("Empty transport name not allowed");
		}
		
		if (speed <= 0.0) {
			throw new IllegalArgumentException("Speed cannot be negative");
		}
		
		if(pricePerKm.signum() == -1) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
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

	public double getComfort() {
		return comfort;
	}
	
}
