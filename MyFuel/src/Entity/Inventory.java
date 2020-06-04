package Entity;

import java.io.Serializable;

public class Inventory implements Serializable {


	public Inventory(String gasoline, String deisel, String scotter, String homeHeating, String gasolineInv,
			String deiselInv, String scotterInv, String homeHeatingInv) {
		super();
		Gasoline = gasoline;
		Deisel = deisel;
		Scotter = scotter;
		HomeHeating = homeHeating;
		GasolineInv = gasolineInv;
		DeiselInv = deiselInv;
		ScotterInv = scotterInv;
		HomeHeatingInv = homeHeatingInv;
	}
	public String getGasoline() {
		return Gasoline;
	}
	public void setGasoline(String gasoline) {
		Gasoline = gasoline;
	}
	public String getDeisel() {
		return Deisel;
	}
	public void setDeisel(String deisel) {
		Deisel = deisel;
	}
	public String getScotter() {
		return Scotter;
	}
	public void setScotter(String scotter) {
		Scotter = scotter;
	}
	public String getHomeHeating() {
		return HomeHeating;
	}
	public void setHomeHeating(String homeHeating) {
		HomeHeating = homeHeating;
	}
	public String getGasolineInv() {
		return GasolineInv;
	}
	public void setGasolineInv(String gasolineInv) {
		GasolineInv = gasolineInv;
	}
	public String getDeiselInv() {
		return DeiselInv;
	}
	public void setDeiselInv(String deiselInv) {
		DeiselInv = deiselInv;
	}
	public String getScotterInv() {
		return ScotterInv;
	}
	public void setScotterInv(String scotterInv) {
		ScotterInv = scotterInv;
	}
	public String getHomeHeatingInv() {
		return HomeHeatingInv;
	}
	public void setHomeHeatingInv(String homeHeatingInv) {
		HomeHeatingInv = homeHeatingInv;
	}
	String Gasoline;
	String Deisel;
	String Scotter;
	String HomeHeating;
	String GasolineInv;
	String DeiselInv;
	String ScotterInv;
	String HomeHeatingInv;

}
