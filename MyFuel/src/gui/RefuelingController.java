package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import Entity.Car;
import Entity.User;
import client.ClientConsole;
import common.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RefuelingController implements Initializable{
	
    public static RefuelingController acainstance;

	 @FXML
	    private Button btnStart;

	    @FXML
	    private Label labelCarNumber;
	    @FXML
	    private Label labelFuelTyple;

	    @FXML
	    private Label labelGasStation;

	    @FXML
	    private Label labelPump;
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
	User detailsUser;
	ArrayList<Car> car2=new ArrayList<Car>();
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/ClientRefuling.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	

	public void CarAcceptor(ArrayList<Car> car) {
		car2.addAll(car);
		//CarList.addAll(car);
	}
    @FXML
    void StartRefueilng(ActionEvent event) {
    	
    }
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;		
		details.accept(new Message(15, null));
		Random rn = new Random();
		int maximum =(car2.size()-1);
		System.out.println(maximum);
		int minimum=0;
		int range = maximum - minimum + 1;
		int randomNum =  rn.nextInt(range) + minimum;
		String s=String.valueOf(randomNum+1);
		labelCarNumber.setText(car2.get(randomNum).getCarNumber());
		labelCarNumber.setVisible(true);
		labelFuelTyple.setText(car2.get(randomNum).getGastype());
		labelFuelTyple.setVisible(true);
		labelGasStation.setText(car2.get(randomNum).getGasStation1());
		labelGasStation.setVisible(true);
		labelPump.setText(s);
		labelPump.setVisible(true);
		}
}
