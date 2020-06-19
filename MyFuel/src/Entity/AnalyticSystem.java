package Entity;

import java.io.Serializable;

public class AnalyticSystem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int orderid;
	String clientID;
	String gastype;
	String date;
	String service;
	String time;
	public AnalyticSystem(int orderid, String clientID, String gastype, String date, String service, String time) {
		super();
		this.orderid = orderid;
		this.clientID = clientID;
		this.gastype = gastype;
		this.date = date;
		this.service = service;
		this.time = time;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getGastype() {
		return gastype;
	}
	public void setGastype(String gastype) {
		this.gastype = gastype;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "AnalyticSystem [orderid=" + orderid + ", clientID=" + clientID + ", gastype=" + gastype + ", date="
				+ date + ", service=" + service + ", time=" + time + "]";
	}
	
	
	

}