package business.trip.transports;

import java.math.BigDecimal;

public class AquaticTransport extends Transport{
	
	public AquaticTransport() {
		super(null, null, 0);
	}
	
	public AquaticTransport(String name, BigDecimal pricePerKm, int speed) {
		super(name, pricePerKm, speed);
	}
	
	public boolean canCrossSea() {
		return true;
	}
}
