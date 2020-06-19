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
import Entity.Sales;
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
	public ClientConsole sales= new ClientConsole("localhost", 5555);

	ArrayList<String> Report=new ArrayList<String>();
	ArrayList<User> userdetails= new ArrayList<User>();
	ArrayList<String> ReportType=new ArrayList<String>();
	ArrayList<Refueling> ref=new ArrayList<Refueling>();

	 ObservableList<String> ReportList =FXCollections.observableArrayList(); 
	User detailsUser;
	String gasolineQuantity,gasolinePrices;
	double GasolineQuantity,GasolinePrices;
	String deiselQuantity,deiselPrices;
	double DeiselQuantity,DeiselPrices;
	String scooterQuantity,scooterPrices;
	double ScooterQuantity,ScooterPrices;
	public ArrayList<Refueling> refuelings;
	Refueling refu;
	
	public ArrayList<User> userClient;
	public ArrayList<Sales> getSaless;
	String ID;

    ObservableList<User> List =FXCollections.observableArrayList(); 
    ObservableList<Sales> ListSales =FXCollections.observableArrayList(); 

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
	
	
	
    public void SalesAcceptor(ArrayList<Sales> salse) {
    	getSaless = (ArrayList<Sales>)salse.clone();
    	System.out.println(getSaless);
    	ListSales.addAll(getSaless);
		}
    
    public void UserClientAcceptor(ArrayList<User> cleints) {
    	userClient = (ArrayList<User>)cleints.clone();
    	//System.out.println(userClient);
		List.addAll(cleints);
		}
	
	public void RefuelingAcceptor(ArrayList<Refueling> ref) {
		refuelings = (ArrayList<Refueling>)ref.clone();
		System.out.println(refuelings);
		}
	
 
    @FXML
    void SelectReport(ActionEvent event) {
    	for(int i=0;i<refuelings.size();i++)
    	{
    		System.out.println(	refuelings.get(i).toString());
    
    	}
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
		      int saleid;
		      int count=0;
				double Total=0;
				double TotalClient=0;
			try {
				myWriter = new FileWriter("C:\\MyFuel\\MyFuelMarketingManagerReports\\Send\\Comments Report for Marketing Campaign.txt");
				for(int i=0;i<getSaless.size();i++) {
					myWriter.write("-----------------------------------------------------------------------------------\n");
					myWriter.write("-----------------------------------------------------------------------------------");
					myWriter.write("\nSales Number" +getSaless.get(i).getIDsales()+"\n\n");
					for(int j=0;j<refuelings.size();j++) {
						double d=Double.parseDouble(refuelings.get(j).getPrice());

					//System.out.println(refuelings.get(j).getSaleID());
					saleid=Integer.valueOf(refuelings.get(j).getSaleID());
				//System.out.println(saleid);
					if(saleid==getSaless.get(i).getIDsales())
					{
						count++;
						Total+=d;
					}
					}
						myWriter.write("Number of customers in this sale:"+count+"\n");		
						myWriter.write("Total money in ths sale:"+Total+"\n");	
						myWriter.write("-----------------------------------------------------------------------------------\n");
						Total=0;
						count=0;
						}
				/////////////////
				double Pay = 0,b;
				String saleid2,id = null;
				for(int i=0;i<getSaless.size();i++)
				{
					myWriter.write("\nSales Number" +getSaless.get(i).getIDsales()+"\n\n");
					saleid2=String.valueOf(getSaless.get(i).getIDsales());
			
					for(int j=0;j<refuelings.size();j++)
					{
						
						if(saleid2.equals(refuelings.get(j).getSaleID()))
						{
							refu=refuelings.get(j);
							ref.add(refu);
							System.out.println("-----"+refu);
							b=Double.valueOf(refu.getPrice());
							id=refuelings.get(j).getOwnerID();
							
							if(id.equals(refu.getOwnerID()))
							{
								Pay+=b;
							}
							myWriter.write("ID:"+id+" "+"Number of customers in this sale:"+Pay+"\n");
							
						}
						Pay=0;
						}
				}
				

				
				
				
	
        		      myWriter.close();
        		      System.out.println("Successfully wrote to the file.");
        		      MyFile msg= new MyFile("Comments Report for Marketing Campaign.txt");
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
		else if(comboReportType.getValue().equals("Customer Periodic Characterization Report"))
		{
				 try {
					 String iDString = null,id = null,station = null;
					 double pay = 0;
	      		    	File myObj = new File("C:\\MyFuel\\MyFuelMarketingManagerReports\\Send\\Customer Periodic Characterization Report.txt");
	        		      FileWriter myWriter;
							myWriter = new FileWriter("C:\\MyFuel\\MyFuelMarketingManagerReports\\Send\\Customer Periodic Characterization Report.txt");
						myWriter.write("Customer Periodic Characterization Report\n");
						for(int m=0;m<refuelings.size()-1;m++)
							{
							iDString=refuelings.get(m).getOwnerID();
							int count=0;
						if(refuelings.get(m).getGasStation().equals("Yellow Station") && iDString.equals(refuelings.get(m+1).getOwnerID())) {
							station="Yellow Station";
								for(int z=0;z<refuelings.size()-1;z++)
								{
									if(iDString.equals(refuelings.get(z+1).getOwnerID()))
									count++;
									refuelings.remove(z);
								}
							System.out.println(count);
							count=0;
						}
							}
					
						//////////////////////////////////////////////////////////////////////////////
						
						
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
    
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance=this;
		chat.accept(new Message(66, null));
		chat.accept(new Message(67, null));
		chat.accept(new Message(75, null));
			Report.add("Comments Report for Marketing Campaign");
			Report.add("Customer Periodic Characterization Report");
			ReportList.addAll(Report);
			comboReportType.setItems(ReportList);
	}

}
