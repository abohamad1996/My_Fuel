package Entity;

import java.io.Serializable;

public class StationsInventory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String StationID;
	String StationName;
	String StationAddress;
	String GasolineQuantity;
	String DieselQuantity;
	String ScooterQuantity;
	String HomeHeatingQuantity;
	String GasolineThresholdLevel;
	String DieselThresholdLevel;
	String ScooterThresholdLevel;
	String managerIDString;
	
	

	
	@Override
	public String toString() {
		return "StationsInventory [StationID=" + StationID + ", StationName=" + StationName + ", StationAddress="
				+ StationAddress + ", GasolineQuantity=" + GasolineQuantity + ", DieselQuantity=" + DieselQuantity
				+ ", ScooterQuantity=" + ScooterQuantity + ", HomeHeatingQuantity=" + HomeHeatingQuantity
				+ ", GasolineThresholdLevel=" + GasolineThresholdLevel + ", DieselThresholdLevel="
				+ DieselThresholdLevel + ", ScooterThresholdLevel=" + ScooterThresholdLevel + ", managerIDString="
				+ managerIDString + "]";
	}

	public StationsInventory(String stationID, String stationName, String stationAddress, String gasolineQuantity,
			String dieselQuantity, String scooterQuantity, String homeHeatingQuantity, String gasolineThresholdLevel,
			String dieselThresholdLevel, String scooterThresholdLevel, String managerIDString) {
		super();
		StationID = stationID;
		StationName = stationName;
		StationAddress = stationAddress;
		GasolineQuantity = gasolineQuantity;
		DieselQuantity = dieselQuantity;
		ScooterQuantity = scooterQuantity;
		HomeHeatingQuantity = homeHeatingQuantity;
		GasolineThresholdLevel = gasolineThresholdLevel;
		DieselThresholdLevel = dieselThresholdLevel;
		ScooterThresholdLevel = scooterThresholdLevel;
		this.managerIDString = managerIDString;
	}

	public String getStationID() {
		return StationID;
	}
	public void setStationID(String stationID) {
		StationID = stationID;
	}
	public String getStationName() {
		return StationName;
	}
	public void setStationName(String stationName) {
		StationName = stationName;
	}
	public String getStationAddress() {
		return StationAddress;
	}
	public void setStationAddress(String stationAddress) {
		StationAddress = stationAddress;
	}
	public String getDieselQuantity() {
		return DieselQuantity;
	}
	public void setDieselQuantity(String dieselQuantity) {
		DieselQuantity = dieselQuantity;
	}
	public String getGasolineQuantity() {
		return GasolineQuantity;
	}
	public void setGasolineQuantity(String gasolineQuantity) {
		GasolineQuantity = gasolineQuantity;
	}
	public String getScooterQuantity() {
		return ScooterQuantity;
	}
	public void setScooterQuantity(String scooterQuantity) {
		ScooterQuantity = scooterQuantity;
	}
	public String getHomeHeatingQuantity() {
		return HomeHeatingQuantity;
	}
	public void setHomeHeatingQuantity(String homeHeatingQuantity) {
		HomeHeatingQuantity = homeHeatingQuantity;
	}
	public String getDieselThresholdLevel() {
		return DieselThresholdLevel;
	}
	public void setDieselThresholdLevel(String dieselThresholdLevel) {
		DieselThresholdLevel = dieselThresholdLevel;
	}
	public String getGasolineThresholdLevel() {
		return GasolineThresholdLevel;
	}
	public void setGasolineThresholdLevel(String gasolineThresholdLevel) {
		GasolineThresholdLevel = gasolineThresholdLevel;
	}
	public String getScooterThresholdLevel() {
		return ScooterThresholdLevel;
	}
	public void setScooterThresholdLevel(String scooterThresholdLevel) {
		ScooterThresholdLevel = scooterThresholdLevel;
	}
	public String getManagerIDString() {
		return managerIDString;
	}
	public void setManagerIDString(String managerIDString) {
		this.managerIDString = managerIDString;
	}


	
	
	
	
}
