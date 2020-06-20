
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

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.accessibility.internal.resources.accessibility;
import com.sun.beans.WeakCache;
import com.sun.corba.se.pept.transport.Connection;
import com.sun.crypto.provider.RSACipher;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import Entity.AnalyticSystem;
import Entity.Car;
import Entity.CreditCard;
import Entity.Files;
import Entity.Inventory;
import Entity.OrderConfirmation;
import Entity.Rates;
import Entity.Rating;
import Entity.Refueling;
import Entity.Sales;
import Entity.StationsInventory;
import Entity.User;
import sun.security.util.Password;

/**
 * 
 * 
 * 
 * @author Mohamed Abu Hamad
 * In this class we added all the Queries of database (select,insert,update).
 * we use this Queries to insert new data and update data and get data as we needed!
 * 
 */
public class DBconnector {
	
	
/**
 * 	This class for connection to database 
 * @return Connection into SQL 
 * @throws SQLException
 */
public static java.sql.Connection getConnection() throws SQLException
{
 java.sql.Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=IST","root","Aa123456");
	return conn;
}

/**
 * 
 * In this method we check if the user is exist in the database  to make login into  My Fuel system
 * 
 * @param connection this parameter for making connection to SQL
 * @param username  This parameter for username to check in database
 * @param password  This parameter for password to check in database
 * @return it returns a user from Entity  Object (User)
 */
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

/**
 * 
 * 
 * 
 * In this method we take the details from a user by his username and save it in an Object of type User
 * @param connection this parameter for making connection to SQL
 * @param username this parameter username is for select all the data of this username from the database and save it into an Object from type User
 * @return an Object user from type User thats all the data of selected user saved into this Object
 */
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
/**
 * 
 * In this method we can update details abouts the logged in user by ID and this updates saved into database
 * 
 * @param user this parameter is for saving the updates
 * @return a Object from type User that the new details saved into this Object
 */
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
/**
 * 
 * this method is for register a new client into database to table user 
 * 
 * 
 * 
 * @param user we use this parameter here to save in it the new registered client data and insert it to database
 * @return here we return if the inserting new client data into sql success or not
 */
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
/**
 * this method is for register a credit card details into database to table creditcard
 * 
 * 
 * 
 * @param card we use this parameter here to save in it the new registered client credit card data and insert it to database
 * @return here we return if the inserting new client credit card data into sql success or not
 */
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
/**
 * this method is for add a new car for an exiting client into database to table car
 * 
 * 
 * 
 * 
 * @param car  we use this parameter here to save in it the new registered car for exiting client data and insert it to database
 * @return here we return if the inserting new car  data into sql success or not
 */
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
/**
 * 
 * 
 * this method is for get a client ID from sql from table user 
 * 
 * 
 * 
 * @param connection this parameter for making connection to SQL
 * @return it returns an array list from type String that includes all the ID's of Clients from table user in database
 */
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
/**
 * 
 * 
 * in this method we select all the car that included for a client by his ID and save it into arraylist from type String
 * 
 * @param connection this parameter for making connection to SQL
 * @param id we use this parameter for getting all the cars number that included to this id
 * @return it returns an arraylist from type String that included all the carnumbers that icluded to this ID
 */
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
/**
 * 
 * 
 * 
 * in this method we select all the data that connected between car and client in other words we save the purchase plan of car the included to this user logged in
 * @param connection this parameter for making connection to SQL
 * @param id this parameter to save the id of connected client and take data that included for this id
 * @return an arraylist of type Car that included all the cars that included to logged in user 
 */ 
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
/**
 * 
 * in this method we update a password for logged in user by id and update it in sql
 * 
 * @param user this parameter for saving the new password of the logged in user 
 * @return an Object of type user that included the new password and update in sql
 */
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
/**
 * 
 * 
 * 
 * in this method we change and update the status for looged in user to active
 * 
 * @param connection this parameter for making connection to SQL
 * @param username this parameter for checking the username data for user
 * @param password this parameter for checking the password data for user
 * @return an Object of type user that changed the status of to acative(1)
 */
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
/**
 * in this method we change the status of logged out account to disactive
 * 
 * @param connection this parameter for making connection to SQL
 * @param username this parameter for checking the username data for user
 * @param password this parameter for checking the password data for user
 * @return an Object of type user that updated the status to disavtived (0)
 */
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
/**
 * 
 * 
 * in this method we select all the data of stations table and added into an arraylist of type StationsInventory 
 * 
 * @param connection this parameter for making connection to SQL
 * @return an arraylist of type StationsInventory that included all the data of stations and inventory and threshould level
 */
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
/**
 * 
 * 
 * in this method we can update and change the threshold level of gas to any type we want
 * @param inv this parameter to save the new values of threshold level of any type of gas to the selected station
 * @return success if the operation done okay 
 */
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
/**
 * 
 * 
 * in this method we make a new request to chnage rate of gas type and insert this request into database to table ratesrequest with status for checking if accepted or not
 * @param rates this parameter for save the new rate request
 * @return in returns if success or not 
 */
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
/**
 * 
 * 
 * in this method we check if there is a new rate in table with status -1 (Not accepted yet)
 * 
 * @param connection connection this parameter for making connection to SQL
 * @return in returns an arraylist of type Rates that included all the requests not accepted yet
 */
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
/**
 * 
 * 
 * 
 * in this method we update the new rate in the table in sql
 * @param rates this parameter for savig the new rates inside it 
 * @return it return success or not
 */
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
/**
 * 
 * 
 * 
 * in this method after accepting the new rate the status changed to 1 (Accepted)
 * @param rates this parameter for saving the new status into the request
 * @return it return success or not
 */
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
/**
 * 
 * 
 * 
 * in this method after not accepting the new rate the status changed to 0 (not Accepted)
 * @param rates rates this parameter for saving the new status into the request
 * @return it return success or not
 */
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
/**
 * 
 * 
 * 
 * 
 * in this method we get the rate of Home Heating Gas
 * 
 * @param connection connection this parameter for making connection to SQL
 * @return it returns a String that includes the rate of HomeHeating Gas from database
 */
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
/**
 * 
 * in this method we add a new home heating order into database (insert a home heating order details)
 * @param homeHeating in this parameter we save the home heating order data
 * @return return seccess or not
 */
public static String HomeHeatingOrder(Entity.HomeHeatingOrder homeHeating)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.homeheating values(?,?,?,?,?,?,?,?);";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      int a=0;
	      String b=homeHeating.getOwnerID();
	  	double c=homeHeating.getQuantity();
	  	String d=homeHeating.getSupplyDate();
	  	String e=homeHeating.getUrgent();
	  	double f=homeHeating.getPrice();
	  	String h=homeHeating.getStatus();
	  	String k=homeHeating.getTime();
		ps.setInt(1, a);
		ps.setString(2,b); 
		ps.setDouble(3, c);
		ps.setString(4, d);
		ps.setString(5, e);
		ps.setDouble(6, f);
		ps.setString(7, h);
		ps.setString(8, k);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
/**
 * 
 * 
 * in this method we take the id of home heating order for logged in client (Track home heating order)
 * 
 * @param connection connection this parameter for making connection to SQL
 * @param id  we use the id for taking the logged in client id and take the correct data of home heating orders that connected to client logged in
 * @return 
 */
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
/**
 * 
 * in this method we take all the data of home heating order the connected to id of looged in account from database 
 * 
 * @param connection connection this parameter for making connection to SQL
 * @param id we use this parameter for taking data from home heating order table by id of logged in account
 * @return arraylist from Type HomeHeatingOrder that included all the data of orders the included to id of logged in account
 */
public static ArrayList<Entity.HomeHeatingOrder> HomeHeatingOrders(java.sql.Connection connection,String id)
{
Entity.HomeHeatingOrder heatingOrder;
Statement stmt;
	ArrayList<Entity.HomeHeatingOrder> arr = new ArrayList<Entity.HomeHeatingOrder>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT OrderID,ClientID,Quantity, SupplyDate, Urgent,Price, OrderStatus,time\r\n" + 
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
			heatingOrder=new Entity.HomeHeatingOrder(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7),rs.getString(8));
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
/**
 * 
 * 
 * in this method we make a new order of less gas in the station with threshold level
 * 
 * 
 * @param orderConfirmation  in this parameter we save all the data about the new order into Object OrderConfirmation
 * @return return seccess or not
 */
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
/**
 * 
 * 
 * in this method we take from sql all the order requests from table orderconfirmation that have a status Waiting for Station Manager
 * 
 * @param connection connection this parameter for making connection to SQL
 * @return arraylist of type OrderConfirmation that included all the orders from table orderconfirmation
 */
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
/**
 * 
 * 
in this method we set all the max prices of rates of gas types
 * 
 * 
 * @param rate in this parameter we save all the new max prices and update it in database
 * @return return seccess or not

 */
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
/**
 * 
 * 
 * in this method we select all the data of max prices in the database 
 * @param connection connection this parameter for making connection to SQL
 * @return  arraylist of tyoe Rates that imcluded all the pax prices from database
 */
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
/**
 * 
 * 
 * in this method we update the inventory of gas type after a client order
 * @param inv in this parameter we save the new inventory of gas type after client order
 * @return return seccess or not
 */
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
/**
 * 
 * in this method we take all the rates of gas type that we choossed it (not the max prices ) from database
 * 
 * @param connection connection this parameter for making connection to SQL
 * @return  arraylist of type Rates that included the current prices of gas in our system from database 
 */
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
/**
 * in this method we select all the car rates of logged in account and save it
 * 
 * 
 * @param connection connection this parameter for making connection to SQL

 * @param id this paramter for selecting the correct cars details of logged in account
 * @return arraylist of tye Car that included all the data abouts cars that connected to logged in account
 */
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
/**
 * 
 * in this method we select all the gas stations from sql table and take it to use 
 * 
 * 
 * 
 * @param connection this parameter for making connection to SQL
 * @return  arraylist of type StationsInventory that included the data of all stations in sql table 
 */
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
/**
 * 
 * in this method we take the station name and address from sql table 
 * 
 * @param connection connection this parameter for making connection to SQL
 * @param id this parameter for select the current station from logged in account
 * @return arraylist of type String that included all the names and address of stations in table station
 */
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
/**
 * 
 *  in this method we take the id of station from Name and Address and save it 
 * @param ref this parameter for saving the station id in refueling
 * @return returns id of selected gas station by Name and Address
 */
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
/**
 * 
 * in this method we update the inventory of Gasoline after order in refueling
 *  
 * @param station this parameter to save the new station details after order and chaged quantity
 * @return return success or not

 */
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
/**
 * 
 * 
 * in this method we update the inventory of Diesel after order in refueling
 * @param station this parameter to save the new station details after order and chaged quantity
 * @return return success or not
 */
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
/**
 * 
 * in this method we update the inventory of Scooter after order in refueling
 * 
 * @param station this parameter to save the new station details after order and chaged quantity
 * @return 
return success or not
 */
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

/**
 * 
 *   in this method we insert into database the data of refueling operation after refueling done
 * 
 * @param ref in this parameter we save all the data of refueling order 
 * @return  return success or not
 */
public static String RefuelinggOrder(Refueling ref)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.refueling values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
	     String l=ref.getService();
	     String m=ref.getTime();
	     String n=ref.getSaleID();
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
		ps.setString(12,l); 
		ps.setString(13,m); 
		ps.setString(14,n); 
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
/**
 * in this method the system added a new order after threshold level for threshlod level of gas type 
 * @param order this parameter of add the new order of system after threshlod level low
 * @return return success or not
 */
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
/**
 * 
 * 
 * in this method we send a notification for suppliwer by new order of system to confirm
 * 
 * @param connection connection this parameter for making connection to SQL

 * @return arraylist of type OrderConfirmation that included all the order data
 */
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
/**
 * 
 * 
 * 
 * in this method we update the status or order after confirm station manager 
 * @param order this parameter for saving the new status to the order from table sql
 * @return return success or not
 */
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
/**
 * 
 * in this method we update the status or order after  no confirm station manager 
 * 
 * @param order order this parameter for saving the new status to the order from table sql
 * @return  success or not

 */
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
/**
 * in this method we send an alert to Supplier by sql table change status with the new system order 
 * @param connection connection this parameter for making connection to SQL
 * @return  arraylist of type OrderConfirmation that included the new system order with status for supplier
 */
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
/**
 * 
 * 
 * in this method update status of system order after confirm the supplier for this order
 * @param order  this parameter for saving the new status to the order from table sql
 * @return
 */
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
/**
 * in this method we update the status or order after  no confirm supplier  
 * 
 * 
 * @param order this parameter for saving the new status to the order from table sql
 * @return return success or not
 */
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
/**
 * 
 * in this method we updated the Gasoline quantity after system order in thredsold level
 * @param order in this parameter we save the new quanatity after system order done
 * @return  success or not
 */
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
/**
 * in this method we updated the Diesel quantity after system order in thredsold level
 * 
 * @param order order in this parameter we save the new quanatity after system order done
 * @return success or not
 */
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
/**
 * 
 * in this method we updated the Scooter quantity after system order in thredsold level
 * @param order in this parameter we save the new quanatity after system order done
 * @return success or not
 */
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
/**
 * in this method we send to Station manager an alert that system order have been done by sql table adn change status of system order
 * 
 * @param connection  this parameter for making connection to SQL
 * @return arraylist of type OrderConfirmation with the new status after accepting and finish the order operation
 */
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
/**
 * 
 * in this method we update the status of system order after finish and seen by th station manager (alert)
 * @param order in this parameter we save the new status of system order and the last one after finished the order
 * @return success or not

 */
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
/**
 * 
 * in this method we select from database between 2 dates we select a refueling details between 2 dates
 * @param connection this parameter for making connection to SQL
 * @param From this parameter for select the date we start to save details about refueling
 * @param To this parameter for select the date we end to save details about refueling
 * @return arraylist of type Refueling that included all the data between 2 dates selected 
 */
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
			refueling=new Refueling(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14));
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
/**
 * 
 * in this method we select all the stations from sql table 
 * @param connection connection this parameter for making connection to SQL
 * @return arraylist from type StationsInventory the included all gas stations from sql table 
 */
public static ArrayList<Entity.StationsInventory> StationDetails(java.sql.Connection connection)
{
	StationsInventory station;
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
			station=new StationsInventory(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
			arr.add(station);
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
/**
 * 
 * in this method we added a new data of file to read it from another place
 * @param file this parameter to save file details
 * @return success or not
 */
public static String ReadFile(Files file)
{
	Statement stmt;
	FileInputStream fis;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.files values(?,?,?,?);";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	  	int a = 0;
	      String b = file.getFilename();
		String c =file.getPath();
		String d=file.getStatus();
		ps.setInt(1, a); 
		ps.setString(2,b); 	
		ps.setString(3,c); 	
		ps.setString(4, d);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
/**
 * 
 * in this method we detect all the files that have been not readed from sql tables and pc locations
 * @param connection this parameter for making connection to SQL
 * @return arraylist of type Files that included all the files details from sql
 */
public static ArrayList<Entity.Files> DetectFiles(java.sql.Connection connection)
{
	Files files;
	Statement stmt;
	ArrayList<Files> arr = new ArrayList<Files>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "select * FROM my_fuel.files WHERE status=\"Not Readed\" or status=\"Readed Station\";";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.files;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			files=new Files(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			arr.add(files);
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
/**
 * 
 * 
 * in this method we change the status to readed by station manager after station amanager read and open the file
 * @param file in this parameter we save all the changes of readed files
 * @return success or not
 */
public static String UpdateStatusStationManagerReaded(Files file)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.files set status=\"Readed Station\" where id=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.files;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      int a =file.getId();
	      ps.setInt(1, a);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
/**
 * 
 * in this method we save all the data o clients from database
 * @param connection his parameter for making connection to SQL
 * @return arraylist of type User that included all the clients data from database
 */
public static ArrayList<Entity.User> Clients(java.sql.Connection connection)
{
	User user;
	Statement stmt;
	ArrayList<User> arr = new ArrayList<User>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT * FROM my_fuel.user where UserRank=\"Client\" or UserRank=\"Client\";";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.user;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			user=new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
			arr.add(user);
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
/**
 * in this method we add and save all the refueling details from database into arraylist
 * 
 * @param connection this parameter for making connection to SQL
 * @return arraylist of type Refueling that included all the details about all user from database
 */
public static ArrayList<Entity.Refueling> RefuelingFromDB(java.sql.Connection connection)
{
	Refueling refueling;
	Statement stmt;
	ArrayList<Refueling> arr = new ArrayList<Refueling>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT * FROM my_fuel.refueling;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.user;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			refueling=new Refueling(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14));
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
/**
 * 
 *  in this method we added a new data of file to read it 
 * @param file this parameter to save file details
 * @return  success or not
 */
public static String ReadFileMarketingManager(Files file)
{
	Statement stmt;
	FileInputStream fis;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.files values(?,?,?,?);";
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	  	int a = 0;
	      String b = file.getFilename();
		String c =file.getPath();
		String d=file.getStatus();
		ps.setInt(1, a); 
		ps.setString(2,b); 	
		ps.setString(3,c); 	
		ps.setString(4, d);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
/**
 * 
 * 
 * in this method we detect all the files that have been not readed from sql tables and pc locations
 * @param connection this parameter for making connection to SQL
 * @return arraylist of type Files that included all the files details from sql
 */
public static ArrayList<Entity.Files> DetectFilesMarketingManager(java.sql.Connection connection)
{
	Files files;
	Statement stmt;
	ArrayList<Files> arr = new ArrayList<Files>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "select * FROM my_fuel.files WHERE status=\"Not Readed Marketing\"  or status=\"Readed Marketing\";";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.files;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			files=new Files(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			arr.add(files);
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
/**
 * 
 * in this method we change the status to readed by marketing manager after station amanager read and open the file
 * @param file  in this parameter we save all the changes of readed files
 * @return success or not
 */
public static String UpdateStatusMarketingManagerReaded(Files file)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.files set status=\"Readed Marketing\" where id=?;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.files;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	      int a =file.getId();
	      ps.setInt(1, a);
		System.out.println(""+ps.toString());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
/**
 * 
 * in this method we add a new sale into database with sale details

 * @param sales in this parameter we save all the data of current sale 
 * @return success or not
 */
public static String sales(Sales sales) {
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.sales values(?,?,?,?,?,?);";
	    PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	    int a = sales.getIDsales();
	  	String b = sales.getFuelType();
	    String c = sales.getDiscount();

	    String e = sales.getFormHour();
	    String f = sales.getToHout();
	    int g=-1;
		ps.setInt(1,a); 
		ps.setString(2,b); 	
		ps.setString(3,c); 
		ps.setString(4,e);
		ps.setString(5,f); 
		ps.setInt(6, g);
	
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
/**
 * in this method we take all the current rates from sql table
 * 
 * @param connection  this parameter for making connection to SQL
 * @return arraylist of type Rates that included all the current rates of gas types
 */
public static ArrayList<Rates> actualRatesDetails(java.sql.Connection connection)
{
	Rates rates;
	Statement stmt;
	ArrayList<Rates> arr = new ArrayList<Rates>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT * FROM my_fuel.rates;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.rates;");
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
/**
 * 
 * in this method we take all the actived sales from database and use the sale from sql table and the sales details
 * @param connection this parameter for making connection to SQL
 * @return  Object of type Sales that included all the sale details
 */
public static Sales getSale(java.sql.Connection connection)
{
	Sales sale = null;
	Statement stmt;
	//ArrayList<StationsInventory> arr = new ArrayList<StationsInventory>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT * FROM my_fuel.sales where saleStutus =-1;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.sales ");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			sale=new Sales(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6));
			
			System.out.println(sale);
 		}
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return sale;
}
/**
 * 
 * 
 * in this method we update thge status of sale to off after time is over of sale
 * @param sale in this parameter we save the new status of sale after time of sale over
 * @return  success or not
 */
public static String endSale(Sales sale)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.sales SET  saleStutus=? WHERE ID=?";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.sales;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		int a =0;
		int b =sale.getIDsales();
		ps.setInt(1, a);
		ps.setInt(2, b);
	
		 	
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
/**
 * in this method we take all the sales from database 
 * 
 * @param connection this parameter for making connection to SQL
 * @return arraylist of type Sales that included all the sales from database
 */
public static ArrayList<Sales> GetallSales(java.sql.Connection connection)
{
	Sales sales;
	Statement stmt;
	ArrayList<Sales> arr = new ArrayList<Sales>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "SELECT * FROM my_fuel.sales;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.sales;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
				sales=new Sales(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
			arr.add(sales);
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
/**
 * 
 * in this method we take all the data of analaytic system and for calculate the rates
 * @param connection this parameter for making connection to SQL
 * @return arraylist of type AnalyticSystem that included all the data from refueling table for making rates for system and users
 */
public static ArrayList<AnalyticSystem> analyticsystem(java.sql.Connection connection)
{
	AnalyticSystem analyticsystem;
	Statement stmt;
	ArrayList<AnalyticSystem> arr = new ArrayList<AnalyticSystem>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "select orderid,ownerid,gastype,date,services,time from my_fuel.refueling;";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.refueling;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			analyticsystem=new AnalyticSystem(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6));
			arr.add(analyticsystem);
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
/**
 * 
 * in this method we select the rates from user table
 * @param connection this parameter for making connection to SQL
 * @return  arraylist of type Rating that included the rates of every user 
 */
public static ArrayList<Rating> rating(java.sql.Connection connection)
{
	Rating rate;
	Statement stmt;
	ArrayList<Rating> arr = new ArrayList<Rating>();
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "select id,image  from my_fuel.user where UserRank=\"Client\";";
		ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.user");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next())
 		{
			System.out.println(""+ps.toString());
			rate=new Rating(rs.getString(1), rs.getString(2));
			arr.add(rate);
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
/**
 * 
 * in this method we take all the dates we can buy home heating order from database 
 * @param connection this parameter for making connection to SQL
 * @return arraylist of type String that includes all the dates that client can order from it home heating gas 
 */
public static ArrayList<String> HomeHeatingDates(java.sql.Connection connection)
{
ArrayList<String> arr = new ArrayList<String>();
	Statement stmt;
	try 
	{
		stmt = ((java.sql.Connection) connection).createStatement();
		ResultSet rs = stmt.executeQuery("SELECT  Date FROM my_fuel.homeheatingdates;");
 		while(rs.next())
 		{
		arr.add(rs.getString(1));	
 		}
		rs.close();
	
	} catch (SQLException e) {e.printStackTrace();}
	return arr;
}
/**
 * 
 * in this method we add a new dates to database table for home heating orders
 * @param date this paramter for saving the value of date we added
 * @return  success or not
 */
public static String HomeHeatingDateSelect(String date) {
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "insert into my_fuel.homeheatingdates values(?,?);";
	    PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
	    int a = 0;
	  String b=date;
	
		ps.setInt(1,a); 
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
/**
 * 
 * in this method we update the status of home heating order after 2 min to done after orders and after time over
 * @param arr this parameter to take the time and progress value
 * @return success or not

 */
public static String HomeHeatingStatus(ArrayList<String> arr)
{
	Statement stmt;
	try {
		stmt = DBconnector.getConnection().createStatement();
		String query = "update my_fuel.homeheating SET  OrderStatus=? WHERE OrderID=?;";
		//ResultSet rs = stmt.executeQuery("SELECT * FROM my_fuel.sales;");
	      PreparedStatement ps = DBconnector.getConnection().prepareStatement(query);
		String a=arr.get(0);
		String b=arr.get(1);
		ps.setString(1, a);
		ps.setString(2, b);
		 	
		System.out.println(""+ps.toString());
		ps.executeUpdate();
		return "success";
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
}
}