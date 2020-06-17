package DBconnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.corba.se.pept.transport.Connection;
import com.sun.crypto.provider.RSACipher;

import Entity.Car;
import Entity.CreditCard;
import Entity.Inventory;
import Entity.OrderConfirmation;
import Entity.Rates;
import Entity.Refueling;
import Entity.Sales;
import Entity.StationsInventory;
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
		if (rs.next()==true) {
 			user=new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getInt(9),rs.getInt(10));
 			return user;}
		else {
			return null;
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
	FileInputStream fis;
	File file = new File("C:\\Users\\Abu Hamad\\Desktop\\sky.jpg");
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
		int i=0;
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
		String query = "insert into my_fuel.car values(?,?,?,?,?,?,?,?,?);";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	  	String a = car.getOwnerID();
	      String b = car.getCarNumber();
		String c =car.getPurchasePlan();
		String d = car.getServices();
		String e = car.getGastype();
		String f = car.getGasStation1();
		String g =car.getGasStation2();
		String h=car.getGasStation3();
		String i=car.getRateForCar();
		ps.setString(1,a); 
		ps.setString(2,b); 	
		ps.setString(3,c); 	
		ps.setString(4,d); 	
		ps.setString(5,e); 
		ps.setString(6,f); 
		ps.setString(7,g); 
		ps.setString(8,h); 
		ps.setString(9,i); 
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
		String query = "SELECT OwnerID, carnumber, purchaseplan, services, gastype,gasstation1, gasstation2, gasstation3,rate\r\n" + 
				"FROM my_fuel.car car , my_fuel.user user\r\n" + 
				"				WHERE car.OwnerID=user.ID and OwnerID=?;";
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
			car=new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9)) ;	
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

public static ArrayList<StationsInventory> stationInventoryDetails(java.sql.Connection connection)
{
	StationsInventory inventory;
	Statement stmt;
	ArrayList<StationsInventory> arr = new ArrayList<StationsInventory>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "select *FROM my_fuel.stations;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.stations;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			inventory=new StationsInventory(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11));
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
public static StationsInventory UpdateInventoryLevel(StationsInventory inv)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update  my_fuel.stations set GasolineThresholdLevel=?, DieselThresholdLevel=?,ScooterThresholdLevel=? where ManagerID=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a = inv.getGasolineThresholdLevel();
		String b = inv.getDieselThresholdLevel();
		String c=inv.getScooterThresholdLevel();
		String d =inv.getManagerIDString();
		ps.setString(1,a); 
		ps.setString(2,b); 	
		ps.setString(3,c); 	
		ps.setString(4,d); 	
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
		String query = "select *FROM my_fuel.orderconfirmation WHERE OrderStatus=\"Waiting for Station Manager Confirm\";";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.orderconfirmation;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			orderConfirmation=new OrderConfirmation(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
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
public static Rates SetMaxPrice(Rates rate)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.maximumrates SET Price=? WHERE FuelType=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a = rate.getPrice();
		String b = rate.getFuelType();
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
public static ArrayList<Rates> MaxRatesDetails(java.sql.Connection connection)
{
	Rates rates;
	Statement stmt;
	ArrayList<Rates> arr = new ArrayList<Rates>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT * FROM my_fuel.maximumrates;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.maximumrates;");
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
public static StationsInventory UpdateInventoryAfterOrder(StationsInventory inv)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.stations set HomeHeatingQuantity=? where id=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a = inv.getHomeHeatingQuantity();
		String b = inv.getStationID();
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
public static ArrayList<Rates> rates(java.sql.Connection connection)
{
	Rates rates;
	Statement stmt;
	ArrayList<Rates> arr = new ArrayList<Rates>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT * FROM my_fuel.rates";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.rates");
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
public static ArrayList<Car> CarRates(java.sql.Connection connection,String id)
{
	Car car ;
	Statement stmt;
	ArrayList<Car> arr = new ArrayList<Car>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT OwnerID, carnumber, purchaseplan, services, gastype,gasstation1, gasstation2, gasstation3,rate\r\n" + 
				"FROM my_fuel.car car , my_fuel.user user\r\n" + 
				"				WHERE car.OwnerID=user.ID and OwnerID=?;";
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
			car=new Car(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9)) ;	
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
public static ArrayList<StationsInventory> GasStations(java.sql.Connection connection)
{
	StationsInventory stations;
	Statement stmt;
	ArrayList<StationsInventory> arr = new ArrayList<StationsInventory>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT * FROM my_fuel.stations;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.stations;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			stations=new StationsInventory(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
			arr.add(stations);
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
public static ArrayList<String> GasStationsAddress(java.sql.Connection connection,String id)
{
	Statement stmt;
	ArrayList<String> arr = new ArrayList<String>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT Name Address FROM my_fuel.stations where ;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.stations;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a = id;
			ps.setString(1,a); 
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			arr.add(rs.getString(1));
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

public static String GasStationsID(Refueling ref) // select id
{
	Statement stmt;
	String iDString = null;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT ID FROM my_fuel.stations where NAME=? and Address=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.stations;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a = ref.getGasStation();
	      String b=ref.getAddress();
			ps.setString(1,a); 
			ps.setString(2, b);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			iDString=rs.getString(1);
			return iDString;
 		}
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return iDString;
}

public static StationsInventory UpdateInventoryAfterRefuelingOrderGasoline(StationsInventory station)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.stations set GasolineQuantity=? where ID=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a =station.getGasolineQuantity();
	      String b=station.getStationID();
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
public static StationsInventory UpdateInventoryAfterRefuelingOrderDeisel(StationsInventory station)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.stations set DieselQuantity=? where ID=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a =station.getDieselQuantity();
	      String b=station.getStationID();
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
public static StationsInventory UpdateInventoryAfterRefuelingOrderScooter(StationsInventory station)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.stations set ScooterQuantity=? where ID=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a =station.getScooterQuantity();
	      String b=station.getStationID();
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

public static String sales(Sales sales) {
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.sales values(?,?,?,?,?,?);";
	    PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	    int a = sales.getIDsales();
	  	String b = sales.getFuelType();
	    String c = sales.getDiscount();
	    String d = sales.getDate().toString();
	    String e = sales.getFormHour();
	    String f = sales.getToHout();
		ps.setInt(1,a); 
		ps.setString(2,b); 	
		ps.setString(3,c); 
		ps.setString(4,d);
		ps.setString(5,e); 
		ps.setString(6,f); 
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
public static String RefuelinggOrder(Refueling ref)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.refueling values(?,?,?,?,?,?,?,?,?,?,?);";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      int a=0;
	      String k=ref.getOwnerID();
	     String b=ref.getCarNumber();
	     String c=ref.getGasStation();
	     String d=ref.getAddress();
	     String e=ref.getGasType();
	     String f=ref.getRateForLiter();
	     String g=ref.getQunatity();
	     String h=ref.getPrice();
	     String i=ref.getDate();
	     String j=ref.getPumpNumber();
	 	ps.setInt(1,a);
	 	ps.setString(2, k);
		ps.setString(3,b); 
		ps.setString(4,c); 
		ps.setString(5,d); 
		ps.setString(6,e); 
		ps.setString(7,f); 
		ps.setString(8,g); 
		ps.setString(9,h); 
		ps.setString(10,i); 
		ps.setString(11,j); 
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
public static String SystemNewOrder(OrderConfirmation order)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.orderconfirmation values(?,?,?,?,?,?,?,?);";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	  	int a=0;
	      String b = order.getType();
		String c =order.getQuantity();
		String d = order.getStatus();
		String e = order.getStationName();
		String f = order.getAddress();
		String g = order.getDate();
		String h = order.getManagerID();
		ps.setInt(1, a);
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
public static ArrayList<Entity.OrderConfirmation> OrderConfirmationSupplier(java.sql.Connection connection)
{
	OrderConfirmation orderConfirmation;
	Statement stmt;
	ArrayList<OrderConfirmation> arr = new ArrayList<OrderConfirmation>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "select *FROM my_fuel.orderconfirmation WHERE OrderStatus=\"Waiting for Supplier Confirm\";";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.orderconfirmation;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			orderConfirmation=new OrderConfirmation(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
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
public static String UpdateStatusStationManagerConfirm(OrderConfirmation order)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.orderconfirmation set OrderStatus=\"Waiting for Supplier Confirm\" where OrderNumber=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      int a =order.getOrderNumber();
	      ps.setInt(1, a);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static String UpdateStatusStationManagerNotConfirm(OrderConfirmation order)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.orderconfirmation set OrderStatus=\"Not Confirmed\" where OrderNumber=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      int a =order.getOrderNumber();
	      ps.setInt(1, a);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static ArrayList<Entity.OrderConfirmation> OrderConfirmationSupplierAlert(java.sql.Connection connection)
{
	OrderConfirmation orderConfirmation;
	Statement stmt;
	ArrayList<OrderConfirmation> arr = new ArrayList<OrderConfirmation>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "select *FROM my_fuel.orderconfirmation WHERE OrderStatus=\"Waiting for Supplier Confirm\";";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.orderconfirmation;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			orderConfirmation=new OrderConfirmation(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
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
public static String UpdateStatusSupplierConfirm(OrderConfirmation order)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.orderconfirmation set OrderStatus=\"Order Done\" where OrderNumber=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      int a =order.getOrderNumber();
	      ps.setInt(1, a);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static String UpdateStatusSupplierNotConfirm(OrderConfirmation order)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.orderconfirmation set OrderStatus=\"No Enough Quantity\" where OrderNumber=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      int a =order.getOrderNumber();
	      ps.setInt(1, a);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static String UpdateGasolineQuantity(OrderConfirmation order)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.stations set GasolineQuantity=? where Name=? and Address=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a ="5000";
	      String b =order.getStationName();
	      String c =order.getAddress();
	     ps.setString(1, a);
	     ps.setString(2, b);
	     ps.setString(3, c);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static String UpdateDeiselQuantity(OrderConfirmation order)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.stations set DieselQuantity=? where Name=? and Address=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a ="5000";
	      String b =order.getStationName();
	      String c =order.getAddress();
	     ps.setString(1, a);
	     ps.setString(2, b);
	     ps.setString(3, c);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static String UpdateScooterQuantity(OrderConfirmation order)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.stations set ScooterQuantity=? where Name=? and Address=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a ="5000";
	      String b =order.getStationName();
	      String c =order.getAddress();
	     ps.setString(1, a);
	     ps.setString(2, b);
	     ps.setString(3, c);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static ArrayList<Entity.OrderConfirmation> OrderConfirmationDoneAlert(java.sql.Connection connection)
{
	OrderConfirmation orderConfirmation;
	Statement stmt;
	ArrayList<OrderConfirmation> arr = new ArrayList<OrderConfirmation>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "select * FROM my_fuel.orderconfirmation WHERE OrderStatus=\"Order Done\";";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.orderconfirmation;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			orderConfirmation=new OrderConfirmation(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
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
public static String UpdateStatusStationManagerSeen(OrderConfirmation order)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.orderconfirmation set OrderStatus=\"Seen\" where OrderNumber=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.inventory;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      int a =order.getOrderNumber();
	      ps.setInt(1, a);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static ArrayList<Entity.Refueling> RefuelingDateSelect(java.sql.Connection connection,String From,String To)
{
	Refueling refueling;
	Statement stmt;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
   
	ArrayList<Refueling> arr = new ArrayList<Refueling>();
	try {

		stmt = DBconnector.getConnection().createStatement();
		String query = "select * from my_fuel.refueling where my_fuel.refueling.date between ? and ?;";
		ResultSet rs = stmt.executeQuery("select * from my_fuel.refueling");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      String a =From;
	      String b =To;
	   ps.setString(1, a);
	   ps.setString(2, b);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			refueling=new Refueling(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
					arr.add(refueling);
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