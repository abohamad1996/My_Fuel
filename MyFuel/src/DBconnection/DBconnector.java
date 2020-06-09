package DBconnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.corba.se.pept.transport.Connection;
import com.sun.crypto.provider.RSACipher;

import Entity.Car;
import Entity.CreditCard;
import Entity.Inventory;
import Entity.OrderConfirmation;
import Entity.Rates;
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
 			user=new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getInt(9),rs.getInt(10));
 			return user;
			}
	} catch (SQLException e) {
		//TODO Auto-generated catch block
		e.printStackTrace();
	}
	return user;
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
 			user=new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getInt(9),rs.getInt(10));	 				
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
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.user;");
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
		Integer i =0;
		ps.setString(1,a); 
		ps.setString(2,b); 	
		ps.setString(3,c); 	
		ps.setString(4,d); 	
		ps.setString(5,e); 
		ps.setString(6,f); 
		ps.setString(7,g);
		ps.setString(8, h);
		ps.setInt(9,k); 
		ps.setInt(10, i);
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
		ResultSet rs = stmt.executeQuery("SELECT ID FROM my_fuel.user WHERE UserRank='Client';");
 		while(rs.next())
 		{
		arr.add(rs.getString(1));	
 		}
		rs.close();
	
	} catch (SQLException e) {e.printStackTrace();}
	return arr;
}

public static ArrayList<String> getClientCars(java.sql.Connection connection,String id)
{
ArrayList<String> arr = new ArrayList<String>();
	Statement stmt;
	try 
	{
		stmt = ((java.sql.Connection) connection).createStatement();
		String query = "SELECT carnumber FROM my_fuel.car WHERE OwnerID=?;";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		ResultSet rs = stmt.executeQuery("SELECT carnumber FROM my_fuel.car;");
		String a = id;
		ps.setString(1,a); 
		rs = ps.executeQuery();
		System.out.println(""+rs.toString());
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			arr.add(rs.getString(1));
 		}
		rs.close();
	} catch (SQLException e) {e.printStackTrace();}
	return arr;
}
public static ArrayList<Car> PurchasePlanDetails(java.sql.Connection connection,String id)
{
	Car car ;
	Statement stmt;
	ArrayList<Car> arr = new ArrayList<Car>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT OwnerID, carnumber, purchaseplan, services, gastype,gasstation1, gasstation2, gasstation3\r\n" + 
				"FROM my_fuel.car car , my_fuel.user user\r\n" + 
				"WHERE car.OwnerID=user.ID and OwnerID=?";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.car;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a = id;
	      //String b=carnumber;
		ps.setString(1,a); 
	//	ps.setString(2,b); 
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			car=new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)) ;	
			arr.add(car);
			System.out.println(arr);
 		}
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return arr;
}
public static String UpdateUserPassword(User user)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.user SET Userpassword=? WHERE ID=?";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.user;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		String a = user.getPassword();
		String b = user.getId();
		ps.setString(1,a); 
		ps.setString(2,b); 	
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
public static User StatusLoginUpdate(java.sql.Connection connection, String username, String password) {
	Statement stmt ;
	User user=null ;
	Integer status=1;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "UPDATE my_fuel.user SET Status=1 WHERE Username=? and Userpassword=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.user;");
	     PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	     String a = username;
		String b = password;
		ps.setString(1,a); 
		ps.setString(2,b); 
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		//TODO Auto-generated catch block
		e.printStackTrace();
	}
	return user;
}
public static User StatusLogoutUpdate(java.sql.Connection connection, String username, String password) {
	Statement stmt ;
	User user=null ;
	Integer status=1;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "UPDATE my_fuel.user SET Status=0 WHERE Username=? and Userpassword=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.user;");
	     PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	     String a = username;
		String b = password;
		ps.setString(1,a); 
		ps.setString(2,b); 
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		//TODO Auto-generated catch block
		e.printStackTrace();
	}
	return user;
}

public static ArrayList<Inventory> inventoryDetails(java.sql.Connection connection)
{
	Inventory inventory;
	Statement stmt;
	ArrayList<Inventory> arr = new ArrayList<Inventory>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "select *FROM my_fuel.inventory;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			inventory=new Inventory(rs.getString(1), rs.getString(2), rs.getString(3))	;
			arr.add(inventory);
			System.out.println(arr);
 		}
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return arr;
}
public static Inventory UpdateInventoryLevel(Inventory inv)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.inventory SET ThresholdLevel=? WHERE FuelType=?";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a = inv.getLevel();
		String b = inv.getFuelType();
		ps.setString(1,a); 
		ps.setString(2,b); 	
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static String RatesRequest(Rates rates)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.ratesrequest values(?,?,?);";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	  	String a = rates.getFuelType();
	      String b = rates.getPrice();;
		int c=-1;
		ps.setString(1,a); 
		ps.setString(2,b); 	
		ps.setInt(3,c);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
public static ArrayList<Rates> NewRatesRequest(java.sql.Connection connection)
{
	Rates rates;
	Statement stmt;
	ArrayList<Rates> arr = new ArrayList<Rates>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "select *FROM my_fuel.ratesrequest WHERE status=\"-1\";";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.ratesrequest;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			rates=new Rates(rs.getString(1), rs.getString(2));
			arr.add(rates);
			System.out.println(arr);
 		}
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return arr;
}
public static String SetNewRates(Rates rates)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.rates SET Price=? WHERE FuelType=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.rates;;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a = rates.getPrice();
		String b = rates.getFuelType();
		ps.setString(1,a); 
		ps.setString(2,b); 	
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static String SetNewRatesStatusConfirmed(Rates rates)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.ratesrequest SET status=1 WHERE FuelType=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.ratesrequest;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		String a = rates.getFuelType();
		ps.setString(1,a); 
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static String SetNewRatesStatusNotConfirmed(Rates rates)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.ratesrequest SET status=0 WHERE FuelType=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.ratesrequest;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		String a = rates.getFuelType();
		ps.setString(1,a); 
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static String getHomeHeatingRate(java.sql.Connection connection)
{
	Statement stmt;
	String homeHeatingRate =new String();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT Price FROM my_fuel.rates where FuelType=\"Home Heating fuel\";";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
				 homeHeatingRate=rs.getString(1);
			//System.out.println("rate="+homeHeatingRate);
 		}
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return homeHeatingRate;
	}
	return homeHeatingRate;
}
public static String HomeHeatingOrder(Entity.HomeHeatingOrder homeHeating)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.homeheating values(?,?,?,?,?,?,?);";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      int a=0;
	      String b=homeHeating.getOwnerID();
	  	double c=homeHeating.getQuantity();
	  	String d=homeHeating.getSupplyDate();
	  	String e=homeHeating.getUrgent();
	  	double f=homeHeating.getPrice();
	  	String h=homeHeating.getStatus();
		ps.setInt(1, a);
		ps.setString(2,b); 
		ps.setDouble(3, c);
		ps.setString(4, d);
		ps.setString(5, e);
		ps.setDouble(6, f);
		ps.setString(7, h);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
public static ArrayList<String> getClientHomeHeatingOrders(java.sql.Connection connection,String id)
{
ArrayList<String> arr = new ArrayList<String>();
	Statement stmt;
	try 
	{
		stmt = ((java.sql.Connection) connection).createStatement();
		String query = "SELECT OrderID FROM my_fuel.homeheating WHERE ClientID=?;";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		ResultSet rs = stmt.executeQuery("SELECT OrderID FROM my_fuel.homeheating;");
		String a = id;
		ps.setString(1,a); 
		rs = ps.executeQuery();
		System.out.println(""+rs.toString());
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			arr.add(rs.getString(1));
 		}
		rs.close();
	} catch (SQLException e) {e.printStackTrace();}
	return arr;
}
public static ArrayList<Entity.HomeHeatingOrder> HomeHeatingOrders(java.sql.Connection connection,String id)
{
Entity.HomeHeatingOrder heatingOrder;
Statement stmt;
	ArrayList<Entity.HomeHeatingOrder> arr = new ArrayList<Entity.HomeHeatingOrder>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT OrderID,ClientID,Quantity, SupplyDate, Urgent,Price, OrderStatus\r\n" + 
				"							FROM my_fuel.homeheating home , my_fuel.user user\r\n" + 
				"								WHERE home.ClientID=user.ID and ClientID=?";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.homeheating;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a = id;
	      //String b=carnumber;
		ps.setString(1,a); 
	//	ps.setString(2,b); 
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			heatingOrder=new Entity.HomeHeatingOrder(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7));
			arr.add(heatingOrder);
		//	System.out.println(arr);
 		}
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return arr;
}
public static String orderconfirmation(Entity.OrderConfirmation orderConfirmation)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.orderconfirmation values(?,?,?,?);";
	    PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	  	int a = orderConfirmation.getOrderNumber();
	    String b = orderConfirmation.getType();
	    String e= orderConfirmation.getQuantity();
		int c=-1;
		ps.setInt(1, a);
		ps.setString(2,b); 	
		ps.setInt(4,c);
		ps.setString(5, e);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
public static ArrayList<Entity.OrderConfirmation> OrderConfirmation(java.sql.Connection connection)
{
	OrderConfirmation orderConfirmation;
	Statement stmt;
	ArrayList<OrderConfirmation> arr = new ArrayList<OrderConfirmation>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "select *FROM my_fuel.orderconfirmation WHERE OrderStatus=-1;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.orderconfirmation;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			orderConfirmation=new OrderConfirmation(rs.getInt(1), rs.getString(2), rs.getString(3));
			arr.add(orderConfirmation);
			System.out.println(arr);
 		}
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return arr;
}
}