package gui;

import java.io.Serializable;

public class Employee implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String firstname,lastname,empnum,email,role,org;
public Employee(String firstname, String lastname,String empnum, String email, String role, String org) {
	super();
	this.firstname = firstname;
	this.lastname = lastname;
	this.empnum = empnum;
	this.email = email;
	this.role = role;
	this.org = org;
	
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public String getEmpnum() {
	return empnum;
}
public void setEmpnum(String empnum) {
	this.empnum = empnum;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getOrg() {
	return org;
}
public void setOrg(String org) {
	this.org = org;
}



}
