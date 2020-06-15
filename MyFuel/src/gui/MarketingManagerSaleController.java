package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.Sales;
import Entity.User;
import client.ClientConsole;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MarketingManagerSaleController implements Initializable{
	
	@FXML
    private Button btnStart;

    @FXML
    private ComboBox<String> comboFuelType;

    @FXML
    private TextField txtDiscout;

    @FXML
    private TextField txtFrom;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtTo;

	
	
	
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public ClientConsole details= new ClientConsole("localhost", 5555);
	ArrayList<User> userdetails= new ArrayList<User>();
	
	
	ObservableList<String> gastypeList =FXCollections.observableArrayList(); 
	ArrayList<String> gastypeValues=new ArrayList<String>();
	
	User detailsUser;
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/MarketingManagerSale.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
		
	@FXML
    void Start(ActionEvent event) {
		Sales sales=new Sales(comboFuelType.getValue(), txtDiscout.getText(), txtDate.getValue(), txtFrom.getText(), txtTo.getText());
    }

	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		gastypeValues.add("Gasoline 95");
		gastypeValues.add("Diesel fuel");
		gastypeValues.add("Scooters fuel");
		gastypeValues.add("Home Heating fuel");
		
		gastypeList.addAll(gastypeValues);
		
		
		comboFuelType.setItems(gastypeList);

	}

}
