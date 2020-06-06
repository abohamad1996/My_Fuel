package Entity;

import java.io.Serializable;

public class Inventory implements Serializable {

	@Override
	public String toString() {
		return "Inventory [fuelType=" + fuelType + ", Quantity=" + Quantity + ", Level=" + Level + "]";
	}
	String fuelType;
	String Quantity;
	String Level;
	public Inventory(String fuelType, String quantity, String level) {
		super();
		this.fuelType = fuelType;
		Quantity = quantity;
		Level = level;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}

}
