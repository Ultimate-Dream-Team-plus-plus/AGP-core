package business.trip.places;

import java.math.BigDecimal;

public abstract class Place {
	private String name;
	private Position position;
	private BigDecimal price;
	private boolean isSeaSided;
	
	public Place() {
		this.name = null;
		this.position = null;
		this.price = null;
		this.isSeaSided = false;
	}
	
	public Place(String name, Position position, BigDecimal price, boolean isSeaSided) {
		super();
		this.name = name;
		this.position = position;
		this.price = price;
		this.isSeaSided = isSeaSided;
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
	
}
