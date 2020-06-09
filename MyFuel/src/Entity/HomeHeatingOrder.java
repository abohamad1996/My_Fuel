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

	
	int OrderID;
	String ownerID;
	double quantity;
	String SupplyDate;
	String Urgent;
	double Price;
	String Status;



	
	public HomeHeatingOrder(int orderID, String ownerID, double quantity, String supplyDate, String urgent,
			double price, String status) {
		super();
		OrderID = orderID;
		this.ownerID = ownerID;
		this.quantity = quantity;
		SupplyDate = supplyDate;
		Urgent = urgent;
		Price = price;
		Status = status;
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




	@Override
	public String toString() {
		return "HomeHeatingOrder [OrderID=" + OrderID + ", ownerID=" + ownerID + ", quantity=" + quantity
				+ ", SupplyDate=" + SupplyDate + ", Urgent=" + Urgent + ", Price=" + Price + ", Status=" + Status + "]";
	}

	

}
