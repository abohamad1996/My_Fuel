package Entity;

import java.io.Serializable;
/**
*This class represents the order that the system create when the inventory
*for gas type goes below threshold level
*Every order confirmation Characterized by orderNumber,type, quantity, status, stationName
*address, date, managerID
*/
public class OrderConfirmation implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		int orderNumber;
		String type;
		String quantity;
		String status;
		String stationName;
		String address;
		String date;
		String managerID;
		
		
		
		
		
		/**
		* 
		*constructor and initializes object of type order confirmation
		*
		* @param orderNumber order's ID
		* @param type  gas type wanted
		* @param quantity quantity needed to refuel
		* @param status order's status 
		* @param stationName station name
		* @param address station address
		* @param date order's created date
		* @param managerID station manafger ID
		*/
		public OrderConfirmation(int orderNumber, String type, String quantity, String status, String stationName,
				String address, String date, String managerID) {
			super();
			this.orderNumber = orderNumber;
			this.type = type;
			this.quantity = quantity;
			this.status = status;
			this.stationName = stationName;
			this.address = address;
			this.date = date;
			this.managerID = managerID;
		}






		public int getOrderNumber() {
			return orderNumber;
		}






		public void setOrderNumber(int orderNumber) {
			this.orderNumber = orderNumber;
		}






		public String getType() {
			return type;
		}






		public void setType(String type) {
			this.type = type;
		}






		public String getQuantity() {
			return quantity;
		}






		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}






		public String getStatus() {
			return status;
		}






		public void setStatus(String status) {
			this.status = status;
		}






		public String getStationName() {
			return stationName;
		}






		public void setStationName(String stationName) {
			this.stationName = stationName;
		}






		public String getAddress() {
			return address;
		}






		public void setAddress(String address) {
			this.address = address;
		}






		public String getDate() {
			return date;
		}






		public void setDate(String date) {
			this.date = date;
		}






		public String getManagerID() {
			return managerID;
		}






		public void setManagerID(String managerID) {
			this.managerID = managerID;
		}




		/**
		*This method object of type order confirmation 
		*/



		@Override
		public String toString() {
			return "OrderConfirmation [orderNumber=" + orderNumber + ", type=" + type + ", quantity=" + quantity
					+ ", status=" + status + ", stationName=" + stationName + ", address=" + address + ", date=" + date
					+ ", managerID=" + managerID + "]";
		}
		
	
	
		

}
