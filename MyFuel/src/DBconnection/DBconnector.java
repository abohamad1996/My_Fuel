package DBconnection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.corba.se.pept.transport.Connection;

import Entity.Car;
import Entity.CreditCard;
import Entity.User;
import gui.Employee;
import gui.UpdateRoleController;

public class DBconnector {

public static java.sql.Connection getConnection() throws SQLException
{
 java.sql.Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=IST","root","Aa123456");
	return conn;
}
public static ArrayList<Employee> addtodb(java.sql.Connection connection)
	{
ArrayList<Employee> arr = new ArrayList<Employee>();
Employee employee;
		Statement stmt;
		try 
		{
			stmt = ((java.sql.Connection) connection).createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.employee;");
	 		while(rs.next())
	 		{
	 			employee=new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));	 			
			arr.add(employee);	
	 		}
			rs.close();
		
			//stmt.executeUpdate("UPDATE course SET semestr=\"W08\" WHERE num=61309");
		} catch (SQLException e) {e.printStackTrace();}
		return arr;
	}
public static String UpdateRole(Employee emp)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.employee SET Role =? WHERE Empnum=?";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.employee;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		String a = emp.getRole();
		String b = emp.getEmpnum();
		ps.setString(1,a); 
		ps.setString(2,b); 	
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
public static User isInDB(java.sql.Connection connection, String username, String password) {
	Statement stmt ;
	User user=null ;
	Integer status=1;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT* FROM my_fuel.user WHERE Username=? AND Userpassword=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.user;");
	     PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	     String a = username;
		String b = password;
		ps.setString(1,a); 
		ps.setString(2,b); 
		System.out.println(""+ps.toString());
		rs=ps.executeQuery();
		if (!rs.next())
			return null;
			else {
 			user=new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getString(9));
 			return user;
			}
	} catch (SQLException e) {
		//TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static User userDetails(java.sql.Connection connection, String username)
{
	User user;
	Statement stmt;
	try 
	{
		stmt = ((java.sql.Connection) connection).createStatement();
		String query = "SELECT* FROM my_fuel.user WHERE Username=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.user;");
	     PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	  	String a = username;
		ps.setString(1,a); 
		rs = ps.executeQuery();
 		while(rs.next())
 		{
 			user=new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9));	 				
 			return user;
 		}
 		///////// comment
		rs.close();
		} catch (SQLException e) {e.printStackTrace();}
	return null;
	}
public static String UpdateUser(User user)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.user SET Firstname=? , Lastname=? , Email=? WHERE ID=?";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.employee;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		String a = user.getFirstname();
		String b =user.getLastname();
		String c = user.getEmail();
		String d = user.getId();
		ps.setString(1,a); 
		ps.setString(2,b); 	
		ps.setString(3,c); 	
		ps.setString(4,d); 	
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
public static String ClientRegisterUserDetails(User user)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.user values(?,?,?,?,?,?,?,?,?,?);";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	  	String a = user.getId();
	      String b = user.getFirstname();
		String c =user.getLastname();
		String d = user.getEmail();
		String e = user.getUsername();
		String f = user.getPassword();
		String g = user.getRank();
		String h = user.getClientType();
		Integer k = user.getStatus();
		Integer i = 0;
		ps.setString(1,a); 
		ps.setString(2,b); 	
		ps.setString(3,c); 	
		ps.setString(4,d); 	
		ps.setString(5,e); 
		ps.setString(6,f); 
		ps.setString(7,g);
		ps.setString(8, h);
		ps.setInt(9,k); 
		ps.setInt(10,i); 
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
public static String ClientRegisterCreditCard(CreditCard card)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.creditcard values(?,?,?,?,?);";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	  	String a = card.getOwnerID();
	      String b = card.getCreditCardNumber();
		String c =card.getCreditMonth();
		String d = card.getCreditYearString();
		String e = card.getCvv();
		ps.setString(1,a); 
		ps.setString(2,b); 	
		ps.setString(3,c); 	
		ps.setString(4,d); 	
		ps.setString(5,e); 
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
public static String ClientAddCars(Car car)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.car values(?,?,?,?,?,?,?,?);";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	  	String a = car.getOwnerID();
	      String b = car.getCarNumber();
		String c =car.getPurchasePlan();
		String d = car.getServices();
		String e = car.getGastype();
		String f = car.getGasStation1();
		String g =car.getGasStation2();
		String h=car.getGasStation3();
		ps.setString(1,a); 
		ps.setString(2,b); 	
		ps.setString(3,c); 	
		ps.setString(4,d); 	
		ps.setString(5,e); 
		ps.setString(6,f); 
		ps.setString(7,g); 
		ps.setString(8,h); 
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
public static ArrayList<String> getClientIDfromDatabase(java.sql.Connection connection)
{
ArrayList<String> arr = new ArrayList<String>();
	Statement stmt;
	try 
	{
		stmt = ((java.sql.Connection) connection).createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ID FROM my_fuel.user;");
 		while(rs.next())
 		{
		arr.add(rs.getString(1));	
 		}
		rs.close();
	
	} catch (SQLException e) {e.printStackTrace();}
	return arr;
}
}
