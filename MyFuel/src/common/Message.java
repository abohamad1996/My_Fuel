package common;

import java.io.Serializable;

import javax.sql.CommonDataSource;

public class Message implements Serializable {
	int code;
	Object object;

	/**
	 * this common is for send and recieve messages from server by code and object we want to send 
	 * the code for message number and the object for the something we want to send
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 * @param code this parameter for message number when we send and recieve from server and client
	 * @param object this is parameter for save the something we want to send or recieve in the client and server its type of Object
	 */
	public Message(int code, Object object) {
		this.code = code;
		this.object = object;
	}
/**
 * 
 * @return the code number that we send or recieved
 */
	public int getCode() {
		return code;
	}
/**
 * 
 * 
 * @return the object that we send or recieved
 */
	public Object getObject() {
		return object;
	}
}
