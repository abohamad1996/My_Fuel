package Entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Sales implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String FuelType;
	String Discount;
	LocalDate Date;
	String FormHour;
	String ToHout;
	
	public Sales(String fuelStringType, String discount, LocalDate date, String formHour, String toHout) {
		super();
		this.FuelType = fuelStringType;
		Discount = discount;
		Date = date;
		FormHour = formHour;
		ToHout = toHout;
	}

	public String getFuelStringType() {
		return FuelType;
	}

	public void setFuelStringType(String fuelStringType) {
		this.FuelType = fuelStringType;
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
		return "Sales [fuelStringType=" + FuelType + ", Discount=" + Discount + ", Date=" + Date + ", FormHour="
				+ FormHour + ", ToHout=" + ToHout + "]";
	}
	
	
	
	
	
	
	
}
