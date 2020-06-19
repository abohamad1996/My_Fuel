package Entity;

import java.io.Serializable;

public class Rating implements Serializable{
	
	String clientid;
	String rate;
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
	
	@Override
	public String toString() {
		return "Rating [clientid=" + clientid + ", rate=" + rate + "]";
	}
}
