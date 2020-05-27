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
		switch (recieved.getCode()) {
		case 1:
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
