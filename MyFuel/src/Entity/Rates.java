package Entity;

import java.io.Serializable;

public class Rates implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String fuelType;
	String price;

	
	
	public Rates(String fuelType, String price) {
		super();
		this.fuelType = fuelType;
		this.price = price;

	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Rates [fuelType=" + fuelType + ", price=" + price + "]";
	}
	
	




	
}
