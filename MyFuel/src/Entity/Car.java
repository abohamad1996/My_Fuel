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
	String gasStation1;
	String gasStation2;
	String gasStation3;
	public Car(String ownerID, String carNumber, String purchasePlan, String services, String gasStation1,
			String gasStation2, String gasStation3) {
		super();
		this.ownerID = ownerID;
		this.carNumber = carNumber;
		this.purchasePlan = purchasePlan;
		Services = services;
		this.gasStation1 = gasStation1;
		this.gasStation2 = gasStation2;
		this.gasStation3 = gasStation3;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	



}
