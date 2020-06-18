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
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.Rates;
import Entity.Refueling;
import Entity.User;
import client.ClientConsole;
import common.Message;
import common.MyFile;
import gui.LoginController;
import gui.ProfileSettingsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MarketingManagerNewReports implements Initializable{
	
	public static MarketingManagerNewReports acainstance;


    @FXML
    private ComboBox<String> comboReportType;

    @FXML
    private Button btnSend;

	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public ClientConsole details= new ClientConsole("localhost", 5555);
	public ClientConsole chat= new ClientConsole("localhost", 5555);
	ArrayList<String> Report=new ArrayList<String>();
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
	public ArrayList<Refueling> refuelings;
	public ArrayList<User> userClient;

    ObservableList<User> List =FXCollections.observableArrayList(); 

    public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/MarketingManagerNewReports.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	
	
	
    public void UserClientAcceptor(ArrayList<User> cleints) {
    	userClient = (ArrayList<User>)cleints.clone();
    	System.out.println(userClient);
		List.addAll(cleints);
	
		}
	
	public void RefuelingAcceptor(ArrayList<Refueling> ref) {
		refuelings = (ArrayList<Refueling>)ref.clone();
		System.out.println(refuelings);
		}
	
  

    @FXML
    void SelectReport(ActionEvent event) {

    }

    @FXML
    void SendReport(ActionEvent event) {
    	   try {
   	        Path path = Paths.get("C:\\MyFuel\\MyFuelMarketingManagerReports\\");
   	        Path Sendpath = Paths.get("C:\\MyFuel\\MyFuelMarketingManagerReports\\Send\\");
   	        Path Recivepath = Paths.get("C:\\MyFuel\\MyFuelMarketingManagerReports\\Recieve\\");
   			Files.createDirectories(path);
   			Files.createDirectories(Sendpath);
   			Files.createDirectories(Recivepath);
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
           //////////////////////////////////////////////////////////////////////////////
    	if(comboReportType.getValue().equals("Comments Report for Marketing Campaign"))
		{
			File myObj = new File("C:\\MyFuel\\MyFuelMarketingManagerReports\\Send\\Comments Report for Marketing Campaign.txt");
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
				myWriter = new FileWriter("C:\\MyFuel\\MyFuelMarketingManagerReports\\Send\\Comments Report for Marketing Campaign.txt");
				/*for(int i=0;i<refuelings.size();i++) {
				   myWriter.write("OrderID:"+refuelings.get(i).getOrderID()+" "+" Car Number::"+refuelings.get(i).getCarNumber()+" "+" Gas Station:"+refuelings.get(i).getGasStation()+" "+" Address:"+refuelings.get(i).getAddress()+" "+" Gas Type::"+refuelings.get(i).getGasType()+" "+" Rate:"+refuelings.get(i).getRateForLiter()+" "+" Quantity:"+refuelings.get(i).getOwnerID()+" "+" Price:"+refuelings.get(i).getPrice()+" Date:"+refuelings.get(i).getDate()+" "+" Pump:"+refuelings.get(i).getPumpNumber()+"\n");
							}*/
        		      myWriter.close();
        		      System.out.println("Successfully wrote to the file.");
        		      MyFile msg= new MyFile("Comments Report for Marketing Campaign");
        		 	 String location="C:\\MyFuel\\MyFuelMarketingManagerReports\\Recieve\\";
						 String reclocation=location.concat(myObj.getName());
        		      Entity.Files f=new Entity.Files(0, msg.getFileName(), reclocation,"Not Readed");
					  try{
						      File newFile = new File (myObj.getPath());      
						      byte [] mybytearray  = new byte [(int)newFile.length()];
						      FileInputStream fis = new FileInputStream(newFile);
						      BufferedInputStream bis = new BufferedInputStream(fis);			  
						      msg.initArray(mybytearray.length);
						      msg.setSize(mybytearray.length);
						      bis.read(msg.getMybytearray(),0,mybytearray.length);
									//StationManagerReportController.acainstance.details.accept(new Message(60, msg));
									//StationManagerReportController.acainstance.chat.accept(new Message(62, f));
						    }
						catch (Exception e) {
							System.out.println("Error send (Files)msg) to Server");
						}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			}
		else if(comboReportType.getValue().equals("Customer Periodic Characterization Report"))
		{
			 String Gasoline,Deisel,Scooter,StationName,StationAddress;
			for(int i=0;i<userClient.size();i++)
			{
				if(userClient.get(i).getId().equals(refuelings.get(i).getOwnerID()))
				{
				 try {
	      		    	File myObj = new File("C:\\MyFuel\\MyFuelMarketingManagerReports\\Send\\Customer Periodic Characterization Report.txt");
	        		      FileWriter myWriter;
							myWriter = new FileWriter("C:\\MyFuel\\MyFuelMarketingManagerReports\\Send\\Customer Periodic Characterization Report.txt");
						myWriter.write("Customer Periodic Characterization Report\n");
						myWriter.write("\nStation Name is:"+refuelings.get(i).getOrderID()+"\n");
					    myWriter.close();
					    MyFile msg= new MyFile("Customer Periodic Characterization Report.txt");
					 	 String location="C:\\MyFuel\\MyFuelMarketingManagerReports\\Recieve\\";
						 String reclocation=location.concat(myObj.getName());
	        		      Entity.Files f=new Entity.Files(0, msg.getFileName(), reclocation,"Not Readed Marketing");
	        		      MarketingManagerNewReports.acainstance.details.accept(new Message(69, f));
						  try{
							      File newFile = new File (myObj.getPath());      
							      byte [] mybytearray  = new byte [(int)newFile.length()];
							      FileInputStream fis = new FileInputStream(newFile);
							      BufferedInputStream bis = new BufferedInputStream(fis);			  
							      msg.initArray(mybytearray.length);
							      msg.setSize(mybytearray.length);
							      bis.read(msg.getMybytearray(),0,mybytearray.length);
							      MarketingManagerNewReports.acainstance.details.accept(new Message(68, msg));
								 
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
    
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance=this;
		chat.accept(new Message(66, null));
		chat.accept(new Message(67, null));
			Report.add("Comments Report for Marketing Campaign");
			Report.add("Customer Periodic Characterization Report");
			ReportList.addAll(Report);
			comboReportType.setItems(ReportList);
	}

}
