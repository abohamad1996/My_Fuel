package gui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.org.apache.bcel.internal.generic.NEW;

import DBconnection.DBconnector;
import Entity.OrderConfirmation;
import Entity.Rates;
import Entity.Refueling;
import Entity.StationsInventory;
import Entity.User;
import client.ClientConsole;
import common.Message;
import common.MyFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StationManagerReportController implements Initializable {
	
	public static StationManagerReportController acainstance;

    @FXML
    private ComboBox<String> comboReportType;
    @FXML
    private ComboBox<String> comboQuartet;

    @FXML
    private ComboBox<String> comboYear;
 
    String currentYear,DateFrom,DateTo,From,To;
    @FXML
    private Button btnSend;
	ArrayList<String> ReportValues=new ArrayList<String>();
	ArrayList<String> Dates=new ArrayList<String>();
	public ArrayList<Refueling> refuelings;
	public ArrayList<StationsInventory> stations;

    ObservableList<String> Report =FXCollections.observableArrayList(); 
	ArrayList<String> Quarter=new ArrayList<String>();
    ObservableList<String> QuarterList =FXCollections.observableArrayList(); 
	ArrayList<String> Year=new ArrayList<String>();
    ObservableList<String> YearList =FXCollections.observableArrayList(); 
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public ClientConsole details= new ClientConsole("localhost", 5555);
	ArrayList<User> userdetails= new ArrayList<User>();
	ArrayList<String> ReportType=new ArrayList<String>();
	 ObservableList<String> ReportList =FXCollections.observableArrayList(); 
	User detailsUser;
	String gasolineQuantity,gasolinePrices;
	double GasolineQuantity,GasolinePrices;
	String deiselQuantity,deiselPrices;
	double DeiselQuantity,DeiselPrices;
	String scooterQuantity,scooterPrices;
	double ScooterQuantity,ScooterPrices;
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/StationManagerNewReports.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	
	public void RefuelingAcceptor(ArrayList<Refueling> ref) {
		refuelings = (ArrayList<Refueling>)ref.clone();
		//System.out.println(refuelings);
		}
	public void StationAcceptor(ArrayList<StationsInventory> station) {
		stations = (ArrayList<StationsInventory>)station.clone();
		System.out.println(stations);
		}
    @FXML
    void SendReport(ActionEvent event) {
        try {
	        Path path = Paths.get("C:\\MyFuel\\");
	        Path Sendpath = Paths.get("C:\\MyFuel\\Send\\");
	        Path Recivepath = Paths.get("C:\\MyFuel\\Recieve\\");
			Files.createDirectories(path);
			Files.createDirectories(Sendpath);
			Files.createDirectories(Recivepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //////////////////////////////////////////////////////////////////////////////
    			if(comboReportType.getValue().equals("Quarterly Revenue Report"))
        		{
    				File myObj = new File("C:\\MyFuel\\Send\\Quarterly Revenue Report.txt");
        		      FileWriter myWriter;
  					try {
  						myWriter = new FileWriter("C:\\MyFuel\\Send\\Quarterly Revenue Report.txt");
	  					 myWriter.write("\n-----------------------------------------------------------------------------------");

  					  myWriter.write("\nGasoline 95 Details\n");
  						for(int i=0;i<refuelings.size();i++)
  	        			{
  	        				if(refuelings.get(i).getGasType().equals("Gasoline 95")) {
    							
  	        				gasolineQuantity=refuelings.get(i).getQunatity();
  	        				double d=Double.parseDouble(gasolineQuantity);
  	        				GasolineQuantity+=d;
  	        				gasolineQuantity=String.valueOf(GasolineQuantity); 
  	        				gasolinePrices=refuelings.get(i).getPrice();
  	        				double d2=Double.parseDouble(gasolinePrices);
  	        				GasolinePrices+=d2;
  	        				gasolinePrices=String.valueOf(GasolinePrices); 
  							  myWriter.write("OrderID:"+refuelings.get(i).getOrderID()+" "+" Car Number::"+refuelings.get(i).getCarNumber()+" "+" Gas Station:"+refuelings.get(i).getGasStation()+" "+" Address:"+refuelings.get(i).getAddress()+" "+" Gas Type::"+refuelings.get(i).getGasType()+" "+" Quantity:"+refuelings.get(i).getQunatity()+" "+" Price:"+refuelings.get(i).getPrice()+" Date:"+refuelings.get(i).getDate()+"\n");  	        			
  	        				}
  	        			}
	  					  myWriter.write("Gasoline Qunatity is:"+gasolineQuantity);
	  					 myWriter.write("\nGasoline Prices is:"+gasolinePrices);
	  					 myWriter.write("\n-----------------------------------------------------------------------------------");
	  					  myWriter.write("\n\nDeisel Fuel Details\n");
  						for(int i=0;i<refuelings.size();i++)
  	        			{
  							 if(refuelings.get(i).getGasType().equals("Diesel fuel")) {
  		      					myWriter.write("Deisel Fuel Details");
  		          				deiselQuantity=refuelings.get(i).getQunatity();
  		          				double d=Double.parseDouble(deiselQuantity);
  		          				DeiselQuantity+=d;
  		          				deiselQuantity=String.valueOf(DeiselQuantity);  
  		          			deiselPrices=refuelings.get(i).getPrice();
  	        				double d2=Double.parseDouble(deiselPrices);
  	        				DeiselPrices+=d2;
  	        				deiselPrices=String.valueOf(DeiselPrices); 
  								  myWriter.write("OrderID:"+refuelings.get(i).getOrderID()+" "+" Car Number::"+refuelings.get(i).getCarNumber()+" "+" Gas Station:"+refuelings.get(i).getGasStation()+" "+" Address:"+refuelings.get(i).getAddress()+" "+" Gas Type::"+refuelings.get(i).getGasType()+" "+" Quantity:"+refuelings.get(i).getQunatity()+" "+" Price:"+refuelings.get(i).getPrice()+" Date:"+refuelings.get(i).getDate()+"\n");  	        			  
  		      				}
  	        			}
	
	  					  myWriter.write("Deisel Qunatity is:"+deiselQuantity);
	  					 myWriter.write("\nDeisel Prices is:"+deiselPrices);
	  					 myWriter.write("\n-----------------------------------------------------------------------------------");

	  					  myWriter.write("\n\nScooter Fuel Details\n");

	  						for(int i=0;i<refuelings.size();i++)
	  	        			{
	  							 if(refuelings.get(i).getGasType().equals("Scooters fuel")) {
	  		      					myWriter.write("Scooters fuel Fuel Details");
	  		          				scooterQuantity=refuelings.get(i).getQunatity();
	  		          				double d=Double.parseDouble(scooterQuantity);
	  		          				ScooterQuantity+=d;
	  		          				scooterQuantity=String.valueOf(ScooterQuantity);  
	  		          			scooterPrices=refuelings.get(i).getPrice();
	  	        				double d2=Double.parseDouble(scooterPrices);
	  	        				ScooterPrices+=d2;
	  	        				scooterPrices=String.valueOf(ScooterPrices); 
	  								  myWriter.write("OrderID:"+refuelings.get(i).getOrderID()+" "+" Car Number::"+refuelings.get(i).getCarNumber()+" "+" Gas Station:"+refuelings.get(i).getGasStation()+" "+" Address:"+refuelings.get(i).getAddress()+" "+" Gas Type::"+refuelings.get(i).getGasType()+" "+" Quantity:"+refuelings.get(i).getQunatity()+" "+" Price:"+refuelings.get(i).getPrice()+" Date:"+refuelings.get(i).getDate()+"\n");  	        			  
	  		      				}
	  	        			}
		  					  myWriter.write("Scooter Qunatity is:"+scooterQuantity);
		  					 myWriter.write("\nScooter Prices is:"+scooterPrices);
		  					 double allprices;
		  					 allprices=DeiselPrices+GasolinePrices+ScooterPrices;
		  					 
		  					 myWriter.write("\n\n\nAll Prices is:"+allprices);
  		        		      myWriter.close();
  		        		      //////////////////////////send file
  		        		     MyFile msg= new MyFile(comboReportType.getValue()+".txt");
  							  try{
  								      File newFile = new File (myObj.getPath());      
  								      System.out.println(newFile.getPath().toString());
  								      byte [] mybytearray  = new byte [(int)newFile.length()];
  								      FileInputStream fis = new FileInputStream(newFile);
  								      BufferedInputStream bis = new BufferedInputStream(fis);			  
  								      msg.initArray(mybytearray.length);
  								      msg.setSize(mybytearray.length);
  								      bis.read(msg.getMybytearray(),0,mybytearray.length);
  	  								 StationManagerReportController.acainstance.details.accept(new Message(61, msg));
  	   		        
  	   		        		
  								    }
  								catch (Exception e) {
  									System.out.println("Error send (Files)msg) to Server");
  								}
  					} catch (IOException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					}  
        		
        			 
        		}
        		
        		else if(comboReportType.getValue().equals("Purchases Report"))
        		{
        			File myObj = new File("C:\\MyFuel\\Send\\Purchases Report.txt");
      		      try {
						if (myObj.createNewFile()) {
						    System.out.println("File created: " + myObj.getName());
						  } else {
						    System.out.println("File already exists.");
						  }
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
      		      FileWriter myWriter;
					try {
						myWriter = new FileWriter("C:\\MyFuel\\Send\\Purchases Report.txt");
						for(int i=0;i<refuelings.size();i++) {
						   myWriter.write("OrderID:"+refuelings.get(i).getOrderID()+" "+" Car Number::"+refuelings.get(i).getCarNumber()+" "+" Gas Station:"+refuelings.get(i).getGasStation()+" "+" Address:"+refuelings.get(i).getAddress()+" "+" Gas Type::"+refuelings.get(i).getGasType()+" "+" Rate:"+refuelings.get(i).getRateForLiter()+" "+" Quantity:"+refuelings.get(i).getOwnerID()+" "+" Price:"+refuelings.get(i).getPrice()+" Date:"+refuelings.get(i).getDate()+" "+" Pump:"+refuelings.get(i).getPumpNumber()+"\n");
									}
		        		      myWriter.close();
		        		      System.out.println("Successfully wrote to the file.");
		        		      MyFile msg= new MyFile("Purchases Report.txt");
							  try{
								      File newFile = new File (myObj.getPath());      
								      byte [] mybytearray  = new byte [(int)newFile.length()];
								      FileInputStream fis = new FileInputStream(newFile);
								      BufferedInputStream bis = new BufferedInputStream(fis);			  
								      msg.initArray(mybytearray.length);
								      msg.setSize(mybytearray.length);
								      bis.read(msg.getMybytearray(),0,mybytearray.length);
	  								StationManagerReportController.acainstance.details.accept(new Message(60, msg));
								    }
								catch (Exception e) {
									System.out.println("Error send (Files)msg) to Server");
								}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
					

					}
        		else if(comboReportType.getValue().equals("Quantity in stock Report"))
        		{
        			 String Gasoline,Deisel,Scooter,StationName,StationAddress;
        			for(int i=0;i<stations.size();i++)
        			{
        				if(stations.get(i).getManagerIDString().equals(user.getId()))
        				{
        					Gasoline=stations.get(i).getGasolineQuantity();
        					Deisel=stations.get(i).getDieselQuantity();
        					Scooter=stations.get(i).getScooterQuantity();
        					StationName=stations.get(i).getStationName();
        					StationAddress=stations.get(i).getStationAddress();
        				 try {
        	      		    	File myObj = new File("C:\\MyFuel\\Send\\Quantity in stock Report.txt");
        	        		      FileWriter myWriter;
        							myWriter = new FileWriter("C:\\MyFuel\\Send\\Quantity in stock Report.txt");
        						myWriter.write("Quantity in stock Report\n");
        						myWriter.write("\nStation Name is:"+StationName);
        						myWriter.write("\nStation Address is:"+StationAddress);
        						myWriter.write("\nGasoline:"+ Gasoline+" Liters");
        						myWriter.write("\nDeisel:"+ Deisel+" Liters");
        						myWriter.write("\nScooter:"+ Scooter+" Liters");
        					    myWriter.close();
        					    MyFile msg= new MyFile("Quantity in stock Report.txt");
  							  try{
  								      File newFile = new File (myObj.getPath());      
  								      byte [] mybytearray  = new byte [(int)newFile.length()];
  								      FileInputStream fis = new FileInputStream(newFile);
  								      BufferedInputStream bis = new BufferedInputStream(fis);			  
  								      msg.initArray(mybytearray.length);
  								      msg.setSize(mybytearray.length);
  								      bis.read(msg.getMybytearray(),0,mybytearray.length);
  									 StationManagerReportController.acainstance.details.accept(new Message(58, msg));
  									
  							  }
  								catch (Exception e) {
  									System.out.println("Error send (Files)msg) to Server");
  								}
        					} catch (IOException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					}
        				}
        	        			}
        			}
        			}
  
    @FXML
    void SelectReport(ActionEvent event) {
    	
    }
    @FXML
    void SelectQuartet(ActionEvent event) {
    	if(comboQuartet.getValue().equals("Quartet 1 (Between 1-1 To 30-3)"))
    			{
    			DateFrom="01-01";
    			DateTo="03-31";
    			}
    	else if(comboQuartet.getValue().equals("Quartet 2 (Between 1-4 To 31-6)"))
		{
    			DateFrom="04-01";
				DateTo="06-31";
		}
    	else if(comboQuartet.getValue().equals("Quartet 3 (Between 1-7 To 30-9)"))
		{
    			DateFrom="07-01";
    			DateTo="09-31";
		}
    	else if(comboQuartet.getValue().equals("Quartet 4 (Between 1-10 To 31-12)"))
		{
    			DateFrom="10-01";
    			DateTo="12-31";
		}
    	
    }

    @FXML
    void SelectYear(ActionEvent event) {
    	currentYear=comboYear.getValue()+"-";
    	From=currentYear.concat(DateFrom);
		To=currentYear.concat(DateTo);
		Dates.add(From);
		Dates.add(To);
		StationManagerReportController.acainstance.details.accept(new Message(57, Dates));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance=this;
		details.accept(new Message(59, null));
		ReportType.add("Quarterly Revenue Report");
		ReportType.add("Purchases Report");
		ReportType.add("Quantity in stock Report");
		Report.addAll(ReportType);
		comboReportType.setItems(Report);
		Year.add("2019");
		Year.add("2020");
		YearList.addAll(Year);
		comboYear.setItems(YearList);
		Quarter.add("Quartet 1 (Between 1-1 To 30-3)");
		Quarter.add("Quartet 2 (Between 1-4 To 31-6)");
		Quarter.add("Quartet 3 (Between 1-7 To 30-9)");
		Quarter.add("Quartet 4 (Between 1-10 To 31-12)");
		QuarterList.addAll(Quarter);
		comboQuartet.setItems(QuarterList);
		}

}
