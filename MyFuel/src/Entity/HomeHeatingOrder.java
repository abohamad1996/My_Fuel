package Entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeHeatingOrder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Integer HomeHeatingOrderID=1;
	
	String clientID;
	public static Integer getHomeHeatingOrderID() {
		return HomeHeatingOrderID;
	}




	




	public String getClientID() {
		return clientID;
	}




	public void setClientID(String clientID) {
		this.clientID = clientID;
	}




	public int getOrderID() {
		return OrderID;
	}




	public void setOrderID(int orderID) {
		OrderID = orderID;
	}




	public double getQuantity() {
		return quantity;
	}




	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}




	public String getSupplyDate() {
		return SupplyDate;
	}




	public void setSupplyDate(String supplyDate) {
		SupplyDate = supplyDate;
	}




	public String getUrgent() {
		return Urgent;
	}




	public void setUrgent(String urgent) {
		Urgent = urgent;
	}




	public double getPrice() {
		return Price;
	}




	public void setPrice(double price) {
		Price = price;
	}




	public String getStatus() {
		return Status;
	}




	public void setStatus(String status) {
		Status = status;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	int OrderID;
	double quantity;
	String SupplyDate;
	String Urgent;
	double Price;
	String Status;

	public HomeHeatingOrder(String clientID, double quantity, String supplyDate, String urgent, double price,
			String status) {
		super();
		this.clientID = clientID;
		this.quantity = quantity;
		this.SupplyDate = supplyDate;
		this.Urgent = urgent;
		this.Price = price;
		this.Status = status;
		OrderID=HomeHeatingOrderID++;
	}


	
	public double Calculate_Price_HomeHeating(double quan, String urg,double H_Rate) {
		if (urg.equals("Yes")) {
			H_Rate = H_Rate * 1.2;
		}
		
		double FinalPrice = quan * H_Rate ;
		
		if (quan >= 600 && quan <= 800) {
			FinalPrice = FinalPrice * 0.97;
		}
		else if (quan > 800) {
			FinalPrice = FinalPrice * 0.96;
		}
		
		return FinalPrice;
	}

	

}
