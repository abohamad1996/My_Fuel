package Entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**This class represents Home Heating Order
*Every Home heating Order Characterized by OrderID,
*ownerID,quantity, SupplyDate, Urgent, Price, Status
*/
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
	String time;


	
	
	

	/**
	*constructor and initializes object of type home heatimg order 
	*@param OrderID- order's number
	*@param ownerID- ID number for the client who ordered
	*@param quantity- order's quantity 
	*@param SupplyDate- the date that the client want to get the order
	*@param Urgent- is the order urgent (if yes it order execute within 6 hours)
	*@param Price- order's price calculated according to quantity and Urgent
	*@param Status- Describes the Status of the order
	*@param time- indecat to the hour of adding the order
	*/

	public HomeHeatingOrder(int orderID, String ownerID, double quantity, String supplyDate, String urgent,
			double price, String status, String time) {
		super();
		OrderID = orderID;
		this.ownerID = ownerID;
		this.quantity = quantity;
		SupplyDate = supplyDate;
		Urgent = urgent;
		Price = price;
		Status = status;
		this.time = time;
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









	public String getTime() {
		return time;
	}









	public void setTime(String time) {
		this.time = time;
	}









	public static long getSerialversionuid() {
		return serialVersionUID;
	}







	/**
	*This method print object of type home heating order 
	*/


	@Override
	public String toString() {
		return "HomeHeatingOrder [OrderID=" + OrderID + ", ownerID=" + ownerID + ", quantity=" + quantity
				+ ", SupplyDate=" + SupplyDate + ", Urgent=" + Urgent + ", Price=" + Price + ", Status=" + Status
				+ ", time=" + time + "]";
	}







	/**
	*
	*The method calculate the price for home heating order and the
	*discount according to quantity and Urgent
	*if it urgent the rate increase by 2% 
	*if the quantiten less 600 liter the client do not get discount
	*if the quantiten between 600 literand 800 liter the client get discount 3%
	*if the quantiten more than 800 liter the client get discount 4%
	*
	*
	*@param quan the quantity of the order
	*@param H_Rate the rate for home heating fuel
	*@return the price of the order 
	*
	*/

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
