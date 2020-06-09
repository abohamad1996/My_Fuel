package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.HomeHeatingOrder;
import Entity.Rates;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeHeatingOrderController implements Initializable{
	public static HomeHeatingOrderController acainstance;


    @FXML
    public Label status;
    @FXML
    private Button btnAddOrder;

    @FXML
    private TextField btnQuantity;

    @FXML
    private DatePicker dateSelect;

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
	public String finalPrice;
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
	
	public void HomeHeatingRatesAcceptor(String rate) {
		homeHeatingRate=rate;
		System.out.println("new rate:"+rate);
		  rates = Double.parseDouble(rate);
		}
	
	   @FXML
	    void AddNewOrder(ActionEvent event) {
		
		HomeHeatingOrderController.acainstance.details.accept(new Message(23, heatingOrder));
	    }
	
	    @FXML
	    void Calculate(ActionEvent event) {
	    	  date=dateSelect.getValue().toString();
			   quantity=Double.parseDouble(btnQuantity.getText());
			   urgent=txtUrgent.getValue();
			   heatingOrder=new HomeHeatingOrder(0, user.getId(), quantity, date, urgent, price, "in");
			 price =   heatingOrder.Calculate_Price_HomeHeating(quantity, urgent, rates);
			 heatingOrder.setPrice(price);
			finalPrice=String.valueOf(price);
			labelPrice.setVisible(true);
			labelPrice.setText(finalPrice);
	    }
	    @FXML
	    void CalculatePrice(KeyEvent event) {
	    	
	    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance=this;
		details.accept(new Message(22, null));
		labelRate.setVisible(true);
		labelRate.setText(homeHeatingRate);
		urgenttypeValues.add("Yes");
		urgenttypeValues.add("No");
		urgentList.addAll(urgenttypeValues);
		txtUrgent.setItems(urgentList);
	}

}
