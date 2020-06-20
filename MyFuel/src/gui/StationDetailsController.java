package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.OrderConfirmation;
import Entity.StationsInventory;
import Entity.User;
import client.ClientConsole;
import common.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * This class represents a controller for the gui StationDetails
 * @author  mahmoud odeh
 *
 */
public class StationDetailsController implements Initializable{
    public static StationDetailsController acainstance;

	  @FXML
	    private Text GasolineFuel;

	    @FXML
	    private Text GasolineFuel1;

	    @FXML
	    private Text GasolineFuel11;

	    @FXML
	    private Text GasolineFuel12;

	    @FXML
	    private Text GasolineFuel121;

	    @FXML
	    private Text GasolineFuel2;

	    @FXML
	    private Text GasolineFuel21;

	    @FXML
	    private Text GasolineFuel122;

	    @FXML
	    private Label labelManagerName;

	    @FXML
	    private Label labelManagerID;

	    @FXML
	    private Label labelStationID;

	    @FXML
	    private Label labelStationName;

	    @FXML
	    private Label labelStationAdreress;

	    @FXML
	    private Label labelGasolineQuantity;

	    @FXML
	    private Label labelDieselThreshold;

	    @FXML
	    private Label labelGasolinneThreshold;

	    @FXML
	    private Label labelHomeHearingQuantity;

	    @FXML
	    private Label labelScooterQuantity;

	    @FXML
	    private Label labelDieselQuantity;

	    @FXML
	    private Label labelScooterThreshold;
		public StationsInventory stationsInventory;
		public ClientConsole details= new ClientConsole("localhost", 5555);
		/**
		 * This methode get notification from the chatclient
		 * @param inv this paramet of type station inventory contaians StationID,StationName,StationAddress,GasolineQuantity,DieselQuantity,ScooterQuantity,HomeHeatingQuantity,GasolineThresholdLevel,DieselThresholdLevel,ScooterThresholdLevel,managerIDString;
		 */
		public void FuelAcceptor(ArrayList<StationsInventory> inv) {
			stationsInventory=null;
			for(StationsInventory temp:inv)
			{
				if(temp.getManagerIDString().equals(user.getId()))
				{
					stationsInventory=temp;
				}
			}
				System.out.println(stationsInventory);
			}
	
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	 ObservableList<OrderConfirmation> List =FXCollections.observableArrayList(); 
	  final Button addButton  = new Button("Confirm");

	private AnchorPane lowerAnchorPane;
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
			loader = new FXMLLoader(getClass().getResource("/gui/StationDetails.fxml"));
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
			acainstance=this;
			details.accept(new Message(34, null));
			String fullName=user.getFirstname()+" "+user.getLastname();
			labelManagerID.setText(stationsInventory.getManagerIDString());
			labelGasolineQuantity.setText(stationsInventory.getGasolineQuantity());
			labelDieselQuantity.setText(stationsInventory.getDieselQuantity());
			labelScooterQuantity.setText(stationsInventory.getScooterQuantity());
			labelHomeHearingQuantity.setText(stationsInventory.getHomeHeatingQuantity());
			labelGasolinneThreshold.setText(stationsInventory.getGasolineThresholdLevel());
			labelDieselThreshold.setText(stationsInventory.getDieselThresholdLevel());
			labelScooterThreshold.setText(stationsInventory.getScooterThresholdLevel());
			labelStationID.setText(stationsInventory.getStationID());
			labelStationName.setText(stationsInventory.getStationName());
			labelStationAdreress.setText(stationsInventory.getStationAddress());
			labelManagerName.setText(fullName);
	}
}
