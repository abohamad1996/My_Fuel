package Entity;

import java.io.Serializable;

public class OrderConfirmation implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		String orderNumber;
		String type;
		String quantity;
		
		
		
		
	
		public OrderConfirmation(String orderNumber, String type, String quantity) {
			super();
			this.orderNumber = orderNumber;
			this.type = type;
			this.quantity = quantity;
		}
		public String getOrderNumber() {
			return orderNumber;
		}
		public void setOrderNumber(String orderNumber) {
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
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	
		

}
