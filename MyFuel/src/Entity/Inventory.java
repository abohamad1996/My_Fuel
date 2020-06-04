package Entity;

import java.io.Serializable;

public class Inventory implements Serializable {

	String fuel_Type;
	Integer Quantity;
	Integer thresholdLevel;
	public Inventory(String fuel_Type, Integer quantity, Integer thresholdLevel) {
		super();
		this.fuel_Type = fuel_Type;
		Quantity = quantity;
		this.thresholdLevel = thresholdLevel;
	}
	public String getFuel_Type() {
		return fuel_Type;
	}
	public void setFuel_Type(String fuel_Type) {
		this.fuel_Type = fuel_Type;
	}
	public Integer getQuantity() {
		return Quantity;
	}
	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}
	public Integer getThresholdLevel() {
		return thresholdLevel;
	}
	public void setThresholdLevel(Integer thresholdLevel) {
		this.thresholdLevel = thresholdLevel;
	}
	

}
