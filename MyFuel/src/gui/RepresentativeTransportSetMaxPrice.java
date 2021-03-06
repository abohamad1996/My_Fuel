package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

import Entity.Inventory;
import Entity.Rates;
import Entity.User;
import client.ClientConsole;
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
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * This class represents a controller for the gui RepresentativeTransportSetMaxPrice
 * @author  mahmoud odeh
 *
 */
public class RepresentativeTransportSetMaxPrice implements Initializable{
    public static RepresentativeTransportSetMaxPrice acainstance;
    public 	 ClientConsole MaxRates= new ClientConsole("localhost", 5555);


    @FXML
	public Label status;
	@FXML
    private Text GasolineFuel;

    @FXML
    private Button btnSet;

    @FXML
    private TextField txtGasoline;

    @FXML
    private TextField txtDiesel;

    @FXML
    private TextField txtScooter;

    @FXML
    private TextField txtHomeHeating;
	    
	Rates Gasoline,Diesel,Scotter,HomeHeating;
	/**
	* This methode get notification from the chat client if there is anew maxprice
	* @param event
	*/
    @FXML
    void SetPrice(ActionEvent event) {
    	if(txtGasoline.getText().isEmpty() || txtDiesel.getText().isEmpty()||txtScooter.getText().isEmpty()||txtHomeHeating.getText().isEmpty())
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
    		Gasoline=new Rates("Gasoline 95", txtGasoline.getText());
    		Diesel=new Rates("Diesel fuel", txtDiesel.getText());
    		Scotter=new Rates("Scooters fuel", txtScooter.getText());
    		HomeHeating=new Rates("Home Heating fuel", txtHomeHeating.getText());
    		RepresentativeTransportSetMaxPrice.acainstance.MaxRates.accept(new Message(28, Gasoline));
    		RepresentativeTransportSetMaxPrice.acainstance.MaxRates.accept(new Message(28, Diesel));
    		RepresentativeTransportSetMaxPrice.acainstance.MaxRates.accept(new Message(28, Scotter));
    		RepresentativeTransportSetMaxPrice.acainstance.MaxRates.accept(new Message(28, HomeHeating));
    		Platform.runLater(new Runnable() {
    			@Override
    			public void run() {
    				Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setAlertType(AlertType.INFORMATION); 
    				alert.setContentText("Rate Set Successfully");
    				alert.show(); 
    			}
    		});
    	}
    }
    /**
    * This methode get the fuel type and price 
    * @param maxPrice this parameter from the type rates and contains fueltype,price
    */
	public void RatesAcceptor(ArrayList<Rates> maxPrice) {
		System.out.println(maxPrice.get(0).getPrice());
		Gasoline=new Rates(maxPrice.get(0).getFuelType(), maxPrice.get(0).getPrice());
		Diesel=new Rates(maxPrice.get(1).getFuelType(), maxPrice.get(1).getPrice());
		Scotter=new Rates(maxPrice.get(2).getFuelType(), maxPrice.get(2).getPrice());
		HomeHeating=new Rates(maxPrice.get(3).getFuelType(), maxPrice.get(3).getPrice());
		}
	
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
    ObservableList<String> gastypeList =FXCollections.observableArrayList(); 	
		ArrayList<String> gastypeValues=new ArrayList<String>();
		/**
		* 
		* @param splitpane this parameter from the type splitpane
		* @param user this paramater from the type user that contains id,firtstname,lastname,email,username,password,rank,clienttype,status,image
		* @param userJob this parameter from the type string
		*/
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/RepresentativeTransportSetMaxPrice.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	/**
	 * This methode get notification from the chatclient
	 */
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 acainstance = this;			
		 MaxRates.accept(new Message(29, null));
		 txtGasoline.setText(Gasoline.getPrice());
		 txtDiesel.setText(Diesel.getPrice());
		 txtScooter.setText(Scotter.getPrice());
		 txtHomeHeating.setText(HomeHeating.getPrice());
	}
}
