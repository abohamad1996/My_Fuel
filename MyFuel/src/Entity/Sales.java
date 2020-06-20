package Entity;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
/**
*This class represents sale operation
*Every sale Characterized by IDsales, FuelType, Discount, FromHour,
*ToHour, status
*
*/

public class Sales implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	int IDsales;
	String FuelType;
	String Discount;
	String FormHour;
	String ToHout;
	int Status;
	
	
	
	/**
	*  constructor and initializes object of type sales
	*
	* @param iDsales sale ID
	* @param fuelType fuel type discount
	* @param discount Percentage of discount
	* @param formHour start hour
	* @param toHout end hour
	* @param status sale status
	*/

	public Sales(int iDsales, String fuelType, String discount, String formHour, String toHout, int status) {
		super();
		IDsales = iDsales;
		FuelType = fuelType;
		Discount = discount;
		FormHour = formHour;
		ToHout = toHout;
		Status = status;
	}


	public int getIDsales() {
		return IDsales;
	}
	public void setIDsales(int iDsales) {
		IDsales = iDsales;
	}
	public String getFuelType() {
		return FuelType;
	}
	public void setFuelType(String fuelType) {
		FuelType = fuelType;
	}
	public String getDiscount() {
		return Discount;
	}
	public void setDiscount(String discount) {
		Discount = discount;
	}
	public String getFormHour() {
		return FormHour;
	}
	public void setFormHour(String formHour) {
		FormHour = formHour;
	}
	public String getToHout() {
		return ToHout;
	}
	public void setToHout(String toHout) {
		ToHout = toHout;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}

	/**
	*This method object of type sales  
	*/

	@Override
	public String toString() {
		return "Sales [IDsales=" + IDsales + ", FuelType=" + FuelType + ", Discount=" + Discount + ", FormHour="
				+ FormHour + ", ToHout=" + ToHout + ", Status=" + Status + "]";
	}
	
	
	
}
