package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Entity.Rates;
import Entity.User;
import client.ClientConsole;
import client.Func;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MarketingManagerRateController implements Initializable {
	public static MarketingManagerRateController acainstance;
  
    @FXML
    private Label labelGasoline;

    @FXML
    private Label labelDiesel;

    @FXML
    private Label labelScooter;

    @FXML
    private Label labelHomeHeating;
	@FXML
	public Label status;
    @FXML
    private ComboBox<String> comboFuelType;

    @FXML
    private TextField txtRate;
    @FXML
    private Button btnSend;
	@FXML
	private static SplitPane splitpane;
    public ClientConsole chat= new ClientConsole("localhost", 5555);
	Rates Gasoline,Diesel,Scotter,HomeHeating;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	double currentprice;
	double priceGasoline;
	double priceDiesel;
	double priceScooter;
	double priceHomeHeating;
    ObservableList<String> gastypeList =FXCollections.observableArrayList(); 	
		ArrayList<String> gastypeValues=new ArrayList<String>();
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/MarketingManagerRate.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	public void RatesAcceptor(ArrayList<Rates> maxPrice) {
		Gasoline=new Rates(maxPrice.get(0).getFuelType(), maxPrice.get(0).getPrice());
		Diesel=new Rates(maxPrice.get(1).getFuelType(), maxPrice.get(1).getPrice());
		Scotter=new Rates(maxPrice.get(2).getFuelType(), maxPrice.get(2).getPrice());
		HomeHeating=new Rates(maxPrice.get(3).getFuelType(), maxPrice.get(3).getPrice());
		priceGasoline=Double.parseDouble(Gasoline.getPrice());
		priceDiesel=Double.parseDouble(Diesel.getPrice());
		priceScooter=Double.parseDouble(Scotter.getPrice());
		priceHomeHeating=Double.parseDouble(HomeHeating.getPrice());
		System.out.println(""+priceGasoline+priceDiesel+priceScooter+priceHomeHeating);
		}
		
    @FXML
    void SendToApprove(ActionEvent event) {
    	currentprice=Double.parseDouble(txtRate.getText());
    	//System.out.println(currentprice);
    	if(comboFuelType.getValue().equals("Gasoline 95")&&currentprice>priceGasoline)
    	{
    		Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setAlertType(AlertType.ERROR); 
					alert.setContentText("You choose a high price look at the list!!");
					alert.show(); 
				}
			});
    		
    	}
    	else if(comboFuelType.getValue().equals("Diesel fuel")&&currentprice>priceDiesel)
    	{
    		Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setAlertType(AlertType.ERROR); 
					alert.setContentText("You choose a high price look at the list!!");
					alert.show(); 
				}
			});
    	}
    		else if(comboFuelType.getValue().equals("Scooters fuel")&&currentprice>priceScooter)
        	{
        		Platform.runLater(new Runnable() {
    				@Override
    				public void run() {
    					Alert alert = new Alert(AlertType.ERROR);
    					alert.setAlertType(AlertType.ERROR); 
    					alert.setContentText("You choose a high price look at the list!!");
    					alert.show(); 
    				}
    			});
        	}
    		else if(comboFuelType.getValue().equals("Home Heating fuel")&&currentprice>priceHomeHeating)
        	{
        		Platform.runLater(new Runnable() {
    				@Override
    				public void run() {
    					Alert alert = new Alert(AlertType.ERROR);
    					alert.setAlertType(AlertType.ERROR); 
    					alert.setContentText("You choose a high price look at the list!!");
    					alert.show(); 
    				}
    			});
				}
    		else {
    	    	Rates rates=new Rates(comboFuelType.getValue(), txtRate.getText());
    	    	MarketingManagerRateController.acainstance.chat.accept(new Message(16, rates));
    	    	Platform.runLater(new Runnable() {
    				@Override
    				public void run() {
    					Alert alert = new Alert(AlertType.ERROR);
    					alert.setAlertType(AlertType.ERROR); 
    					alert.setContentText("Rate changed successfully!!!");
    					alert.show(); 
    				}
    			});
        	}
    
    	}
	@SuppressWarnings("unused")
	private void runLater(Func f) {
		f.call();
		Platform.runLater(() -> {
			try {
				Thread.sleep(10);
				f.call();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		acainstance=this;		
		 chat.accept(new Message(30, null));
		gastypeValues.add("Gasoline 95");
		gastypeValues.add("Diesel fuel");
		gastypeValues.add("Scooters fuel");
		gastypeValues.add("Home Heating fuel");
		gastypeList.addAll(gastypeValues);
		comboFuelType.setItems(gastypeList);
		labelGasoline.setText(Gasoline.getPrice());
		labelDiesel.setText(Diesel.getPrice());
		labelScooter.setText(Scotter.getPrice());
		labelHomeHeating.setText(HomeHeating.getPrice());
	}


}
