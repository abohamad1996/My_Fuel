package Entity;

import java.io.Serializable;

public class User implements Serializable{

	String id;
	String Firstname;
	String Lastname;
	String email;
	String Creditcard;
	String Username;
	String Password;
	Integer Rank;
	Integer Status;
	public User(String id, String firstname, String lastname, String email, String creditcard, String username,
			String password, Integer rank, Integer status) {
		super();
		this.id = id;
		Firstname = firstname;
		Lastname = lastname;
		this.email = email;
		Creditcard = creditcard;
		Username = username;
		Password = password;
		Rank = rank;
		Status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreditcard() {
		return Creditcard;
	}
	public void setCreditcard(String creditcard) {
		Creditcard = creditcard;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Integer getRank() {
		return Rank;
	}
	public void setRank(Integer rank) {
		Rank = rank;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}

	
}
