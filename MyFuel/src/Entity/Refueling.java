package Entity;

import java.io.Serializable;

public class Refueling  implements Serializable{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String CarNumber;
	String GasStation;
	String address;
	String GasType;
	String RateForLiter;
	String Qunatity;
	String Price;
	String Date;
	String pumpNumber;
	String rate;
	




	


	public Refueling(String carNumber, String gasStation, String address, String gasType, String rateForLiter,
			String qunatity, String price, String date, String pumpNumber, String rate) {
		super();
		CarNumber = carNumber;
		GasStation = gasStation;
		this.address = address;
		GasType = gasType;
		RateForLiter = rateForLiter;
		Qunatity = qunatity;
		Price = price;
		Date = date;
		this.pumpNumber = pumpNumber;
		this.rate = rate;
	}












	@Override
	public String toString() {
		return "Refueling [CarNumber=" + CarNumber + ", GasStation=" + GasStation + ", address=" + address
				+ ", GasType=" + GasType + ", RateForLiter=" + RateForLiter + ", Qunatity=" + Qunatity + ", Price="
				+ Price + ", Date=" + Date + ", pumpNumber=" + pumpNumber + ", rate=" + rate + "]";
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












	public String getAddress() {
		return address;
	}












	public void setAddress(String address) {
		this.address = address;
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












	public String getRate() {
		return rate;
	}












	public void setRate(String rate) {
		this.rate = rate;
	}












	public static long getSerialversionuid() {
		return serialVersionUID;
	}












	public double Calculate_Price_Refueling(double quan, double rate) {
		return 0;
	}
	
}
