package gui;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import Entity.HomeHeatingOrder;
import Entity.Inventory;
import Entity.Rates;
import Entity.StationsInventory;
import Entity.User;
import client.ClientConsole;
import client.Func;
import common.Message;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 *  This class represents a controller for the gui  HomeHeatingOrder
 */

public class HomeHeatingOrderController implements Initializable{
	public static HomeHeatingOrderController acainstance;
    @FXML
    private ComboBox<String> comboGasStation;

    @FXML
    public Label status;
    @FXML
    private Button btnAddOrder;

    @FXML
    private TextField btnQuantity;

    @FXML
    private ComboBox<String> DateSelect;

    @FXML
    private ComboBox<String> txtUrgent;
    @FXML
    private Label labelPrice;
    @FXML
    private Label labelRate;
    @FXML
    private Button btnCalculate;
	ArrayList<String> urgenttypeValues=new ArrayList<String>();
    ObservableList<String> urgentList =FXCollections.observableArrayList(); 
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public ClientConsole details= new ClientConsole("localhost", 5555);
	HomeHeatingOrder heatingOrder;
	public String homeHeatingRate;
	double rates;
	double quantity;
	double price;
	String urgent;
	String date;
	double inventory;
	double newInventory;
	public String finalPrice;
	public StationsInventory stationsInventory;
	ArrayList<StationsInventory> inven;
    ObservableList<String> List =FXCollections.observableArrayList(); 
    ObservableList<String> DatesList =FXCollections.observableArrayList(); 

    ObservableList<String> GasList =FXCollections.observableArrayList(); 
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/HomeHeatingNewOrder.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	/**
	 * 
	 * @param inv from type ArrayList put all gas station to the combo box  
	 */

	public void FuelAcceptorQuantity(ArrayList<StationsInventory> inv) {
		stationsInventory=null;
		
		}
	/**
	 * 
	 * @param inv from type ArrayList put all gas station to the combo box  
	 */

	
	public void FuelAcceptor(ArrayList<StationsInventory> inv) {
		inven = (ArrayList<StationsInventory>)inv.clone();
		int size=inv.size();
		String [] gasStation;
		gasStation=new String[size];
		for(int i=0;i<inv.size();i++)
		{
		gasStation[i]=inv.get(i).getStationID()+","+inv.get(i).getStationName()+","+inv.get(i).getStationAddress()+","+inv.get(i).getHomeHeatingQuantity()+" Liter";
		List.add(gasStation[i]);
		}
	}
	/**
	 * 
	 *  accept dates for home heating order
	 * @param dates
	 */
	public void DatesAcceptor(ArrayList<String> dates) {
		DatesList.addAll(dates);
		}
	
	/**
	 * This method insert the rate of type string to the SQL table 
	 * @param rate new rate type string
	 */

	public void HomeHeatingRatesAcceptor(String rate) {
		homeHeatingRate=rate;
		System.out.println("new rate:"+rate);
		  rates = Double.parseDouble(rate);
		}
	/**
	 * This method insert the event of type ActionEvent to the SQL table 
	 * @param event have the name of gas station
	 */

    @FXML
    void SelectGasStation(ActionEvent event) {
    	String input = comboGasStation.getValue();;
    	String output = input.substring(0, input.indexOf(','));
    	int index=Integer.parseInt(output)-1;
    	System.out.println("index is:"+index);
    	//	System.out.println(inven.get(index).getStationID());
    		//System.out.println(inven.get(index).toString());
    	stationsInventory=inven.get(index);
    }
    /**
     * This method insert the event of type ActionEvent to the SQL table 
     * @param event of button

     */

	   @FXML
	    void AddNewOrder(ActionEvent event) {
		  
		   inventory=Double.parseDouble(stationsInventory.getHomeHeatingQuantity());
		   newInventory=inventory-quantity;
		   String Inventory=String.valueOf(newInventory); 
		   stationsInventory=new StationsInventory(stationsInventory.getStationID(), null, null, null, null, null, Inventory, null, null, null, null);
		//System.out.println(newInventory);
		//System.out.println(Inventory);
			HomeHeatingOrderController.acainstance.details.accept(new Message(23, heatingOrder));
		   HomeHeatingOrderController.acainstance.details.accept(new Message(32, stationsInventory));
		   LocalTime now=LocalTime.now();
		   System.out.println(now);
		   LocalTime nowAfter2Min=LocalTime.now().plusMinutes(2);
		   System.out.println(nowAfter2Min);
		   if(now.compareTo(nowAfter2Min)>0)
		   {
			   System.out.println("aaa");
		   }
		   HomeHeatingOrderDone homeHeatingOrderDone;
		   homeHeatingOrderDone = new HomeHeatingOrderDone();
	    	runLater(() -> {
	    		homeHeatingOrderDone.start(splitpane, user, "User");
	});
			//System.out.println(stationsInventory);
	    }
	   /**
	    *  this method Calculate price order
	    * @param  event of button
 
	    */

	    @FXML
	    void Calculate(ActionEvent event) {
	    	 if(txtUrgent.getSelectionModel().isEmpty()|| comboGasStation.getSelectionModel().isEmpty() ||DateSelect.getSelectionModel().isEmpty())
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
			   else {
	    	  date=DateSelect.getValue();
	    	  LocalTime now=LocalTime.now();
			   quantity=Double.parseDouble(btnQuantity.getText());
			   urgent=txtUrgent.getValue();
			   heatingOrder=new HomeHeatingOrder(0, user.getId(), quantity, date, urgent, price, "In Progress",now.toString());
			 price =   heatingOrder.Calculate_Price_HomeHeating(quantity, urgent, rates);
			 heatingOrder.setPrice(price);
			finalPrice=String.valueOf(price);
			labelPrice.setVisible(true);
			labelPrice.setText(finalPrice);
			btnAddOrder.setVisible(true);
	    }
	    }
	    @FXML
	    void CalculatePrice(KeyEvent event) {
	    	
	    }
	    /**
		 * This method run all the buttons in the gui
		 * @param f type for function
		 */

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
		/**
		 * this method send notification  to chat client
		 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance=this;
		details.accept(new Message(22, null));
		details.accept(new Message(31, null));
		details.accept(new Message(78, null));
		labelRate.setVisible(true);
		labelRate.setText(homeHeatingRate);
		urgenttypeValues.add("Yes");
		urgenttypeValues.add("No");
		urgentList.addAll(urgenttypeValues);
		txtUrgent.setItems(urgentList);
		comboGasStation.setItems(List);
		DateSelect.setItems(DatesList);
	}

}
