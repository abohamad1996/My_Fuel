package Entity;

import java.io.Serializable;
/**
*This class represents Inventory for management
*Every inventory Characterized by fuelType, Quantity, Level
*
*/
public class Inventory implements Serializable {
	/**
	*This method print invoentory details for each fuel type
	*/

	@Override
	public String toString() {
		return "Inventory [fuelType=" + fuelType + ", Quantity=" + Quantity + ", Level=" + Level + "]";
	}
	String fuelType;
	String Quantity;
	String Level;
	/**
	* constructor and initializes object of type inventory
	*
	* @param fuelType fuel type name
	* @param quantity of available inventory
	* @param level Threshold level
	*
	*/
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
