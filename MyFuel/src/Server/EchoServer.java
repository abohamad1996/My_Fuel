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

import DBconnection.DBconnector;
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
		case 1:
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
		case 2:
			Employee emp=(Employee) recieved.getObject();
			String str=DBconnector.UpdateRole(emp);
			try {
				client.sendToClient(new Message(2, str));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
	case 3:
		String[] loginMessage = (String[]) recieved.getObject();
		System.out.println("Username:"+loginMessage[1]+" Password:"+loginMessage[2]);	
			Connection connection;
				//System.out.println(""+a.getId()+" "+a.getFirstname()+" "+a.getLastname()+" "+a.getEmail()+" "+a.getUsername()+" "+a.getPassword()+" "+a.getRank()+" "+a.getStatus());
				try {
						connection = DBconnector.getConnection();
						User user=DBconnector.isInDB(connection, loginMessage[1], loginMessage[2]);
						client.sendToClient(new Message(3, user));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
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
