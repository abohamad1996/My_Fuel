package Entity;

import java.io.Serializable;
/**
*This class represents the details after refueling 
*Every refueling Characterized by OrderID, ownerID, CarNumber,
*GasStation, address, GasType, RateForLiter, Qunatity, 
*Price, Date, pumpNumber, service, time, saleID
*
*/

public class Refueling  implements Serializable{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int OrderID;
	String ownerID;
	String CarNumber;
	String GasStation;
	String address;
	String GasType;
	String RateForLiter;
	String Qunatity;
	String Price;
	String Date;
	String pumpNumber;
	String service;
	String time;
	String saleID;
	/**
	* constructor and initializes object of type refueling
	*
	* @param orderID refueling expired id
	* @param ownerID client id
	* @param carNumber car number
	* @param gasStation gas staion that the client refuel from
	* @param address station address
	* @param gasType gas typ used in refueling
	* @param rateForLiter rate for gas accordeing to to client
	* @param qunatity refueling quantity
	* @param price amount of many
	* @param date refueling date
	* @param pumpNumber pump number 
	* @param service client services
	* @param time refueling time
	* @param saleID sale Id in case that the client refuel at sale time
	*/
	public Refueling(int orderID, String ownerID, String carNumber, String gasStation, String address, String gasType,
			String rateForLiter, String qunatity, String price, String date, String pumpNumber, String service,
			String time, String saleID) {
		super();
		OrderID = orderID;
		this.ownerID = ownerID;
		CarNumber = carNumber;
		GasStation = gasStation;
		this.address = address;
		GasType = gasType;
		RateForLiter = rateForLiter;
		Qunatity = qunatity;
		Price = price;
		Date = date;
		this.pumpNumber = pumpNumber;
		this.service = service;
		this.time = time;
		this.saleID = saleID;
	}
	/**
	*This method object of type refueling 
	*/


	@Override
	public String toString() {
		return "Refueling [OrderID=" + OrderID + ", ownerID=" + ownerID + ", CarNumber=" + CarNumber + ", GasStation="
				+ GasStation + ", address=" + address + ", GasType=" + GasType + ", RateForLiter=" + RateForLiter
				+ ", Qunatity=" + Qunatity + ", Price=" + Price + ", Date=" + Date + ", pumpNumber=" + pumpNumber
				+ ", service=" + service + ", time=" + time + ", saleID=" + saleID + "]";
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
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
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSaleID() {
		return saleID;
	}
	public void setSaleID(String saleID) {
		this.saleID = saleID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	



	
}
