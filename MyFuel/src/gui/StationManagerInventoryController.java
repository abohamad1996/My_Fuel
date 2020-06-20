package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

import Entity.Inventory;
import Entity.StationsInventory;
import Entity.User;
import client.ClientConsole;
import common.Message;
import javafx.application.Platform;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.internal.dynalink.beans.StaticClass;
/**
 * This class represents a controller for the gui StationManagerInventory
 * @author  mahmoud odeh
 *
 */
public class StationManagerInventoryController implements Initializable{
    public static StationManagerInventoryController acainstance;
    @FXML
    private Text GasolineFuel;

    @FXML
    private TextField txtGasoline;

    @FXML
    private TextField txtDiesel;

    @FXML
    private TextField txtScooter;


    @FXML
    private TextField txtGasolineInv;

    @FXML
    private TextField txtDieselInv;

    @FXML
    private TextField txtScooterInv;

    @FXML
    private TextField txtHomeHeatingInv;

    @FXML
    private Button btnSetLevel;
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public ClientConsole details= new ClientConsole("localhost", 5555);
	public StationsInventory gasStationsInventory;
	public StationsInventory gasStationsInventoryUpdate;
	public StationsInventory stationsInventory;


	String status;
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
			loader = new FXMLLoader(getClass().getResource("/gui/StationManagerInventory.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	
	/**
	* This methode get notification from the chat client
	* @param inv this paramet of type station inventory contaians StationID,StationName,StationAddress,GasolineQuantity,DieselQuantity,ScooterQuantity,HomeHeatingQuantity,GasolineThresholdLevel,DieselThresholdLevel,ScooterThresholdLevel,managerIDString;
	*/
	
	public void FuelAcceptor(ArrayList<StationsInventory> inv) {
		stationsInventory=null;
		/*Diesel=new Inventory(inv.get(0).getFuelType(), inv.get(0).getQuantity(), inv.get(0).getLevel());
		Gasoline=new Inventory(inv.get(1).getFuelType(), inv.get(1).getQuantity(), inv.get(1).getLevel());
		Scotter=new Inventory(inv.get(3).getFuelType(), inv.get(3).getQuantity(), inv.get(3).getLevel());
		HomeHeating=new Inventory(inv.get(2).getFuelType(), inv.get(2).getQuantity(), inv.get(2).getLevel());
		System.out.println(Gasoline.toString());
		System.out.println(Diesel.toString());
		System.out.println(Scotter.toString());
		System.out.println(HomeHeating.toString());*/
		for(StationsInventory temp:inv)
		{
			if(temp.getManagerIDString().equals(user.getId()))
			{
				stationsInventory=temp;
			}
		}
			System.out.println(stationsInventory);
		}
	
	public void NewFuelAcceptor(Inventory inv) {
	
		}
	
	/**
	* This methode get notification from the chatclient
	* @param event
	*/
    @FXML
    void SetLevel(ActionEvent event) {
    /*	Gasoline=new Inventory(Gasoline.getFuelType(), Gasoline.getQuantity(), Gasoline.getLevel());
		Gasoline.setLevel(txtGasoline.getText());
		Diesel=new Inventory(Diesel.getFuelType(), Diesel.getQuantity(), Diesel.getLevel());
		Diesel.setLevel(txtDiesel.getText());
		Scotter=new Inventory(Scotter.getFuelType(), Scotter.getQuantity(), Scotter.getLevel());
		Scotter.setLevel(txtScooter.getText());
		HomeHeating=new Inventory(HomeHeating.getFuelType(), HomeHeating.getQuantity(), HomeHeating.getLevel());
		HomeHeating.setLevel(txtHomeHeating.getText());
    	StationManagerInventoryController.acainstance.details.accept(new Message(14, Gasoline));
    	StationManagerInventoryController.acainstance.details.accept(new Message(14, Diesel));
    	StationManagerInventoryController.acainstance.details.accept(new Message(14, Scotter));
    	StationManagerInventoryController.acainstance.details.accept(new Message(14, HomeHeating));*/
    	
    	if(txtGasoline.getText().isEmpty()||txtDiesel.getText().isEmpty()||txtScooter.getText().isEmpty())
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
    	else
    	{
    	gasStationsInventoryUpdate =new StationsInventory(stationsInventory.getStationID(), stationsInventory.getStationName(), stationsInventory.getStationAddress(),  stationsInventory.getGasolineQuantity(), stationsInventory.getDieselQuantity(), stationsInventory.getScooterQuantity(), stationsInventory.getHomeHeatingQuantity(), txtGasoline.getText(), txtDiesel.getText(), txtScooter.getText(), stationsInventory.getManagerIDString());
     	StationManagerInventoryController.acainstance.details.accept(new Message(14, gasStationsInventoryUpdate));
    	System.out.println(gasStationsInventoryUpdate);
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setAlertType(AlertType.INFORMATION); 
    			alert.setContentText("ThresholdLevel changes successfully!!");
    			alert.show(); 
    }
    }
    /**
     * This methode get notification from the chatclient
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;		
		details.accept(new Message(13, null));
		txtGasolineInv.setText(stationsInventory.getGasolineQuantity());
		txtGasoline.setText(stationsInventory.getGasolineThresholdLevel());
		txtDieselInv.setText(stationsInventory.getDieselQuantity());
		txtDiesel.setText(stationsInventory.getDieselThresholdLevel());
		txtScooter.setText(stationsInventory.getScooterThresholdLevel());
		txtScooterInv.setText(stationsInventory.getScooterQuantity());
		txtHomeHeatingInv.setText(stationsInventory.getHomeHeatingQuantity());
		
	}
}
