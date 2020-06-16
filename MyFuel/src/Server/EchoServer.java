// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package Server;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

import DBconnection.DBconnector;
import Entity.Car;
import Entity.CreditCard;
import Entity.HomeHeatingOrder;
import Entity.Inventory;
import Entity.OrderConfirmation;
import Entity.Rates;
import Entity.Refueling;
import Entity.Sales;
import Entity.StationsInventory;
import Entity.User;
import common.Message;
import gui.Employee;
import gui.MarketingManagerRateController;
import gui.NetworkManagerApproveRatesController;
import gui.NetworkManagerController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer {
	public String username=null;
	public String iD=null;
	public String carNumber=null;
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	// final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 * 
	 */

	public EchoServer(int port) {
		super(port);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 * @param
	 */
	public static class sqlcon {
		public static Connection getConnection() throws SQLException {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=IST", "root",
					"Aa123456");
			return conn;
		}
	}

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {

		Message recieved = (Message) msg;
		System.out.println("Message received: " + recieved.getCode() + " from " + client);
		switch (recieved.getCode()) {
		case 1:// show emplouyee details
			try {
				ArrayList<Employee> aa = DBconnector.addtodb(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(1, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:// update role
			Employee emp=(Employee) recieved.getObject();
			String str=DBconnector.UpdateRole(emp);
			try {
				client.sendToClient(new Message(2, str));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
	case 3:// login
		String[] loginMessage = (String[]) recieved.getObject();
		System.out.println("Username:"+loginMessage[1]+" Password:"+loginMessage[2]);	
			Connection connection;
			username=loginMessage[1];
				//System.out.println(""+a.getId()+" "+a.getFirstname()+" "+a.getLastname()+" "+a.getEmail()+" "+a.getUsername()+" "+a.getPassword()+" "+a.getRank()+" "+a.getStatus());
				try {
						connection = DBconnector.getConnection();
						User user=DBconnector.isInDB(connection, loginMessage[1], loginMessage[2]);
						iD=user.getId();
						client.sendToClient(new Message(3, user));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
	case 4:// profile user
		try {
			User aa = DBconnector.userDetails(DBconnector.getConnection(),username);
			Object bb = aa;
			client.sendToClient(new Message(4, bb));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 5:// update profile user
		User user=(User) recieved.getObject();
		String str1=DBconnector.UpdateUser(user);
		try {
			client.sendToClient(new Message(5, str1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 6:// create client account
		User Client=(User) recieved.getObject();
		String s2t=DBconnector.ClientRegisterUserDetails(Client);
		try {
			client.sendToClient(new Message(6, s2t));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 7:// add credit card
		CreditCard card=(CreditCard) recieved.getObject();
		String s3t=DBconnector.ClientRegisterCreditCard(card);
		try {
			client.sendToClient(new Message(7, s3t));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 8:// add car 
		Car car=(Car) recieved.getObject();
		carNumber=car.getCarNumber();
		String s4t=DBconnector.ClientAddCars(car);
		try {
			client.sendToClient(new Message(8, s4t));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 9:// show id details
		try {
			ArrayList<String> aa;
			try {
				aa = DBconnector.getClientIDfromDatabase(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(9, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 10:// purchase plan
		try {
			ArrayList<Car> aa = DBconnector.PurchasePlanDetails(DBconnector.getConnection(), iD);
			Object bb = aa;
			client.sendToClient(new Message(10, bb));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 11:// carOwners
		try {
			ArrayList<String> aa;
			try {
				aa = DBconnector.getClientCars(DBconnector.getConnection(),iD);
				Object bb = aa;
				client.sendToClient(new Message(11, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 12:// update profile user
		User user2=(User) recieved.getObject();
		String str11=DBconnector.UpdateUserPassword(user2);
		try {
			client.sendToClient(new Message(12, str11));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 13:// InventoryDetails
		try {
			ArrayList<StationsInventory> aa;
			try {
				aa = DBconnector.stationInventoryDetails(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(13, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 14:// Inventory
		StationsInventory inv=(StationsInventory)recieved.getObject();;
		StationsInventory strInv=DBconnector.UpdateInventoryLevel(inv);
		try {
			client.sendToClient(new Message(14, strInv));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 15:// purchase plan
		try {
			ArrayList<Car> aa = DBconnector.PurchasePlanDetails(DBconnector.getConnection(), iD);
			Object bb = aa;
			client.sendToClient(new Message(15, bb));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 16:// rates
		Rates rates=(Rates)recieved.getObject();;
		String strRates=DBconnector.RatesRequest(rates);
		try {
			client.sendToClient(new Message(16, strRates));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 17:// Rates Request
		try {
			ArrayList<Rates> aa;
			try {
				aa = DBconnector.NewRatesRequest(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(17, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 18:// update rates
		Rates newRates=(Rates) recieved.getObject();
		String strSetRates=DBconnector.SetNewRates(newRates);
		try {
			client.sendToClient(new Message(18, strSetRates));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 19:// update rate status confirmed
		Rates newRatesConfirm=(Rates) recieved.getObject();
		String strSetRatesConfirm=DBconnector.SetNewRatesStatusConfirmed(newRatesConfirm);
		try {
			client.sendToClient(new Message(19, strSetRatesConfirm));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 20:// update rate status not confirmed
		Rates newRatesNotConfirm=(Rates) recieved.getObject();
		String strSetRatesNotConfirm=DBconnector.SetNewRatesStatusNotConfirmed(newRatesNotConfirm);
		try {
			client.sendToClient(new Message(20, strSetRatesNotConfirm));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 21:// Rates Request Alert
		try {
			ArrayList<Rates> aa;
			try {
				aa = DBconnector.NewRatesRequest(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(21, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 22:// Rates Request Alert
		try {
			String aa;
			try {
				aa = DBconnector.getHomeHeatingRate(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(22, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 23:// HomeHeating
		HomeHeatingOrder homeHeatingOrder=(HomeHeatingOrder)recieved.getObject();;
		String strHomeHeating=DBconnector.HomeHeatingOrder(homeHeatingOrder);
		try {
			client.sendToClient(new Message(23, strHomeHeating));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 24:// HomeHeating Orders Owners
		try {
			ArrayList<String> aa;
			try {
				aa = DBconnector.getClientHomeHeatingOrders(DBconnector.getConnection(), iD);
				Object bb = aa;
				client.sendToClient(new Message(24, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 25:// HomeHeating Orders
		try {
			ArrayList<HomeHeatingOrder> aa = DBconnector.HomeHeatingOrders(DBconnector.getConnection(), iD);
			Object bb = aa;
			client.sendToClient(new Message(25, bb));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 26:// Order Request
		try {
			ArrayList<OrderConfirmation> aa;
			try {
				aa = DBconnector.OrderConfirmation(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(26, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 27:// Order Request Alert
		try {
			ArrayList<OrderConfirmation> aa;
			try {
				aa = DBconnector.OrderConfirmation(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(27, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 28:// set max rates
		Rates maxRates=(Rates)recieved.getObject();;
		Rates strMaxRates=DBconnector.SetMaxPrice(maxRates);
		try {
			client.sendToClient(new Message(28, strMaxRates));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 29:// Max Price Details 
		try {
			ArrayList<Rates> aa;
			try {
				aa = DBconnector.MaxRatesDetails(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(29, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 30:// Max Price Details Marketing manager
		try {
			ArrayList<Rates> aa;
			try {
				aa = DBconnector.MaxRatesDetails(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(30, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 31:// InventoryDetails For Orders
		try {
			ArrayList<StationsInventory> aa;
			try {
				aa = DBconnector.stationInventoryDetails(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(31, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 32:// Inventory update after home heating order
		StationsInventory HomeHeatingOrderinv=(StationsInventory)recieved.getObject();;
		StationsInventory strHomeHeatingOrderInv=DBconnector.UpdateInventoryAfterOrder(HomeHeatingOrderinv);
		try {
			client.sendToClient(new Message(32, strHomeHeatingOrderInv));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 33:// InventoryDetailsfor station manager controller
		try {
			ArrayList<StationsInventory> aa;
			try {
				aa = DBconnector.stationInventoryDetails(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(33, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 34:// InventoryDetailsfor station manager controller for button
		try {
			ArrayList<StationsInventory> aa;
			try {
				aa = DBconnector.stationInventoryDetails(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(34, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 35:// rates details
		try {
			ArrayList<Rates> aa;
			try {
				aa = DBconnector.rates(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(35, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 36:// rates for cars
		try {
			ArrayList<Car> aa = DBconnector.CarRates(DBconnector.getConnection(), iD);
			Object bb = aa;
			client.sendToClient(new Message(36, bb));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 37:// stations details
		try {
			ArrayList<StationsInventory> aa;
			try {
				aa = DBconnector.GasStations(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(37, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 38:// Gas station id
		Refueling gasstationID=(Refueling)recieved.getObject();;
		String strGasstation=DBconnector.GasStationsID(gasstationID);
		try {
			client.sendToClient(new Message(38, strGasstation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 39:// Inventory update after gasoline
		StationsInventory Gasoline=(StationsInventory)recieved.getObject();;
		StationsInventory Gasolinestr=DBconnector.UpdateInventoryAfterRefuelingOrderGasoline(Gasoline);
		try {
			client.sendToClient(new Message(39, Gasolinestr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 40:// Inventory update after deisel
		StationsInventory Deisel=(StationsInventory)recieved.getObject();;
		StationsInventory Deiselstr=DBconnector.UpdateInventoryAfterRefuelingOrderDeisel(Deisel);
		try {
			client.sendToClient(new Message(40, Deiselstr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 41:// Inventory update after scooter
		StationsInventory Scooter=(StationsInventory)recieved.getObject();;
		StationsInventory Scooterstr=DBconnector.UpdateInventoryAfterRefuelingOrderScooter(Scooter);
		try {
			client.sendToClient(new Message(41, Scooterstr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 42:// sales
		Sales sales=(Sales)recieved.getObject();;
		String strSales=DBconnector.sales(sales);
		try {
			client.sendToClient(new Message(42, strSales));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 43:// HomeHeating
		Refueling refueling=(Refueling)recieved.getObject();;
		String strrefueling=DBconnector.RefuelinggOrder(refueling);
		try {
			client.sendToClient(new Message(43, strrefueling));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 44:// new order system
		OrderConfirmation order=(OrderConfirmation) recieved.getObject();
		String Orderstr=DBconnector.SystemNewOrder(order);
		try {
			client.sendToClient(new Message(44, Orderstr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 45:// Order Request
		try {
			ArrayList<OrderConfirmation> aa;
			try {
				aa = DBconnector.OrderConfirmationSupplier(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(45, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 46:// update status from station manager to supllier confirm
		OrderConfirmation StationManagerConfirm=(OrderConfirmation) recieved.getObject();
		String strStationManagerConfirm=DBconnector.UpdateStatusStationManagerConfirm(StationManagerConfirm);
		try {
			client.sendToClient(new Message(46, strStationManagerConfirm));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 47:// update status from station manager to supllier not confirm
		OrderConfirmation StationManagerNotConfirm=(OrderConfirmation) recieved.getObject();
		String strStationManagerNotConfirm=DBconnector.UpdateStatusStationManagerNotConfirm(StationManagerNotConfirm);
		try {
			client.sendToClient(new Message(47, strStationManagerNotConfirm));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 48:// Order Request Alert
		try {
			ArrayList<OrderConfirmation> aa;
			try {
				aa = DBconnector.OrderConfirmationSupplierAlert(DBconnector.getConnection());
				Object bb = aa;
				client.sendToClient(new Message(48, bb));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 49:// update status toe order done supplier
		OrderConfirmation SupplierrConfirm=(OrderConfirmation) recieved.getObject();
		String strSupplierConfirm=DBconnector.UpdateStatusSupplierConfirm(SupplierrConfirm);
		try {
			client.sendToClient(new Message(49, strSupplierConfirm));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 50:// update status from station manager to supllier not confirm
		OrderConfirmation SupplierNotConfirm=(OrderConfirmation) recieved.getObject();
		String strSupplierNotConfirm=DBconnector.UpdateStatusSupplierNotConfirm(SupplierNotConfirm);
		try {
			client.sendToClient(new Message(50, strSupplierNotConfirm));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 51:// Inventory update after gasoline
		OrderConfirmation GasolineThreshould=(OrderConfirmation)recieved.getObject();;
		String GasolineThreshouldstr=DBconnector.UpdateGasolineQuantity(GasolineThreshould);
		try {
			client.sendToClient(new Message(51, GasolineThreshouldstr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 52:// Inventory update after gasoline
		OrderConfirmation DeiselThreshould=(OrderConfirmation)recieved.getObject();;
		String DeiselThreshouldstr=DBconnector.UpdateDeiselQuantity(DeiselThreshould);
		try {
			client.sendToClient(new Message(52, DeiselThreshouldstr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 53:// Inventory update after gasoline
		OrderConfirmation ScooterThreshould=(OrderConfirmation)recieved.getObject();;
		String ScooterThreshouldstr=DBconnector.UpdateScooterQuantity(ScooterThreshould);
		try {
			client.sendToClient(new Message(53, ScooterThreshouldstr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		}
		}
	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());

	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}
}
//End of EchoServer class
