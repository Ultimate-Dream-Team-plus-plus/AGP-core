package business.trip.transports;

import java.math.BigDecimal;

public class LandTransport extends Transport{
	public LandTransport() {
		super(null, null, 0);
	}
	
	public LandTransport(String name, BigDecimal pricePerKm, int speed) {
		super(name, pricePerKm, speed);
	}
	
	public boolean canCrossSea() {
		return false;
	}
}
