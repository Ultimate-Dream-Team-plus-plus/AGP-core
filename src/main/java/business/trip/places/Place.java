package business.trip.places;

import java.math.BigDecimal;

public abstract class Place {
	private String name;
	private Position position;
	private BigDecimal price;
	private boolean isSeaSided;
	private boolean isIntoSea;
	
	public Place(String name, Position position, BigDecimal price, boolean isSeaSided, boolean isIntoSea) {
		super();
		this.name = name;
		this.position = position;
		this.price = price;
		this.isSeaSided = isSeaSided;
		this.isIntoSea = isIntoSea;
	}
	
	public String getName() {
		return name;
	}

	public Position getPosition() {
		return position;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public boolean isSeaSided() {
		return isSeaSided;
	}

	public boolean isIntoSea() {
		return isIntoSea;
	}
	
}
