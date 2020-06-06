package Entity;

import java.sql.Date;

public class HomeHeatingOrder {
	
	static Integer HomeHeatingOrderID=1;
	
	String clientID;
	Integer OrderID;
	Integer quantity;
	Date SupplyDate;
	String Urgent;
	Double Price;
	String Status;

	public HomeHeatingOrder(String clientID, Integer quantity, Date supplyDate, String urgent, Double price,
			String status) {
		super();
		this.clientID = clientID;
		this.quantity = quantity;
		this.SupplyDate = supplyDate;
		this.Urgent = urgent;
		this.Price = price;
		this.Status = status;
		OrderID=HomeHeatingOrderID;
		HomeHeatingOrderID++;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public Integer getOrderID() {
		return OrderID;
	}

	public void setOrderID(Integer orderID) {
		OrderID = orderID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getSupplyDate() {
		return SupplyDate;
	}

	public void setSupplyDate(Date supplyDate) {
		SupplyDate = supplyDate;
	}

	public String getUrgent() {
		return Urgent;
	}

	public void setUrgent(String urgent) {
		Urgent = urgent;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	
	public double Calculate_Price_HomeHeating(Integer quan, String urg,double H_Rate) {
		if (urg.equals("yes")) {
			H_Rate = H_Rate * 1.2;
		}
		
		double FinalPrice = quan * H_Rate ;
		
		if (quan > 600 && quan < 800) {
			FinalPrice = FinalPrice * 0.97;
		}
		else if (quan > 800) {
			FinalPrice = FinalPrice * 0.96;
		}
		
		return FinalPrice;
	}


	
	
	
}
