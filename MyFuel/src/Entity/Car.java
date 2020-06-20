package Entity;

import java.io.Serializable;
/**
*lass represents client's Car
*Every car characterized by ownerID, carNumber, purchasePlan, Services,
*gastype, gasStation1, gasStation2, gasStation3, rateForCar;
*/
public class Car implements Serializable{

	
	
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
	/**
	*constructor and initializes object of type car 
	*
	*@param ownerID - car owner ID
	*@param carNumber - car's number
	*@param purchasePlan - purchasePlan's level
	*@param services- client' services that he choose at the registertion 
	*@param gastype - gasType that the car use 
	*@param gasStation2- gas station that the client want to refuel from
	*@param gasStation3- gas station that the client want to refuel from
	*@param rateForCar- client' rate according to the services
	*
	*/
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
	/**
	*This method print object of type car 
	*
	*/
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
	* The method calculate the discount for client that he receive according to the service he choose
	* if the serivce Casual fueling the client do not get discount
	* if the serivce Regular monthly 1 car the client get discount 4%
	* if the serivce Regular monthly +1 the client get discount 4% and 10%
	* if the serivce Full monthly the client get discount 4% and 10% and another 3% for pre-paying

	* @param rate for gas type in the station
	* @param service the service the Client get discount according to
	* @param Pay for services Full monthly if he use Pre-Paying
	* @return newRate that the client pay according to it
	* 
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
