package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.User;
import client.ClientConsole;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PurchasePlanController implements Initializable{
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
		User detailsUser;
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
	
	
	
	

		
		
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
