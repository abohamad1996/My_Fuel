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
import Entity.User;
import common.Message;
import gui.Employee;
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
