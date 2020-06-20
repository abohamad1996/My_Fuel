package Entity;

import java.io.Serializable;
/**
*This class represents the maximum price for the fuel
*that determinated by Representative Transport
*Every rate Characterized by fuelType, price
*
*/
public class Rates implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String fuelType;
	String price;

	/**
	* constructor and initializes object of type Rates
	*
	* @param fuelType fuel type
	* @param price maximum price 
	*/
	
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
	/**
	*This method object of type maxbuy  
	*/
	@Override
	public String toString() {
		return "Rates [fuelType=" + fuelType + ", price=" + price + "]";
	}
	
	




	
}
