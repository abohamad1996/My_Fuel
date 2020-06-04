package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.Inventory;
import Entity.User;
import client.ClientConsole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StationManagerInventoryController implements Initializable{
    public static StationManagerInventoryController acainstance;
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

    @FXML
    private TextField txtGasolineInv;

    @FXML
    private TextField txtDieselInv;

    @FXML
    private TextField txtScooterInv;

    @FXML
    private TextField txtHomeHeatingInv;

	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public ClientConsole details= new ClientConsole("localhost", 5555);
	Inventory inventory;
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
	
	
	
	public void FuelAcceptor(Inventory inventory) {
		inventory=new Inventory(txtGasoline.getText(), txtDiesel.getText(), txtScooter.getText(), txtHomeHeating.getText(), txtGasolineInv.getText(), txtDiesel.getText(), txtScooterInv.getText(), txtHomeHeatingInv.getText());
		}
	
	
    @FXML
    void Set(ActionEvent event) {
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;		
	}

}
