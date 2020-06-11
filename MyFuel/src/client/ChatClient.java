// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import client.*;
import common.ChatIF;
import common.Message;
import gui.EmployeeFrameController;
import gui.HomeHeatingOrderController;
import gui.HomeHeatingOrderTrackController;
import gui.LoginController;
import gui.MarketingManagerController;
import gui.MarketingManagerRateController;
import gui.MarketingRepresentativeController;
import gui.NetworkManagerApproveRatesController;
import gui.NetworkManagerController;
import gui.ProfileSettingsController;
import gui.PurchasePlanController;
import gui.RefuelingController;
import gui.RepresentativeTransportController;
import gui.RepresentativeTransportSetMaxPrice;
import gui.StaionManagerController;
import gui.StationManagerInventoryController;
import gui.StationManagerOrderConfirmationController;
import gui.CarRegisterController;
import gui.ClientController;
import gui.ClientRegisterController;
import gui.Employee;
import gui.UpdateRoleController;
import gui.UserHomeController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import Entity.HomeHeatingOrder;
import Entity.Inventory;
import Entity.OrderConfirmation;
import Entity.Rates;
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
		case "Marketing Manager":
			System.out.println("Marketing Manager");
			MarketingManagerController Manager=new MarketingManagerController();
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
			StaionManagerController StationManager=new StaionManagerController();
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
		case "Network Manager":
			System.out.println("Network");
			NetworkManagerController Network=new NetworkManagerController();
			Network.start(user);
			try {
				user=DBconnector.StatusLoginUpdate(DBconnector.getConnection(), user.getUsername(), user.getPassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  break;
		case "Representative Transport":
			System.out.println("Representative Transport");
			RepresentativeTransportController RepresentativeTransport=new RepresentativeTransportController();
			RepresentativeTransport.start(user);
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
  case 13:
	  ArrayList<?> InventoryArrayList =(ArrayList<?>)recieved.getObject();
		 ArrayList<Inventory> Inv=(ArrayList<Inventory>)InventoryArrayList;
		 StationManagerInventoryController.acainstance.FuelAcceptor(Inv);
	  break;
  case 14:
	  Inventory stInv = (Inventory) recieved.getObject();
	  Platform.runLater(() -> {
		  StationManagerInventoryController.acainstance.NewFuelAcceptor(stInv);
	    });
	  break;
  case 15:
	ArrayList<Car>  car2ArrayList =(ArrayList<Car>)recieved.getObject();
	ArrayList<Car> CAR2arr=(ArrayList<Car>)car2ArrayList;
	RefuelingController.acainstance.CarAcceptor(car2ArrayList);
	  break;
  case 16:
	  String strRates = (String) recieved.getObject();
	  Platform.runLater(() -> {
		  MarketingManagerRateController.acainstance.status.setText(strRates + " Updated!");
	    });
	  break;
  case 17:
	  ArrayList<?> RatesArrayList =(ArrayList<?>)recieved.getObject();
		 ArrayList<Rates> rates=(ArrayList<Rates>)RatesArrayList;
		 NetworkManagerApproveRatesController.acainstance.RatesAcceptor(rates);
		 break;
  case 18:
	  String strNewRates = (String) recieved.getObject();
	  Platform.runLater(() -> {
		  NetworkManagerApproveRatesController.acainstance.status.setText("");
	    });
	  break;
  case 19:
	  String strNewRatesConfirm = (String) recieved.getObject();
	  Platform.runLater(() -> {
		  NetworkManagerApproveRatesController.acainstance.status.setText("");
	    });
	  break;
  case 20:
	  String strNewRatesNotConfirm = (String) recieved.getObject();
	  Platform.runLater(() -> {
		  NetworkManagerApproveRatesController.acainstance.status.setText("");
	    });
	  break;
  case 21:
	  ArrayList<?> RatesArrayListAlert =(ArrayList<?>)recieved.getObject();
		 ArrayList<Rates> ratesAlert=(ArrayList<Rates>)RatesArrayListAlert;
		NetworkManagerController.acainstance.RatesAcceptor(ratesAlert);
		 break;
  case 22:
	 String homeHeatingStringRate=(String)recieved.getObject();
	 //System.out.println("home:"+homeHeatingStringRate);
		HomeHeatingOrderController.acainstance.HomeHeatingRatesAcceptor(homeHeatingStringRate);
		 break;
  case 23:
	  String strHomeHeating = (String) recieved.getObject();
	  Platform.runLater(() -> {
		  HomeHeatingOrderController.acainstance.status.setText(strHomeHeating + " Updated!");
	    });
	  break;
  case 24:
	  ArrayList<?> HomeHeatingOrderArrayList =(ArrayList<?>)recieved.getObject();
		 ArrayList<String> bbbb=(ArrayList<String>)HomeHeatingOrderArrayList;
		 HomeHeatingOrderTrackController.acainstance.HomeHeatingOrderAcceptor(bbbb);
	  break;
  case 25:
	ArrayList<HomeHeatingOrder>  HomeheatingArrayList =(ArrayList<HomeHeatingOrder>)recieved.getObject();
	ArrayList<HomeHeatingOrder> Homeheatingarr=(ArrayList<HomeHeatingOrder>)HomeheatingArrayList;
	HomeHeatingOrderTrackController.acainstance.HomeHeatingOrderAccept(Homeheatingarr);
	  break;
  case 26:
	  ArrayList<?> orderconfirmatioArrayList =(ArrayList<?>)recieved.getObject();
	  ArrayList<OrderConfirmation> orderConfirmations=(ArrayList<OrderConfirmation>)orderconfirmatioArrayList;
	 // System.out.println(""+orderconfirmatioArrayList.get(0));
	  StationManagerOrderConfirmationController.acainstance.OrderConfirmationAcceptor(orderConfirmations);
	  break;
  case 27:
	  ArrayList<?> OrderArrayListAlert =(ArrayList<?>)recieved.getObject();
		 ArrayList<OrderConfirmation> OrderAlert=(ArrayList<OrderConfirmation>)OrderArrayListAlert;
		 StaionManagerController.acainstance.OrderAcceptor(OrderAlert);
		 break;
  case 28:
	  Rates strMaxPrice = (Rates) recieved.getObject();
	  Platform.runLater(() -> {
		  RepresentativeTransportSetMaxPrice.acainstance.status.setText("Done"+strMaxPrice);
	    });
	  break;
  case 29:
	  ArrayList<?> MaxPriceArrayList =(ArrayList<?>)recieved.getObject();
		 ArrayList<Rates> MaxPrice=(ArrayList<Rates>)MaxPriceArrayList;
		 RepresentativeTransportSetMaxPrice.acainstance.RatesAcceptor(MaxPrice);
		 break;
  case 30:
	  ArrayList<?> MaxPriceArrayListMarkiting =(ArrayList<?>)recieved.getObject();
		 ArrayList<Rates> MaxPriceMarkiting=(ArrayList<Rates>)MaxPriceArrayListMarkiting;
		 MarketingManagerRateController.acainstance.RatesAcceptor(MaxPriceMarkiting);
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
