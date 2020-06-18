package gui;






import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import org.omg.CORBA.PUBLIC_MEMBER;

import Entity.Car;
import Entity.OrderConfirmation;
import Entity.Rates;
import Entity.Refueling;
import Entity.Sales;
import Entity.StationsInventory;
import Entity.User;
import client.ChatClient;
import client.ClientConsole;
import client.Func;
import common.Message;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


public class RefuelingController implements Initializable{
	
	

	public int j;
    public static RefuelingController acainstance;
    @FXML
    private TextField txtQuantity;
    @FXML
    private Label labelRate;

    @FXML
    private Label labelID;
    	@FXML
    	private Label labelPrice;
	 @FXML
	    private Button btnStart;
	   @FXML
	    private Label labelAddress;
	    @FXML
	    private Label labelCarNumber;
	    @FXML
	    private Label labelFuelTyple;
	    @FXML
	    private Button btnCalculate;
	    @FXML
	    private Label labelGasStation;
	    @FXML
	    private Button brnDetails;
	    @FXML
	    private Label labelPump;
	    @FXML
	    private Label labelServices;
	    @FXML
	    private ProgressBar RefuelingPrgeess;
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	
//////////////////////////////////////////////////////////////////////////////////////////

	 int r;
		
		Calendar rightNow = Calendar.getInstance();
	    int y = rightNow.get(Calendar.YEAR);
	    int m = rightNow.get(Calendar.MONTH) + 1;
	    int d = rightNow.get(Calendar.DAY_OF_MONTH);
	    String date=y+"-"+m+"-"+d;
	   
       
	    Date loc_Time=new Date();
	    DateFormat dateFormat = new SimpleDateFormat("hh:mm");
	 	boolean saleOn=false;
	    double discount ;
//////////////////////////////////////////////////////////////////////////////////////////

	public static ProfileSettingsController ProfileSetting;
	public static  ClientRefuelingDetailsController clientRefuelingDetails;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	String CurrentRate;
	String gasStationString;
	String Price;
	String currentAddress;
	public ClientConsole details= new ClientConsole("localhost", 5555);
	ArrayList<User> userdetails= new ArrayList<User>();
	public ArrayList<StationsInventory> stationsInventories;
	public ArrayList<String> Address= new ArrayList<String>();;
	int size;
	Sales  sales;
	User detailsUser;
	double inventory;
	double newInventory;
	double quantity;
	String Quantity;
	String gasStationID;
	StationsInventory currentStations;
	Refueling refueling;
	ArrayList<Car> car2=new ArrayList<Car>();
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/ClientRefuling.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
    @FXML
    void Calculate(ActionEvent event) {
    	String now=dateFormat.format(loc_Time);
    /*	System.out.println(now);
    	System.out.println(sales.getFuelType().compareTo(car2.get(r).getGastype())==0);
    	System.out.println((now.compareTo(sales.getFormHour())>0));
    	System.out.println(sales.getFormHour());*/
    	//System.out.println((now.compareTo(sales.getToHout())<0));
    	if (sales==null) {
			System.out.println("there is no sale at this time");
			now="10:00";
			sales=new Sales(1, "aaa", "aaa", "aaa", "12:00", 1);
		}
    	else if (sales.getFuelType().compareTo(car2.get(r).getGastype())==0   &&(now.compareTo(sales.getFormHour())>0)   &&(now.compareTo(sales.getToHout())<0)) {
    		discount=Double.valueOf(sales.getDiscount());
    		saleOn=true;
        	discount=discount/100;
        	discount=1-discount;
    	}
    	else if (now.compareTo(sales.getToHout())>0) {
    		System.out.println("hello");
    		RefuelingController.acainstance.details.accept(new Message(74, sales));
		}
    	String now2=dateFormat.format(loc_Time);

//////////////////////////////////////////////////////////////////////////////////////////
    	
    	boolean a=false;
		double	dd=Double.parseDouble(CurrentRate);
		double	f=Double.parseDouble(txtQuantity.getText());
		double price=dd*f;
		if (saleOn) {
			price=price*discount;
			saleOn=false;
			a=true;
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setAlertType(AlertType.INFORMATION); 
					alert.setContentText("You get a discount: "+ sales.getDiscount()+"%");
					alert.show(); 
				}
			});
		}
		 Price=String.valueOf(price);  
		labelPrice.setText(Price);
		btnStart.setVisible(true);
		String salesID=String.valueOf(sales.getIDsales());  
		if(a) {
		refueling=new Refueling(0,labelID.getText(),labelCarNumber.getText(), labelGasStation.getText(),currentAddress, labelFuelTyple.getText(), CurrentRate, txtQuantity.getText(), Price, date, labelPump.getText(),labelServices.getText(), now2, salesID);
		}
		else {
			refueling=new Refueling(0,labelID.getText(),labelCarNumber.getText(), labelGasStation.getText(),currentAddress, labelFuelTyple.getText(), CurrentRate, txtQuantity.getText(), Price, date, labelPump.getText(),labelServices.getText(), now2, "0");
		}
		RefuelingController.acainstance.details.accept(new Message(38, refueling));
		Quantity=txtQuantity.getText();
		quantity=Double.parseDouble(Quantity);
    }
    
    @FXML
    void SeeDetails(ActionEvent event) {
    	clientRefuelingDetails = new ClientRefuelingDetailsController();
    	runLater(() -> {
    		clientRefuelingDetails.start(splitpane, user, "User");
});
    }
	public void StationAcceptor(ArrayList<StationsInventory> station) {
		stationsInventories = (ArrayList<StationsInventory>)station.clone();
		}
	public void checkForSaleAcceptor(Sales sale) {
		sales = sale;
		System.out.println(sales);
		}
	public void CarAcceptor(ArrayList<Car> car) {
		car2.addAll(car);
	//	System.out.println(car2.get(0).getOwnerID());
		//CarList.addAll(car);
	}
	public void StationIDAcceptor(String ID) {
		gasStationID=ID;
		//System.out.println("Gas Station ID:"+gasStationID);
	}
	public void StationToUpdate(StationsInventory station) {
		System.out.println(station);
		
	}
    @FXML
    void StartRefueilng(ActionEvent event) {
		 for(int i=0;i<stationsInventories.size();i++)
			{
			 System.out.println((stationsInventories.get(i).getStationID()));
				if(stationsInventories.get(i).getStationID().equals(gasStationID))
					currentStations=stationsInventories.get(i);
			}
		 OrderConfirmation order;
		if(labelFuelTyple.getText().equals("Gasoline 95"))
		{
			String Threshold;
			double threshold;
			   inventory=Double.parseDouble(currentStations.getGasolineQuantity());
			   newInventory=inventory-quantity;
			   String Inventory=String.valueOf(newInventory);
			   currentStations.setGasolineQuantity(Inventory);
			   System.out.println(currentStations);
			   RefuelingController.acainstance.details.accept(new Message(39, currentStations));
			   System.out.println(refueling);
			   Threshold=currentStations.getGasolineThresholdLevel();
			   threshold=Double.parseDouble(Threshold);
			   System.out.println("Gasloine Threshold:"+threshold+" "+newInventory);
			   if(newInventory<threshold)
			   {
				   System.out.println("alert");
				   newInventory=5000-newInventory;
				   System.out.println(newInventory);
				   String NewInventory=String.valueOf(newInventory);
				   	System.out.println(NewInventory);
				   	order=new OrderConfirmation(0, refueling.getGasType(), NewInventory, "Waiting for Station Manager Confirm", refueling.getGasStation(), refueling.getAddress(), refueling.getDate(), currentStations.getManagerIDString());
					   RefuelingController.acainstance.details.accept(new Message(44, order));
					   System.out.println(order);

			   }
			   else {
				System.out.println("Quantity good!");
			}
			   
		}
		else if(labelFuelTyple.getText().equals("Diesel fuel"))
		{
			String Threshold;
			double threshold;
			 inventory=Double.parseDouble(currentStations.getDieselQuantity());
			   newInventory=inventory-quantity;
			   String Inventory=String.valueOf(newInventory);
			   System.out.println(Inventory);
			   currentStations.setDieselQuantity(Inventory);
			   System.out.println(currentStations);
			   RefuelingController.acainstance.details.accept(new Message(40, currentStations));
			   System.out.println(refueling);
			   Threshold=currentStations.getDieselThresholdLevel();
			   threshold=Double.parseDouble(Threshold);
			   System.out.println("Deisel Threshold:"+threshold+" "+newInventory);
			   if(newInventory<threshold)
			   {
				   newInventory=5000-newInventory;
				   System.out.println(newInventory);
				   System.out.println("alert"); 
				   String NewInventory=String.valueOf(newInventory);
				   	System.out.println(NewInventory);
					   order=new OrderConfirmation(0, refueling.getGasType(), NewInventory, "Waiting for Station Manager Confirm", refueling.getGasStation(), refueling.getAddress(), refueling.getDate(), currentStations.getManagerIDString());
					   RefuelingController.acainstance.details.accept(new Message(44, order));
					   System.out.println(order);
			   }
			   else {
					System.out.println("Quantity good!");
				}
		}
		else if(labelFuelTyple.getText().equals("Scooters fuel"))
		{
			String Threshold;
			double threshold;
			inventory=Double.parseDouble(currentStations.getScooterQuantity());
			   newInventory=inventory-quantity;
			   String Inventory=String.valueOf(newInventory);
			   System.out.println(Inventory);
			   currentStations.setScooterQuantity(Inventory);
			   System.out.println(currentStations);
			   RefuelingController.acainstance.details.accept(new Message(41, currentStations));
			   System.out.println(refueling);
			   Threshold=currentStations.getScooterThresholdLevel();
			   threshold=Double.parseDouble(Threshold);
			   System.out.println("Scooter Threshold:"+threshold+" "+newInventory);
			   if(newInventory<threshold)
			   {
				   newInventory=5000-newInventory;
				   System.out.println(newInventory);
				   System.out.println("alert"); 
				   String NewInventory=String.valueOf(newInventory);
				   	System.out.println(NewInventory);
				   	order=new OrderConfirmation(0, refueling.getGasType(), NewInventory, "Waiting for Station Manager Confirm", refueling.getGasStation(), refueling.getAddress(), refueling.getDate(), currentStations.getManagerIDString());
					   RefuelingController.acainstance.details.accept(new Message(44, order));
					   System.out.println(order);
			   }
			   else {
					System.out.println("Quantity good!");
				}
		}
		   RefuelingController.acainstance.details.accept(new Message(43, refueling));
		Task <Void> t = new Task <Void> () {
    		    protected Void call() throws Exception {
    		     for (int i = 0; i < 10; i++) {
    		      updateProgress(i, 9);
    		      Thread.sleep(500);
    		      if(i==9)
    		      {
    		    	  brnDetails.setVisible(true);
    		      }
    		     }
    		     return null;
    		    }
    		   };
    		   RefuelingPrgeess.progressProperty().bind(t.progressProperty());
    		   //new Thread(t).run(); // wrong
    		   new Thread(t).start(); // right
}
    private void runLater(Func f) {
		f.call();
		Platform.runLater(() -> {
			try {
				Thread.sleep(10);
				f.call();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;		
		details.accept(new Message(15, null));
		details.accept(new Message(37, null));
		details.accept(new Message(73, null));
		Random rn = new Random();
		int maximum =(car2.size()-1);
		System.out.println(maximum);
		
		int minimum=0;
		int range = maximum - minimum + 1;
		int randomNum =  rn.nextInt(range) + minimum;
		r=randomNum;

		String s=String.valueOf(randomNum+1);
		labelID.setText(car2.get(randomNum).getOwnerID());
		labelCarNumber.setText(car2.get(randomNum).getCarNumber());
		labelCarNumber.setVisible(true);
		labelFuelTyple.setText(car2.get(randomNum).getGastype());
		labelFuelTyple.setVisible(true);
		labelGasStation.setText(car2.get(randomNum).getGasStation1());
		labelServices.setText(car2.get(randomNum).getServices());
		labelGasStation.setVisible(true);
		labelPump.setText(s);
		labelPump.setVisible(true);
		labelRate.setText(car2.get(randomNum).getRateForCar());
		CurrentRate=car2.get(randomNum).getRateForCar();
		gasStationString=car2.get(randomNum).getGasStation1();
		
		for(int i=0;i<stationsInventories.size();i++)
		{
			if(stationsInventories.get(i).getStationName().equals(gasStationString))
			{
				System.out.println(gasStationString);
				String gaString;
				gaString=stationsInventories.get(i).getStationAddress();
				Address.add(stationsInventories.get(i).getStationAddress());
	
			}
		}
		Random rn2 = new Random();
		int maximum2 =(Address.size()-1);
		int minimum2=0;
		int range2 = maximum2 - minimum2 + 1;
		int randomNum2 =  rn2.nextInt(range2) + minimum2;
		labelAddress.setText(Address.get(randomNum2));
		currentAddress=Address.get(randomNum2);;
		}
	
}
