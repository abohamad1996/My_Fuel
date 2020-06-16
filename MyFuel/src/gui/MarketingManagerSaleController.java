package gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.oracle.jrockit.jfr.TimedEvent;

import Entity.Rates;
import Entity.Sales;
import Entity.User;
import client.ClientConsole;
import client.Func;
import common.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MarketingManagerSaleController implements Initializable{
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
    private DatePicker txtDate;

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
	String from;
	String date;
	String totime;
	Sales offer;
	
	LocalDate testDatePicker;
	
	Calendar rightNow = Calendar.getInstance();
    int y = rightNow.get(Calendar.YEAR);
    int m = rightNow.get(Calendar.MONTH) + 1;
    int d = rightNow.get(Calendar.DAY_OF_MONTH);
    int hours=rightNow.get(Calendar.HOUR);
    int minute=rightNow.get(Calendar.MINUTE);
    
    String currentdate=y+"-"+m+"-"+d;
    String currenttime=hours+":"+minute;
    LocalDate currtdate=LocalDate.of(y, m, d); 
	LocalTime curreTime=LocalTime.of(hours, minute);
	
	
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
		currentdiscount=Double.parseDouble(txtDiscout.getText());
		date=txtDate.getPromptText();
		from=txtFrom.getText();
		totime=txtTo.getText();
		
		testDatePicker=txtDate.getValue();
		
		//System.out.println(testDatePicker);
    	//System.out.println(currentprice);
		if(!(testDatePicker.isAfter(currtdate)))
    	{
    		Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setAlertType(AlertType.ERROR); 
					alert.setContentText("You choose a date or time wrong!!");
					alert.show(); 
				}
			});
    	}
    	else {
	    	Sales sales=new Sales(IDsales, comboFuelType.getValue(), txtDiscout.getText(), txtDate.getValue(), txtFrom.getText(), txtTo.getText());
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
    	}
	}
		
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		acainstance=this;		
		//chat.accept(new Message(35, null));
		gastypeValues.add("Gasoline 95");
		gastypeValues.add("Diesel fuel");
		gastypeValues.add("Scooters fuel");
		gastypeValues.add("Home Heating fuel");
		gastypeList.addAll(gastypeValues);
		comboFuelType.setItems(gastypeList);

	}


	public void FuelAcceptor(ArrayList<Sales> salmarketingmanagerList) {
		offer=new Sales(salmarketingmanagerList.get(0).getIDsales(), salmarketingmanagerList.get(0).getFuelType(), salmarketingmanagerList.get(0).getDiscount(), salmarketingmanagerList.get(0).getDate(), salmarketingmanagerList.get(0).getFormHour(), salmarketingmanagerList.get(0).getToHout());
		
		
	}

}
