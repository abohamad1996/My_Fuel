package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.Car;
import Entity.User;
import client.ClientConsole;
import common.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PurchasePlanController implements Initializable{
    public static PurchasePlanController acainstance;

	 @FXML
	    private ComboBox<String> comboCar;

	    @FXML
	    private TextField txtPurchaseplan;

	    @FXML
	    private TextField txtGasStation;

	    @FXML
	    private TextField txtGasStation2;

	    @FXML
	    private TextField txtServices;

	    @FXML
	    private TextField txtGastype;

	    @FXML
	    private TextField txtCarnumber;

	    @FXML
	    private TextField txtGasStation3;

	    @FXML
	    private TextField txtID;
	
	
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
		Car detailsCar;
	    ObservableList<String> List =FXCollections.observableArrayList(); 
		public void start(SplitPane splitpane, User user,String userJob) {
			this.splitpane=splitpane;
			this.user=user;
			this.userrank=userJob;
			primaryStage=LoginController.primaryStage;
			try{	
				loader = new FXMLLoader(getClass().getResource("/gui/ClientPurchasePlan.fxml"));
				lowerAnchorPane = loader.load();
				splitpane.getItems().set(1, lowerAnchorPane);
			} catch(Exception e) {
				e.printStackTrace();
		}		
	}
	
	
		public void CarAcceptor(Car car) {
			detailsCar=new Car(car.getOwnerID(), car.getCarNumber(), car.getPurchasePlan(), car.getServices(), car.getGastype(), car.getGasStation1(), car.getGasStation2(), car.getGasStation3());
		}
	
		public void Car2Acceptor(ArrayList<String> bb) {
			List.addAll(bb);		
		}
	    @FXML
	    void ShowDetails(ActionEvent event) {
	    	if(comboCar.getValue().equals(detailsCar.getCarNumber())) {
	    	System.out.println(""+comboCar.getValue());
			txtID.setText(detailsCar.getOwnerID());
			txtGasStation.setText(detailsCar.getGasStation1());
			txtGasStation2.setText(detailsCar.getGasStation2());
			txtGasStation3.setText(detailsCar.getGasStation3());
			txtPurchaseplan.setText(detailsCar.getPurchasePlan());
			txtServices.setText(detailsCar.getServices());
			txtGastype.setText(detailsCar.getGastype());
			txtCarnumber.setText(detailsCar.getCarNumber());}
	    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		acainstance = this;		
		details.accept(new Message(10, null));
		details.accept(new Message(11, null));
		comboCar.setItems(List);
	}

}
