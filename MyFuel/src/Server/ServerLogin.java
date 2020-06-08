package Server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import DBconnection.DBconnector;
import Entity.User;
import common.Message;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ServerLogin extends AbstractServer{
	public ServerLogin(int port) {
		super(port);
	}
	public static class sqlcon {
		public static Connection getConnection() throws SQLException {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=IST", "root",
					"Aa123456");
			return conn;
		}
	}

	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Message recieved = (Message) msg;
		System.out.println("Message received: " + recieved.getCode() + " from " + client);
	
}
	
	
	
	
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
