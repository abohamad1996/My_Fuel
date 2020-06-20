package gui;

import java.net.URL;
import java.util.ResourceBundle;

import Entity.OrderConfirmation;
import Entity.User;
import client.ClientConsole;
import common.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *In this class we added a dates for home heating order
 * @author mahmoud odeh
 *
 */	
public class StationManagerHomeHeatingDates implements Initializable{

	
	public ClientConsole details= new ClientConsole("localhost", 5555);

	public static StationManagerHomeHeatingDates acainstance;

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
			loader = new FXMLLoader(getClass().getResource("/gui/StationManagerHomeHeating.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}
}
	
    @FXML
    private DatePicker dateSelect;
	
	
	  @FXML
	    private Text GasolineFuel;

	    @FXML
	    private Button btnSet;
/**
 * 
 * 
this method sets the dates that station manager want to order home heating order
 * @param event
 */
	    @FXML
	    void Set(ActionEvent event) {
	    	String date;
	    		date=dateSelect.getValue().toString();
	    		
	    		StationManagerHomeHeatingDates.acainstance.details.accept(new Message(79, date));
	    }

	
	
	    /**
	     * This methode get notification from the chatclient
	     */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
acainstance=this;		
	}

}
