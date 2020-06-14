package Entity;

import java.io.Serializable;
import java.sql.Date;
/**
 * 
 * 
 *
 */

public class Dulkan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String clientID;
	String GasStation;
	String GasType;
	Integer CarNumber;
	Double RateForLiter;
	Integer quantity;
	Double Price;
	Date dateTimeDate;
	
	public Dulkan(String clientID, String gasStation, String gasType, Integer carNumber, Double rateForLiter,
			Integer quantity, Double price, Date dateTimeDate) {
		super();
		this.clientID = clientID;
		GasStation = gasStation;
		GasType = gasType;
		CarNumber = carNumber;
		RateForLiter = rateForLiter;
		this.quantity = quantity;
		Price = price;
		this.dateTimeDate = dateTimeDate;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
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

	public Integer getCarNumber() {
		return CarNumber;
	}

	public void setCarNumber(Integer carNumber) {
		CarNumber = carNumber;
	}

	public Double getRateForLiter() {
		return RateForLiter;
	}

	public void setRateForLiter(Double rateForLiter) {
		RateForLiter = rateForLiter;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public Date getDateTimeDate() {
		return dateTimeDate;
	}

	public void setDateTimeDate(Date dateTimeDate) {
		this.dateTimeDate = dateTimeDate;
	}
	
	
	/**
	 * 
	 * @param quan
	 * @param rate
	 * @return
	 */
	public Double Calculate_Price(Double quan,Double rate) {
		return quan*rate;
	}
	
}
