package Entity;

import java.io.Serializable;
/**
*This class represents client's credit card 
*Every credit card characterized by ownerID, creditCardNumber, creditMonth, 
*creditYearString, cvv
*/

public class CreditCard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 String ownerID;
	String creditCardNumber;
 String creditMonth;
 String creditYearString;
 String cvv;
 
 
 /**
 * constructor and initializes object of type creait crad
 * 
 * @param ownerID creait crad' owner ID
 * @param creditCardNumber creait crad' number
 * @param creditMonth Expired month
 * @param creditYearString Expired year
 * @param cvv cvv code
 */
public CreditCard(String ownerID, String creditCardNumber, String creditMonth, String creditYearString, String cvv) {
	super();
	this.ownerID = ownerID;
	this.creditCardNumber = creditCardNumber;
	this.creditMonth = creditMonth;
	this.creditYearString = creditYearString;
	this.cvv = cvv;
}
public String getOwnerID() {
	return ownerID;
}
public void setOwnerID(String ownerID) {
	this.ownerID = ownerID;
}
public String getCreditCardNumber() {
	return creditCardNumber;
}
public void setCreditCardNumber(String creditCardNumber) {
	this.creditCardNumber = creditCardNumber;
}
public String getCreditMonth() {
	return creditMonth;
}
public void setCreditMonth(String creditMonth) {
	this.creditMonth = creditMonth;
}
public String getCreditYearString() {
	return creditYearString;
}
public void setCreditYearString(String creditYearString) {
	this.creditYearString = creditYearString;
}
public String getCvv() {
	return cvv;
}
public void setCvv(String cvv) {
	this.cvv = cvv;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
/**
*This method print object of type creait crad
*
*/	 

@Override
public String toString() {
	return "CreditCard [ownerID=" + ownerID + ", creditCardNumber=" + creditCardNumber + ", creditMonth=" + creditMonth
			+ ", creditYearString=" + creditYearString + ", cvv=" + cvv + "]";
}
	
	
	
	
	
}
