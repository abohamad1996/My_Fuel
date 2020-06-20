package gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;




import Entity.Sales;
import Entity.User;
import client.ClientConsole;
import common.Message;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MarketingManagerSaleController implements Initializable{
	
	Calendar rightNow = Calendar.getInstance();
    int hours=rightNow.get(Calendar.HOUR);
    int minute=rightNow.get(Calendar.MINUTE);
    
    String currenttime=hours+":"+minute;
	Sales offer; 

	private static final int IDsales = 0;
	public static MarketingManagerSaleController acainstance;
	@FXML
    private Button btnStart;

    @FXML
    private ComboBox<String> comboFuelType;

    @FXML
    private TextField txtDiscout;

    @FXML
    private TextField txtFrom;

    @FXML
    private TextField txtTo;

	@FXML
	private static SplitPane splitpane;
	public ClientConsole chat= new ClientConsole("localhost", 5555);
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public ClientConsole details= new ClientConsole("localhost", 5555);
	ArrayList<User> userdetails= new ArrayList<User>();
	double currentdiscount;

	
	 
			 
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
		if(txtDiscout.getText().isEmpty()|| txtFrom.getText().isEmpty()|| txtTo.getText().isEmpty() ||comboFuelType.getSelectionModel().isEmpty())
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
	    	Sales sales=new Sales(IDsales, comboFuelType.getValue(), txtDiscout.getText(), txtFrom.getText(), txtTo.getText(),-1);
	    	MarketingManagerSaleController.acainstance.chat.accept(new Message(42, sales));
	    	Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setAlertType(AlertType.INFORMATION); 
					alert.setContentText("Sales has been set!!");
					alert.show(); 
				}
			});
    	
	}}
		
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		acainstance=this;		

		gastypeValues.add("Gasoline 95");
		gastypeValues.add("Diesel fuel");
		gastypeValues.add("Scooters fuel");

		gastypeList.addAll(gastypeValues);
		comboFuelType.setItems(gastypeList);

	}


	public void FuelAcceptor(ArrayList<Sales> salmarketingmanagerList) {
		offer=new Sales(salmarketingmanagerList.get(0).getIDsales(), salmarketingmanagerList.get(0).getFuelType(), salmarketingmanagerList.get(0).getDiscount(), salmarketingmanagerList.get(0).getFormHour(), salmarketingmanagerList.get(0).getToHout(),salmarketingmanagerList.get(0).getStatus());
		
		
	}

}
