package Entity;

import java.io.Serializable;

public class Refueling  implements Serializable{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String CarNumber;
	String GasStation;
	String GasType;
	String RateForLiter;
	String Qunatity;
	String Price;
	String Date;
	String pumpNumber;
	
	

	
	
	public Refueling(String carNumber, String gasStation, String gasType, String rateForLiter, String qunatity,
			String price, String date, String pumpNumber) {
		super();
		CarNumber = carNumber;
		GasStation = gasStation;
		GasType = gasType;
		RateForLiter = rateForLiter;
		Qunatity = qunatity;
		Price = price;
		Date = date;
		this.pumpNumber = pumpNumber;
	}










	@Override
	public String toString() {
		return "Refueling [CarNumber=" + CarNumber + ", GasStation=" + GasStation + ", GasType=" + GasType
				+ ", RateForLiter=" + RateForLiter + ", Qunatity=" + Qunatity + ", Price=" + Price + ", Date=" + Date
				+ ", pumpNumber=" + pumpNumber + "]";
	}










	public String getCarNumber() {
		return CarNumber;
	}










	public void setCarNumber(String carNumber) {
		CarNumber = carNumber;
	}










	public String getGasStation() {
		return GasStation;
	}










	public void setGasStation(String gasStation) {
		GasStation = gasStation;
	}










	public String getGasType() {
		return GasType;
	}










	public void setGasType(String gasType) {
		GasType = gasType;
	}










	public String getRateForLiter() {
		return RateForLiter;
	}










	public void setRateForLiter(String rateForLiter) {
		RateForLiter = rateForLiter;
	}










	public String getQunatity() {
		return Qunatity;
	}










	public void setQunatity(String qunatity) {
		Qunatity = qunatity;
	}










	public String getPrice() {
		return Price;
	}










	public void setPrice(String price) {
		Price = price;
	}










	public String getDate() {
		return Date;
	}










	public void setDate(String date) {
		Date = date;
	}










	public String getPumpNumber() {
		return pumpNumber;
	}










	public void setPumpNumber(String pumpNumber) {
		this.pumpNumber = pumpNumber;
	}










	public double Calculate_Price_Refueling(double quan, double rate) {
		return 0;
	}
	
}
