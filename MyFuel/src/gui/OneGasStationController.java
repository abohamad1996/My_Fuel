package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.glass.events.MouseEvent;

import Entity.Car;
import Entity.User;
import client.ClientConsole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OneGasStationController implements Initializable{

	@FXML
	private static SplitPane splitpane;
	
	   @FXML
	    private Button btnNext;

	    @FXML
	    private ComboBox<String> comboStation;


	   public Car car2  ;
	
	    ObservableList<String> gasstationList =FXCollections.observableArrayList(); 
		ArrayList<String> gasstationValues=new ArrayList<String>();
	public ClientConsole chat= new ClientConsole("localhost", 5555);
	private FXMLLoader loader;	
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public void start(SplitPane splitpane, Car car  ,String userJob) {
		car2=new Car(car.getOwnerID(), car.getCarNumber(), car.getPurchasePlan(), car.getServices(), null, null, null);		
	this.splitpane=splitpane;
	primaryStage=LoginController.primaryStage;
	try{	
		loader = new FXMLLoader(getClass().getResource("/gui/StationRegisterPurchasePlan1.fxml"));
		lowerAnchorPane = loader.load();
		splitpane.getItems().set(1, lowerAnchorPane);
		System.out.println(""+car2.getCarNumber());
	} catch(Exception e) {
		e.printStackTrace();
}	
}
	
    @FXML
    void Next(ActionEvent event) {
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	car2.setGasStation1(comboStation.getValue());
    	System.out.println(""+car2.getGasStation1());
    	
    }
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gasstationValues.add("Paz Station");
		gasstationValues.add("Yellow Station");
		gasstationValues.add("Ten Station");
		gasstationValues.add("Sonol Station");
		gasstationList.addAll(gasstationValues);
		comboStation.setItems(gasstationList);
	}

}
