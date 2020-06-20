package gui;

import java.awt.Label;
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
/**
 * This class represents a controller for the gui PurchasePlan
 * @author mahmod odeh
 *
 */
public class PurchasePlanController implements Initializable{
    public static PurchasePlanController acainstance;
	
    @FXML
    private Label labelIDD;
    @FXML
    private TextField txtRate;

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
		ArrayList<Car> car2=new ArrayList<Car>();
		Car detailsCar;
	    ObservableList<String> List =FXCollections.observableArrayList(); 
	    ObservableList<Car> CarList =FXCollections.observableArrayList(); 
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
				loader = new FXMLLoader(getClass().getResource("/gui/ClientPurchasePlan.fxml"));
				lowerAnchorPane = loader.load();
				splitpane.getItems().set(1, lowerAnchorPane);
			} catch(Exception e) {
				e.printStackTrace();
		}		
	}
	
		/**
		* This methode get notification for thecar
		* @param car this paramater from car type car and contains ownerid,carnumber,purchaseplane,services,gastype,gasstaion1,gasstaion2,gasstaion3,rateforcar
		*/
		public void CarAcceptor(ArrayList<Car> car) {
			
			car2.addAll(car);
			//CarList.addAll(car);
		}
		/**
		* This methode transform to string
		* @param bb
		*/
		public void Car2Acceptor(ArrayList<String> bb) {
			List.addAll(bb);		
		}
		/**
		* This methode get the details of the car
		* @param event
		*/
	    @FXML
	    void ShowDetails(ActionEvent event) {
	    	Integer index;
	    	String carnumber=comboCar.getValue();
	    	index=getCarNumberPos(carnumber);
	    	//System.out.println(""+index);
	 	    	txtID.setText(car2.get(index).getOwnerID());
				txtGasStation.setText(car2.get(index).getGasStation1());
				txtGasStation2.setText(car2.get(index).getGasStation2());
				txtGasStation3.setText(car2.get(index).getGasStation3());
				txtPurchaseplan.setText(car2.get(index).getPurchasePlan());
				txtServices.setText(car2.get(index).getServices());
				txtGastype.setText(car2.get(index).getGastype());
				txtCarnumber.setText(car2.get(index).getCarNumber());
				txtRate.setText(car2.get(index).getRateForCar());
				System.out.println(car2.toString());
	        }
	    /**
	    * 
	    * @param carnumber this parameter of the type string
	    * @return the index of the car
	    */
	    private int getCarNumberPos(String carnumber) {
	    	for(int i = 0; i < this.car2.size(); ++i) {
	            if(this.car2.get(i).getCarNumber().equals(carnumber)) 
	            	return i;
	    	}
	        return -1;
	    }
	    //	System.out.println(""+car2.get(1).getCarNumber());
	    	//System.out.println(""+comboCar.getValue());
	    //	System.out.println(""+CarList.get(1).getOwnerID());
	   // 	txtID.setText(detailsCar.getOwnerID());
		//	txtGasStation.setText(detailsCar.getGasStation1());
		//	txtGasStation2.setText(detailsCar.getGasStation2());
		//	txtGasStation3.setText(detailsCar.getGasStation3());
		//	txtPurchaseplan.setText(detailsCar.getPurchasePlan());
		//	txtServices.setText(detailsCar.getServices());
		//	txtGastype.setText(detailsCar.getGastype());
		//	txtCarnumber.setText(detailsCar.getCarNumber());
	    
	    /**
	     * This methode get notification from the chatclient
	     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		acainstance = this;		
		details.accept(new Message(10, null));
		details.accept(new Message(11, null));
		comboCar.setItems(List);
	}

}
