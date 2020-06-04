// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import client.*;
import common.ChatIF;
import common.Message;
import gui.EmployeeFrameController;
import gui.LoginController;
import gui.MarketingRepresentativeController;
import gui.ProfileSettingsController;
import gui.PurchasePlanController;
import gui.CarRegisterController;
import gui.ClientController;
import gui.ClientRegisterController;
import gui.Employee;
import gui.UpdateRoleController;
import gui.UserHomeController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import DBconnection.DBconnector;
import Entity.Car;
import Entity.User;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI;   public static boolean awaitResponse = false;
  Alert a = new Alert(AlertType.NONE);  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
	 
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   * aaaaaa
   */
  
  public void handleMessageFromServer(Object msg) 
  {
	  awaitResponse = false;
	  Message recieved =(Message)msg;
	  System.out.println("Recived from server " +recieved.getCode());
	  switch(recieved.getCode()) {
	  case 1:
		  ArrayList<?> employeeArrayList =(ArrayList<?>)recieved.getObject();
			 ArrayList<Employee> b=(ArrayList<Employee>)employeeArrayList;
			 EmployeeFrameController.acainstance.TestAcceptor1(b);
		  break;
		  
	  case 2:
		  String st = (String) recieved.getObject();
		  Platform.runLater(() -> {
			  UpdateRoleController.instance.status.setText(st + " Updated!");
		    });
		  break;
	  case 3:
	 	  User user=(User) recieved.getObject();	 	  
	 	  if(user==null)
	 	  {
	 			Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setAlertType(AlertType.ERROR); 
						alert.setContentText("incorrect Username Or Password!!");
						alert.show(); 
					}
				});
	 	  }
	 	  switch (user.getRank()) {
		case "Manager":
			System.out.println("Manager");
			UserHomeController Manager=new UserHomeController();
			Manager.start(user);
			try {
				user=DBconnector.StatusLoginUpdate(DBconnector.getConnection(), user.getUsername(), user.getPassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "Station Manager":
			System.out.println("Station Manager");
			UserHomeController StationManager=new UserHomeController();
			StationManager.start(user);
			try {
				user=DBconnector.StatusLoginUpdate(DBconnector.getConnection(), user.getUsername(), user.getPassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  break;
		case "Marketing Representative":
			System.out.println("Marketing Representative");
			MarketingRepresentativeController Marketing=new MarketingRepresentativeController();
			Marketing.start(user);
			try {
				user=DBconnector.StatusLoginUpdate(DBconnector.getConnection(), user.getUsername(), user.getPassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  break;
		case "Client":
			System.out.println("Client");
			ClientController Client=new ClientController();
			Client.start(user);
			try {
				user=DBconnector.StatusLoginUpdate(DBconnector.getConnection(), user.getUsername(), user.getPassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  break;
	  }
	  case 4:
		 User userArrayList =(User)recieved.getObject();
			 User userarr=(User)userArrayList;
			ProfileSettingsController.acainstance.Acceptor(userarr);
	  break;
  case 5:
	  String st1 = (String) recieved.getObject();
	  Platform.runLater(() -> {
		  ProfileSettingsController.acainstance.status.setText("");
	    });
	  break;
  case 6:
	  String s2t = (String) recieved.getObject();
	  Platform.runLater(() -> {
		  ClientRegisterController.acainstance.status.setText(s2t + " Updated!");
	    });
	  break;
  case 7:
	  String s3t = (String) recieved.getObject();
	  Platform.runLater(() -> {
		  ClientRegisterController.acainstance.status.setText(s3t + " Updated!");
	    });
	  break;
  case 8:
	  String s4t = (String) recieved.getObject();
	  Platform.runLater(() -> {
		  CarRegisterController.acainstance.status.setText(s4t + " Updated!");
	    });
	  break;
  case 9:
	  ArrayList<?> IDArrayList =(ArrayList<?>)recieved.getObject();
		 ArrayList<String> bb=(ArrayList<String>)IDArrayList;
		 CarRegisterController.acainstance.IDAcceptor(bb);;
	  break;
  case 10:
	ArrayList<Car>  carArrayList =(ArrayList<Car>)recieved.getObject();
	ArrayList<Car> CARarr=(ArrayList<Car>)carArrayList;
		PurchasePlanController.acainstance.CarAcceptor(carArrayList);
	  break;
  case 11:
	  ArrayList<?> CarArrayList =(ArrayList<?>)recieved.getObject();
		 ArrayList<String> bbb=(ArrayList<String>)CarArrayList;
		 PurchasePlanController.acainstance.Car2Acceptor(bbb);
	  break;
  case 12:
	  String st12 = (String) recieved.getObject();
	  Platform.runLater(() -> {
		  ProfileSettingsController.acainstance.status.setText("");
	    });
	  break;
  }
  }


  /**
   * This method handles all data coming from the UI            
   *
   * @param str The message from the UI.    
   */
  public void handleMessageFromClientUI(Object str)  
  {
    try
    {
    	awaitResponse = true;
    	sendToServer(str);
		// wait for response
		while (awaitResponse) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }
    catch(IOException e)
    {
      clientUI.display("Could not send message to server.  Terminating client.");
      quit();
    }
  }

  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
