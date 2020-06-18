// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import client.*;
import common.ChatIF;
import common.Message;
import common.MyFile;
import gui.EmployeeFrameController;
import gui.HomeHeatingOrderController;
import gui.HomeHeatingOrderTrackController;
import gui.LoginController;
import gui.MarketingManagerController;
import gui.MarketingManagerNewReports;
import gui.MarketingManagerRateController;
import gui.MarketingManagerRecieveReports;
import gui.MarketingManagerSaleController;
import gui.MarketingRepresentativeController;
import gui.NetworkManagerApproveRatesController;
import gui.NetworkManagerController;
import gui.NetworkManagerReciveReportsController;
import gui.ProfileSettingsController;
import gui.PurchasePlanController;
import gui.RefuelingController;
import gui.RepresentativeTransportController;
import gui.RepresentativeTransportSetMaxPrice;
import gui.StaionManagerController;
import gui.StationDetailsController;
import gui.StationManagerInventoryController;
import gui.StationManagerOrderCompletedController;
import gui.StationManagerOrderConfirmationController;
import gui.StationManagerReportController;
import gui.SupplierController;
import gui.SupplierOrderController;
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
import Entity.Files;
import Entity.HomeHeatingOrder;
import Entity.Inventory;
import Entity.OrderConfirmation;
import Entity.Rates;
import Entity.Refueling;
import Entity.Sales;
import Entity.StationsInventory;
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
	 		 System.out.println(user); 	 
	 		 System.out.println("aaaaaaaaaaaaa");
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
		case "Supplier":
			System.out.println("Supplier");
			SupplierController supplier=new SupplierController();
			supplier.start(user);
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
		 ArrayList<StationsInventory> Inv=(ArrayList<StationsInventory>)InventoryArrayList;
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
  case 31:
	  ArrayList<?> InventoryOrdersArrayList =(ArrayList<?>)recieved.getObject();
		 ArrayList<StationsInventory> InvOrders=(ArrayList<StationsInventory>)InventoryOrdersArrayList;
		 HomeHeatingOrderController.acainstance.FuelAcceptor(InvOrders);
	  break;
  case 32:
	  Inventory strHomeHeatingOrderInv = (Inventory) recieved.getObject();
	  Platform.runLater(() -> {
		//  StationManagerInventoryController.acainstance.NewFuelAcceptor(stInv);
	    });
	  break;
  case 33:
	  ArrayList<?> InventoryArrayListStationManager =(ArrayList<?>)recieved.getObject();
		 ArrayList<StationsInventory> InvStationManager=(ArrayList<StationsInventory>)InventoryArrayListStationManager;
		 StaionManagerController.acainstance.FuelAcceptor(InvStationManager);
	  break;
  case 34:
	  ArrayList<?> InventoryArrayListStationManagerList =(ArrayList<?>)recieved.getObject();
		 ArrayList<StationsInventory> InvStationManagerList=(ArrayList<StationsInventory>)InventoryArrayListStationManagerList;
		 StationDetailsController.acainstance.FuelAcceptor(InvStationManagerList);
	  break;
  case 35:
	  ArrayList<?> RatesArray =(ArrayList<?>)recieved.getObject();
		 ArrayList<Rates> rate=(ArrayList<Rates>)RatesArray;
		 CarRegisterController.acainstance.RatesAcceptor(rate);
	  break;
  case 36:
	ArrayList<Car>  carArrayRates =(ArrayList<Car>)recieved.getObject();
	ArrayList<Car> CARRates=(ArrayList<Car>)carArrayRates;
		PurchasePlanController.acainstance.CarAcceptor(CARRates);
	  break;
	  
  case 37:
	  ArrayList<?> StationArray =(ArrayList<?>)recieved.getObject();
		 ArrayList<StationsInventory> station=(ArrayList<StationsInventory>)StationArray;
		 RefuelingController.acainstance.StationAcceptor(station);
	  break;
  case 38:
	  String GasStationID = (String) recieved.getObject();
	  Platform.runLater(() -> {
		  RefuelingController.acainstance.StationIDAcceptor(GasStationID);
	    });
	  break;
	  case 39:
		  Inventory Gasoline = (Inventory) recieved.getObject();
		  Platform.runLater(() -> {
			//  StationManagerInventoryController.acainstance.NewFuelAcceptor(stInv);
		    });
		  break;
	  case 40:
		  Inventory Deisel = (Inventory) recieved.getObject();
		  Platform.runLater(() -> {
			//  StationManagerInventoryController.acainstance.NewFuelAcceptor(stInv);
		    });
		  break;
	  case 41:
		  Inventory Scooter = (Inventory) recieved.getObject();
		  Platform.runLater(() -> {
			//  StationManagerInventoryController.acainstance.NewFuelAcceptor(stInv);
		    });
		  break;
		  case 42:
			  ArrayList<?> salesmarketingmanagerArrayList =(ArrayList<?>)recieved.getObject();
				 ArrayList<Sales>  salmarketingmanagerList=(ArrayList<Sales>)salesmarketingmanagerArrayList;
				 MarketingManagerSaleController.acainstance.FuelAcceptor(salmarketingmanagerList);
			  break;
		  case 43:
			  String Refuling = (String) recieved.getObject();
			  Platform.runLater(() -> {
				  //HomeHeatingOrderController.acainstance.status.setText(Refuling + " Updated!");
			    });
			  break;
		  case 44:
			  String Order = (String) recieved.getObject();
			  Platform.runLater(() -> {
				 // ClientRegisterController.acainstance.status.setText(Order + " Updated!");
			    });
			  break;
		  case 45:
			  ArrayList<?> OrderConfirmSupplier =(ArrayList<?>)recieved.getObject();
			  ArrayList<OrderConfirmation> orderConfirmation=(ArrayList<OrderConfirmation>)OrderConfirmSupplier;
			 // System.out.println(""+orderconfirmatioArrayList.get(0));
			  SupplierOrderController.acainstance.OrderConfirmationAcceptor(orderConfirmation);
			  break;
		  case 46:
			  String StationManagerConfirm = (String) recieved.getObject();
			  Platform.runLater(() -> {
				 // NetworkManagerApproveRatesController.acainstance.status.setText("");
			    });
			  break;
		  case 47:
			  String StationManagerNotConfirm = (String) recieved.getObject();
			  Platform.runLater(() -> {
				 // NetworkManagerApproveRatesController.acainstance.status.setText("");
			    });
			  break;
		  case 48:
			  ArrayList<?> OrderRefuelingArrayListAlert =(ArrayList<?>)recieved.getObject();
				 ArrayList<OrderConfirmation> OrderRefuelingAlert=(ArrayList<OrderConfirmation>)OrderRefuelingArrayListAlert;
				 SupplierController.acainstance.OrderAcceptor(OrderRefuelingAlert);
				 break;
		  case 49:
			  String SupplierConfirm = (String) recieved.getObject();
			  Platform.runLater(() -> {
				 // NetworkManagerApproveRatesController.acainstance.status.setText("");
			    });
			  break;
		  case 50:
			  String SupplierNotConfirm = (String) recieved.getObject();
			  Platform.runLater(() -> {
				 // NetworkManagerApproveRatesController.acainstance.status.setText("");
			    });
			  break;
		  case 51:
			  String GasolineThreshould = (String) recieved.getObject();
			  Platform.runLater(() -> {
				//  StationManagerInventoryController.acainstance.NewFuelAcceptor(stInv);
			    });
			  break;
		  case 52:
			  String DeiselThreshould = (String) recieved.getObject();
			  Platform.runLater(() -> {
				//  StationManagerInventoryController.acainstance.NewFuelAcceptor(stInv);
			    });
			  break;
		  case 53:
			  String ScooterThreshould = (String) recieved.getObject();
			  Platform.runLater(() -> {
				//  StationManagerInventoryController.acainstance.NewFuelAcceptor(stInv);
			    });
			  break;
		  case 54:
			  ArrayList<?> OrderDoneArrayListAlert =(ArrayList<?>)recieved.getObject();
				 ArrayList<OrderConfirmation> OrderDoneAlert=(ArrayList<OrderConfirmation>)OrderDoneArrayListAlert;
				 StaionManagerController.acainstance.OrderDoneAcceptor(OrderDoneAlert);
				 break;
		  case 55:
			  ArrayList<?> OrderDoneArrayListTable =(ArrayList<?>)recieved.getObject();
				 ArrayList<OrderConfirmation> OrderDoneTable=(ArrayList<OrderConfirmation>)OrderDoneArrayListTable;
				 StationManagerOrderCompletedController.acainstance.OrderDoneAcceptor(OrderDoneTable);
				 break;
		  case 56:
			  String StationManagerSeen = (String) recieved.getObject();
			  Platform.runLater(() -> {
				 // NetworkManagerApproveRatesController.acainstance.status.setText("");
			    });
			  break;
		  case 57:
			  ArrayList<Refueling> Ref = (ArrayList<Refueling>) recieved.getObject();
			  Platform.runLater(() -> {
				  StationManagerReportController.acainstance.RefuelingAcceptor(Ref);
			    });
			  break;
		  case 58:
			  MyFile file;
				file=(MyFile) recieved.getObject();
				  int fileSize =((MyFile)file).getSize(); 
				  String LocalfilePath="C:\\MyFuel\\Recieve\\";
				  String filelocation=LocalfilePath.concat(file.getFileName());
				      File newFile = new File (filelocation); 
				      System.out.println(filelocation);
				      System.out.println(fileSize);
				      System.out.println(file.getMybytearray().length);
				      OutputStream fis;
						try {
							fis =new FileOutputStream(newFile);
							BufferedOutputStream bis = new BufferedOutputStream(fis);
								bis.write(file.getMybytearray(),0, fileSize);
						} catch (  IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			break;
		  case 59:
			  ArrayList<?> StationDetails =(ArrayList<?>)recieved.getObject();
				 ArrayList<StationsInventory> stationdetails=(ArrayList<StationsInventory>)StationDetails;
				 StationManagerReportController.acainstance.StationAcceptor(stationdetails);
				 break;
		  case 60:
			  MyFile file2;
				file2=(MyFile) recieved.getObject();
				  int fileSize2 =((MyFile)file2).getSize(); 
				  String LocalfilePath2="C:\\MyFuel\\Recieve\\";
				  String filelocation2=LocalfilePath2.concat(file2.getFileName());
				      File newFile2 = new File (filelocation2); 
				      System.out.println(filelocation2);
				      System.out.println(fileSize2);
				      System.out.println(file2.getMybytearray().length);
				      OutputStream fis2;
						try {
							fis2 =new FileOutputStream(newFile2);
							BufferedOutputStream bis2 = new BufferedOutputStream(fis2);
								bis2.write(file2.getMybytearray(),0, fileSize2);
						} catch (  IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			break;
		  case 61:
			  MyFile file3;
				file3=(MyFile) recieved.getObject();
				  int fileSize3 =((MyFile)file3).getSize(); 
				  String LocalfilePath3="C:\\MyFuel\\Recieve\\";
				  String filelocation3=LocalfilePath3.concat(file3.getFileName());
				      File newFile3 = new File (filelocation3); 
				     
				      OutputStream fis3;
						try {
							fis3 =new FileOutputStream(newFile3);
							BufferedOutputStream bis3 = new BufferedOutputStream(fis3);
								bis3.write(file3.getMybytearray(),0, fileSize3);
						} catch (  IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			break;
		  case 62:
			  String filereader = (String) recieved.getObject();
			  Platform.runLater(() -> {
				//  ClientRegisterController.acainstance.status.setText(filereader + " Updated!");
			    });
			  break;
		  case 63:
			  ArrayList<Files> files = (ArrayList<Files>) recieved.getObject();
			  Platform.runLater(() -> {
				  NetworkManagerReciveReportsController.acainstance.FilesAcceptor(files);
			    });
			  break;
		  case 64:
			  ArrayList<Files> filesdetect = (ArrayList<Files>) recieved.getObject();
			  Platform.runLater(() -> {
				  NetworkManagerController.acainstance.FilesAcceptor(filesdetect);
			    });
			  break;
		  case 65:
			  String readedfile = (String) recieved.getObject();
			  Platform.runLater(() -> {
				 // NetworkManagerApproveRatesController.acainstance.status.setText("");
			    });
			  break;
		  case 66:
			  ArrayList<User> clientDB = (ArrayList<User>) recieved.getObject();
			  Platform.runLater(() -> {
				  MarketingManagerNewReports.acainstance.UserClientAcceptor(clientDB);
			    });
			  break;
		  case 67:
			  ArrayList<Refueling> Refueling = (ArrayList<Refueling>) recieved.getObject();
			  Platform.runLater(() -> {
				  MarketingManagerNewReports.acainstance.RefuelingAcceptor(Refueling);
			    });
			  break;
		  case 68:
			  MyFile file4;
				file4=(MyFile) recieved.getObject();
				  int fileSize4 =((MyFile)file4).getSize(); 
				  String LocalfilePath4="C:\\MyFuel\\Recieve\\";
				  String filelocation4=LocalfilePath4.concat(file4.getFileName());
				      File newFile4 = new File (filelocation4); 
				      System.out.println(filelocation4);
				      System.out.println(fileSize4);
				      System.out.println(file4.getMybytearray().length);
				      OutputStream fis4;
						try {
							fis4 =new FileOutputStream(newFile4);
							BufferedOutputStream bis4 = new BufferedOutputStream(fis4);
								bis4.write(file4.getMybytearray(),0, fileSize4);
						} catch (  IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			break;
		  case 69:
			  String filereaderMarketing = (String) recieved.getObject();
			  Platform.runLater(() -> {
				//  ClientRegisterController.acainstance.status.setText(filereader + " Updated!");
			    });
			  break;
		  case 70:
			  ArrayList<Files> filesMarketing = (ArrayList<Files>) recieved.getObject();
			  Platform.runLater(() -> {
				  MarketingManagerRecieveReports.acainstance.FilesAcceptor(filesMarketing);
			    });
			  break;
		  case 71:
			  String readedfileMarketing = (String) recieved.getObject();
			  Platform.runLater(() -> {
				 // NetworkManagerApproveRatesController.acainstance.status.setText("");
			    });
			  break;
		  case 72:
			  ArrayList<?> actualPriceArrayListMarkiting =(ArrayList<?>)recieved.getObject();
				 ArrayList<Rates> actualPriceMarkiting=(ArrayList<Rates>)actualPriceArrayListMarkiting;
				 MarketingManagerRateController.acainstance.actualRatesAcceptor(actualPriceMarkiting);
				 break;
				 
		  case 73:
			  Sales offer =(Sales)recieved.getObject();
			  Sales SALE=(Sales)offer;
				 RefuelingController.acainstance.checkForSaleAcceptor(SALE);
			  break;
			  
		  case 74:
			  String strEndSale = (String) recieved.getObject();
			  Platform.runLater(() -> {
				 
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
