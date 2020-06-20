package Entity;

import java.io.Serializable;
/**
*This class represents the rate for each client according to the services
*Every rating Characterized by clientid, price
*
*/
public class Rating implements Serializable{
	
	String clientid;
	String rate;
	/**
	* constructor and initializes object of type rating
	*
	* @param clientid client' ID
	* @param rate Client rate for liter
	*/

	public Rating(String clientid, String rate) {
		super();
		this.clientid = clientid;
		this.rate = rate;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	/**
	*This method object of type rating  
	*/
	@Override
	public String toString() {
		return "Rating [clientid=" + clientid + ", rate=" + rate + "]";
	}
}
