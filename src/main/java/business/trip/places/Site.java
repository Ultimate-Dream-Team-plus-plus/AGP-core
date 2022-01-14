package business.trip.places;

import java.math.BigDecimal;

public class Site extends Place {

	public Site() {
		super();
	}

	public Site(String name, Position position, BigDecimal price, boolean isSeaSided) {
		super(name, position, price, isSeaSided);
	}
	
}
