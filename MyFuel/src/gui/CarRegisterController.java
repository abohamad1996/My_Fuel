package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.Car;
import Entity.User;
import client.ClientConsole;
import client.Func;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CarRegisterController implements Initializable{
    ObservableList<String> planList =FXCollections.observableArrayList(); 
    ObservableList<String> serviceList =FXCollections.observableArrayList(); 

	ArrayList<String> planValues=new ArrayList<String>();
	ArrayList<String> serviceValues=new ArrayList<String>();
    @FXML
    private TextField txtID;
	@FXML
	    private Button btnNext;

	    @FXML
	    private TextField txtCarNumber;

	    @FXML
	    private ComboBox<String> comboPlan;

	    @FXML
	    private ComboBox<String> comboServices;
	    @FXML
	    private ComboBox<String> comboStation;

	    @FXML
	    private ComboBox<String> comboStation2;

	    @FXML
	    private ComboBox<String> comboStation3;
	    @FXML
	    private Label gasLabel1;

	    @FXML
	    private Label star;



	    @FXML
	    private Label gasLabel2;
	@FXML
	private static SplitPane splitpane;

    
    
 
    
	public ClientConsole chat= new ClientConsole("localhost", 5555);
	private FXMLLoader loader;	
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public void start(SplitPane splitpane, User user,String userJob) {
	this.splitpane=splitpane;
	primaryStage=LoginController.primaryStage;
	try{	
		loader = new FXMLLoader(getClass().getResource("/gui/CarRegister.fxml"));
		lowerAnchorPane = loader.load();
		splitpane.getItems().set(1, lowerAnchorPane);
	} catch(Exception e) {
		e.printStackTrace();
}	
}
	

    @FXML
    void PlanChoose(ActionEvent event) {
    	Car car=new Car(txtID.getText(), txtCarNumber.getText(), comboPlan.getValue(), comboServices.getValue(), null, null, null); 
    	String currentLevel=comboPlan.getValue();
    	if(currentLevel.equals("Level 1"))
    	{
    		//comboStation2.setValue("Null");
    		comboStation2.setVisible(false);
    		comboStation3.setVisible(false);
    	gasLabel1.setVisible(false);
    	gasLabel2.setVisible(false);
    	star.setVisible(false);
    	}
    	else if(currentLevel.equals("Level 2"))
    	{
    		//comboStation2.setValue("Null");
    		comboStation2.setVisible(true);
    		comboStation3.setVisible(true);
    	gasLabel1.setVisible(true);
    	gasLabel2.setVisible(true);
    	star.setVisible(true);
    	}
    }
	
    @FXML
    void Next(ActionEvent event) {
    	
    	Car car=new Car(txtID.getText(), txtCarNumber.getText(), comboPlan.getValue(), comboServices.getValue(), null, null, null); 
    	String currentLevel=comboPlan.getValue();
    	if(currentLevel.equals("Level 1"))
    	{
    		
    	}
    	else if(currentLevel.equals("Level 2")){
        	MultiGasStationController gasStation;
        	gasStation = new MultiGasStationController();
        	runLater(() -> {
        		gasStation.start(splitpane, car, "User");
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
		planValues.add("Level 1");
		planValues.add("Level 2");
		serviceValues.add("Casual fueling");
		serviceValues.add("Regular monthly 1 car");
		serviceValues.add("Regular monthly +1");
		serviceValues.add("Full monthly");
		planList.addAll(planValues);
		serviceList.addAll(serviceValues);
		comboPlan.setItems(planList);
		comboServices.setItems(serviceList);
	}

}
