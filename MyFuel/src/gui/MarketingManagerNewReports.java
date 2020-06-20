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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.Rates;
import Entity.Refueling;
import Entity.Sales;
import Entity.User;
import Entity.maxbuy;
import client.ClientConsole;
import common.Message;
import common.MyFile;
import gui.LoginController;
import gui.ProfileSettingsController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * 
 * This class represents a controller for the gui MarketingManagerNewReports
 *
 */

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

	ArrayList<maxbuy> maxP=new ArrayList<maxbuy>();
	ArrayList<maxbuy> maxT=new ArrayList<maxbuy>();

ArrayList<maxbuy> max=new ArrayList<maxbuy>();

	maxbuy teMaxbuy ;

	public ArrayList<User> userClient;
	public ArrayList<Sales> getSaless;
	String ID;

    ObservableList<User> List =FXCollections.observableArrayList(); 
    ObservableList<Sales> ListSales =FXCollections.observableArrayList(); 
    /** This methode load the information into the gui
   	*
   	* @param splitpane this parameter form the type 
   	* @param user this paramater from the type user that contains id,firtstname,lastname,email,username,password,rank,clienttype,status,image
   	* @param userJob this parameter from the type string 
   	*/

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
	
    LocalDateTime now = LocalDateTime.now();  
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");  
    String formatDateTime = now.format(format);
    /**
	 * this method get array of type sales from sql table
	 * @param salse for type ArrayList
	 */

    public void SalesAcceptor(ArrayList<Sales> salse) {
    	getSaless = (ArrayList<Sales>)salse.clone();
    	System.out.println(getSaless);
    	ListSales.addAll(getSaless);
		}
    /**
     * this method get array of type User from sql table
     * @param cleints for type ArrayLis
     */

    public void UserClientAcceptor(ArrayList<User> cleints) {
    	userClient = (ArrayList<User>)cleints.clone();
    	//System.out.println(userClient);
		List.addAll(cleints);
		}
    
    /**
	 * this method get array of type Refueling from sql table
	 * @param ref for type ArrayLis
	 */

	public void RefuelingAcceptor(ArrayList<Refueling> ref) {
		refuelings = (ArrayList<Refueling>)ref.clone();
		System.out.println(refuelings);
		}
	
	/**
	  * this method select the report button 
	  * @param event of button 
	  */

    @FXML
    void SelectReport(ActionEvent event) {
    	for(int i=0;i<refuelings.size();i++)
    	{
    		System.out.println(	refuelings.get(i).toString());
    
    	}
    }
    /**
     * this method for sendreports the MarketingManager creat the reports 
     * @param event of button 
     */

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
    	   if(comboReportType.getSelectionModel().isEmpty())
    	   
    	   {
    		   Platform.runLater(new Runnable() {
    				@Override
    				public void run() {
    					Alert alert = new Alert(AlertType.INFORMATION);
    					alert.setAlertType(AlertType.INFORMATION); 
    					alert.setContentText("There is missing field");
    					alert.show(); 
    				}
    			});
    		   
    	   }
           //////////////////////////////////////////////////////////////////////////////
    	   else  if(comboReportType.getSelectionModel().isEmpty())
    	   {
    	
    			Platform.runLater(new Runnable() {
    				@Override
    				public void run() {
    					Alert alert = new Alert(AlertType.INFORMATION);
    					alert.setAlertType(AlertType.INFORMATION); 
    					alert.setContentText("There is missing field");
    					alert.show(); 
    				}
    			});
    	   }
    	   
    	   else if(comboReportType.getValue().equals("Comments Report for Marketing Campaign"))
		{
    		String path="C:\\MyFuel\\MyFuelMarketingManagerReports\\Send\\";
			String filename="Comments Report for Marketing Campaign";
			String nameString=path.concat(filename+" "+formatDateTime+".txt");
			System.out.println("file name is:"+nameString);
			File myObj = new File(nameString);
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
				
				myWriter = new FileWriter(nameString);
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
        		      MyFile msg= new MyFile(filename+" "+formatDateTime+".txt");
        		 	 String location="C:\\MyFuel\\MyFuelMarketingManagerReports\\Recieve\\";
						 String reclocation=location.concat(filename+" "+formatDateTime+".txt");
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
						     
						      Platform.runLater(new Runnable() {
									@Override
									public void run() {
										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setAlertType(AlertType.INFORMATION); 
										alert.setContentText("Report send successfully!");
										alert.show(); 
									}
								});
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
						String path="C:\\\\MyFuel\\\\MyFuelMarketingManagerReports\\\\Send\\\\";
	    				String filename="Customer Periodic Characterization Report";
	    				String nameString=path.concat(filename+" "+formatDateTime+".txt");
	    				System.out.println("file name is:"+nameString);
	    				File myObj = new File(nameString);
					 ///////////////////
					 String iDString = null,id = null,station = null;
					 double pay = 0;
	        		      FileWriter myWriter;
							myWriter = new FileWriter(nameString);
						myWriter.write("Customer Periodic Characterization Report\n");
//////////////////////////////////////////////////////////////////////////////////////////////////
/*Yellow Station*/
//////////////////////////////////////////////////////////////////////////////////////////////////
						System.out.println("*********Yellow Station*********");
						int count=0;
						String stationY="Yellow Station";
						for(int m=0;m<refuelings.size();m++) {
							iDString=refuelings.get(m).getOwnerID();
							if (refuelings.get(m).getGasStation().equals(stationY))
							{	
								count++;
								for(int z=m+1;z<refuelings.size();z++)
								{
									if (iDString.equals(refuelings.get(z).getOwnerID())&&refuelings.get(z).getGasStation().equals(stationY)) {
										count++;
	//refuelings.remove(z);
									}
								}

								//System.out.println(iDString);
								teMaxbuy=new maxbuy(iDString, count);
								//	System.out.println(teMaxbuy);
								max.add(teMaxbuy);


							}
								//System.out.println(max);
							count=0;					
						}
						//System.out.println(max);

						for (int x = 0; x < max.size(); x++) {
							id=max.get(x).getId();
							for (int b = x+1; b < max.size(); b++) {
								if (id.equals(max.get(b).getId())) {
									max.remove(b);
									b--;
								}
							}
						}

						System.out.println("******************");
						System.out.println(max);
						myWriter.write("-----------------------------------------------------"+stationY+"-----------------------------------------------------\n");
						for(int r=0;r<max.size();r++)
						{
						myWriter.write(max.get(r).toString()+"\n");
						}
						myWriter.write("\n-----------------------------------------------------------------------------------------------------------------------\n");

						//////////////////////////////////////////////////////////////////////////////////////////////////
						/*Paz Station*/
						//////////////////////////////////////////////////////////////////////////////////////////////////
						System.out.println("*********Paz Station*********");
						int countP=0;
							String stationP="Paz Station";
							for(int m=0;m<refuelings.size();m++) {
								iDString=refuelings.get(m).getOwnerID();
								if (refuelings.get(m).getGasStation().equals(stationP))
								{	
									countP++;
									for(int z=m+1;z<refuelings.size();z++)
									{
										if (iDString.equals(refuelings.get(z).getOwnerID())&&refuelings.get(z).getGasStation().equals(stationP)) {
											countP++;
											//refuelings.remove(z);
											}
									}

									//System.out.println(iDString);
									teMaxbuy=new maxbuy(iDString, countP);
									//	System.out.println(teMaxbuy);
									maxP.add(teMaxbuy);


								}
								countP=0;
								//System.out.println(max);

							}
							//	System.out.println(max);

							for (int x = 0; x < maxP.size(); x++) {
								id=maxP.get(x).getId();
								for (int b = x+1; b < maxP.size(); b++) {
									if (id.equals(maxP.get(b).getId())) {
										maxP.remove(b);
										b--;
									}
								}
							}

							System.out.println(maxP);
							myWriter.write("-----------------------------------------------------"+stationP+"-----------------------------------------------------\n");
							for(int y=0;y<maxP.size();y++)
							{
							myWriter.write(maxP.get(y).toString()+"\n");
							}
							myWriter.write("\n-----------------------------------------------------------------------------------------------------------------------\n");
							
							//////////////////////////////////////////////////////////////////////////////////////////////////
							/*Ten  Station*/
							//////////////////////////////////////////////////////////////////////////////////////////////////
							System.out.println("*********Ten Station*********");
							int countT=0;
							String stationT="Ten Station";
							for(int m=0;m<refuelings.size();m++) {
								iDString=refuelings.get(m).getOwnerID();
								if (refuelings.get(m).getGasStation().equals(stationT))
								{	
									countT++;
									for(int z=m+1;z<refuelings.size();z++)
									{
										if (iDString.equals(refuelings.get(z).getOwnerID())&&refuelings.get(z).getGasStation().equals(stationT)) {
											countT++;
											//refuelings.remove(z);
										}
									}
									//System.out.println(iDString);
									teMaxbuy=new maxbuy(iDString, countT);
									//	System.out.println(teMaxbuy);
									maxT.add(teMaxbuy);
								}
								countT=0;
								//System.out.println(max);

							}
							//System.out.println(max);

							for (int x = 0; x < maxT.size(); x++) {
								id=maxT.get(x).getId();
								for (int b = x+1; b < maxT.size(); b++) {
									if (id.equals(maxT.get(b).getId())) {
										maxT.remove(b);
										b--;
									}
								}
							}

							
							System.out.println(maxT);
							myWriter.write("\n-----------------------------------------------------"+stationT+"-----------------------------------------------------\n");
							for(int z=0;z<maxT.size();z++)
							{
							myWriter.write(maxT.get(z).toString()+"\n");
							}
							myWriter.write("\n-----------------------------------------------------------------------------------------------------------------------\n");
						//////////////////////////////////////////////////////////////////////////////
						
						
						myWriter.close();
					    MyFile msg= new MyFile(filename+" "+formatDateTime+".txt");
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
							     Platform.runLater(new Runnable() {
										@Override
										public void run() {
											Alert alert = new Alert(AlertType.INFORMATION);
											alert.setAlertType(AlertType.INFORMATION); 
											alert.setContentText("Report send successfully!");
											alert.show(); 
										}
									});
								 
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
    
	
    /** 
	 * this method send notification  to chat client
	 */

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
