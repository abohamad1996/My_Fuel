package Entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Sales implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	



	int IDsales;
	String FuelType;
	String Discount;
	LocalDate Date;
	String FormHour;
	String ToHout;
	


	public Sales(int iDsales, String fuelType, String discount, LocalDate date, String formHour, String toHout) {
		super();
		IDsales = iDsales;
		FuelType = fuelType;
		Discount = discount;
		Date = date;
		FormHour = formHour;
		ToHout = toHout;
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



	public LocalDate getDate() {
		return Date;
	}



	public void setDate(LocalDate date) {
		Date = date;
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
	@Override
	public String toString() {
		return "Sales [IDsales="+IDsales+",fuelType=" + FuelType + ", discount=" + Discount +", date="+Date+", from="+FormHour+", to="+ToHout+ "]";
	}
	
	
	
	
	
	
	
}
