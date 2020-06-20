package Entity;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import common.MyFile;
/**
*This class represents all the user for my fuel system
*Every user for my fuel system Characterized by id, Firstname,
*Lastname, email, Username, Password, Rank, ClientType, status
*
*/

public class User implements Serializable{


	/**
	*This method object of type use 
	*/
	@Override
	public String toString() {
		return "User [id=" + id + ", Firstname=" + Firstname + ", Lastname=" + Lastname + ", email=" + email
				+ ", Username=" + Username + ", Password=" + Password + ", Rank=" + Rank + ", ClientType=" + ClientType
				+ ", Status=" + Status + ", Image=" + Image + "]";
	}
	/**
	* constructor and initializes object of type user
	*
	* @param id user id
	* @param firstname user first name
	* @param lastname user last name
	* @param email user email
	* @param username user name to access to my fuel system
	* @param password password to access to my fuel system
	* @param rank user rank
	* @param clientType client type
	* @param status user status
	*/
	public User(String id, String firstname, String lastname, String email, String username, String password,
			String rank, String clientType, Integer status, Integer image) {
		super();
		this.id = id;
		Firstname = firstname;
		Lastname = lastname;
		this.email = email;
		Username = username;
		Password = password;
		Rank = rank;
		ClientType = clientType;
		Status = status;
		Image = image;
	}
	String id;
	String Firstname;
	String Lastname;
	String email;
	String Username;
	String Password;
	String Rank;
	String ClientType;
	Integer Status;
	Integer Image;
	public Integer getImage() {
		return Image;
	}
	public void setImage(Integer image) {
		Image = image;
	}



	public String getClientType() {
		return ClientType;
	}
	public void setClientType(String clientType) {
		ClientType = clientType;
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
	public String getRank() {
		return Rank;
	}
	public void setRank(String rank) {
		Rank = rank;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}

	
}
