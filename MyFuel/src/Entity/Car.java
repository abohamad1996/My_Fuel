package Entity;

import java.io.Serializable;

public class Car implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String ownerID;
	String carNumber;
	String purchasePlan;
	String Services;
	String gastype;
	String gasStation1;
	String gasStation2;
	String gasStation3;
	String rateForCar
	
	
	;
	public Car(String ownerID, String carNumber, String purchasePlan, String services, String gastype,
			String gasStation1, String gasStation2, String gasStation3, String rateForCar) {
		super();
		this.ownerID = ownerID;
		this.carNumber = carNumber;
		this.purchasePlan = purchasePlan;
		Services = services;
		this.gastype = gastype;
		this.gasStation1 = gasStation1;
		this.gasStation2 = gasStation2;
		this.gasStation3 = gasStation3;
		this.rateForCar = rateForCar;
	}
	@Override
	public String toString() {
		return "Car [ownerID=" + ownerID + ", carNumber=" + carNumber + ", purchasePlan=" + purchasePlan + ", Services="
				+ Services + ", gastype=" + gastype + ", gasStation1=" + gasStation1 + ", gasStation2=" + gasStation2
				+ ", gasStation3=" + gasStation3 + ", rateForCar=" + rateForCar + "]";
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getPurchasePlan() {
		return purchasePlan;
	}
	public void setPurchasePlan(String purchasePlan) {
		this.purchasePlan = purchasePlan;
	}
	public String getServices() {
		return Services;
	}
	public void setServices(String services) {
		Services = services;
	}
	public String getGastype() {
		return gastype;
	}
	public void setGastype(String gastype) {
		this.gastype = gastype;
	}
	public String getGasStation1() {
		return gasStation1;
	}
	public void setGasStation1(String gasStation1) {
		this.gasStation1 = gasStation1;
	}
	public String getGasStation2() {
		return gasStation2;
	}
	public void setGasStation2(String gasStation2) {
		this.gasStation2 = gasStation2;
	}
	public String getGasStation3() {
		return gasStation3;
	}
	public void setGasStation3(String gasStation3) {
		this.gasStation3 = gasStation3;
	}
	public String getRateForCar() {
		return rateForCar;
	}
	public void setRateForCar(String rateForCar) {
		this.rateForCar = rateForCar;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 
	 * @param rate is the maximum price
	 * @param service the service the Client get discount according to
	 * @param Pay is the client use Pre-Paying
	 * @return newRate that the client pay according to it
	 * 
	 * the method calculate the discount for client that he receive for the service he choose
	 */
	public double calculate_car_rate(double rate, String service, String Pay) {
		double newRate = 0;
		
		if (service.equals("Casual fueling")) {
			return rate;
		}
		
		else if (service.equals("Regular monthly 1 car")) {
			newRate= rate * 0.96;
		}
		
		else if (service.equals("Regular monthly +1")) {
			newRate= rate * 0.96 * 0.9;
		}
		
		else  {
			/**
			 * Full monthly
			 */
			newRate= rate * 0.96 * 0.9;
			if(Pay.equals("Yes")) {
				/**
				 * in case that the client using Pre-Paying
				 */
				newRate = newRate * 0.97;
			}
		}
		return newRate;
	}
	
}
