package DBconnection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.corba.se.pept.transport.Connection;

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
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT* FROM my_fuel.user WHERE Username=? AND Userpassword=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.user;");
	     PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	  	String a = username;
		String b = password;
		ps.setString(1,a); 
		ps.setString(2,b); 
		//System.out.println(""+ps.toString());
		rs = ps.executeQuery();
		if (!rs.next())
			return null;
			else {
 			user=new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
 			return user;
			}
	} catch (SQLException e) {
		//TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static ArrayList<User> userDetails(java.sql.Connection connection)
{
ArrayList<User> arr = new ArrayList<User>();
User user;
	Statement stmt;
	try 
	{
		stmt = ((java.sql.Connection) connection).createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.user;");
 		while(rs.next())
 		{
 			user=new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9));	 			
		arr.add(user);	
 		}
		rs.close();
		} catch (SQLException e) {e.printStackTrace();}
	return arr;
}

}
