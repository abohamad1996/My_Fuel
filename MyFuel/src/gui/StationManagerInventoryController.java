package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.Inventory;
import Entity.User;
import client.ClientConsole;
import common.Message;
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
    private TextField txtHomeHeating;

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
	public Inventory Gasoline,Diesel,Scotter,HomeHeating;
	String status;
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
	
	
	
	public void FuelAcceptor(ArrayList<Inventory> inv) {
		Diesel=new Inventory(inv.get(0).getFuelType(), inv.get(0).getQuantity(), inv.get(0).getLevel());
		Gasoline=new Inventory(inv.get(1).getFuelType(), inv.get(1).getQuantity(), inv.get(1).getLevel());
		Scotter=new Inventory(inv.get(3).getFuelType(), inv.get(3).getQuantity(), inv.get(3).getLevel());
		HomeHeating=new Inventory(inv.get(2).getFuelType(), inv.get(2).getQuantity(), inv.get(2).getLevel());
		System.out.println(Gasoline.toString());
		System.out.println(Diesel.toString());
		System.out.println(Scotter.toString());
		System.out.println(HomeHeating.toString());
		}
	
	public void NewFuelAcceptor(Inventory inv) {
	
		}
	
	
    @FXML
    void SetLevel(ActionEvent event) {
    	Gasoline=new Inventory(Gasoline.getFuelType(), Gasoline.getQuantity(), Gasoline.getLevel());
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
    	StationManagerInventoryController.acainstance.details.accept(new Message(14, HomeHeating));
       	Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setAlertType(AlertType.CONFIRMATION); 
    			alert.setContentText("ThresholdLevel changes successfully!!");
    			alert.show(); 
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;		
		details.accept(new Message(13, null));
		txtGasolineInv.setText(Gasoline.getQuantity());
		txtGasoline.setText(Gasoline.getLevel());
		txtDieselInv.setText(Diesel.getQuantity());
		txtDiesel.setText(Diesel.getLevel());
		txtScooter.setText(Scotter.getLevel());
		txtScooterInv.setText(Scotter.getQuantity());
		txtHomeHeatingInv.setText(HomeHeating.getQuantity());
		txtHomeHeating.setText(HomeHeating.getLevel());
	}
}
