package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.User;
import client.ClientConsole;
import client.Func;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeHeatingController implements Initializable{
	public static	HomeHeatingOrderController  homeHeatingOrder;
	public static	HomeHeatingOrderTrackController  homeHeatingTrackOrder;
    @FXML
    private Button btnAddOrder;

    @FXML
    private Button btnTrackOrder;
	
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
			loader = new FXMLLoader(getClass().getResource("/gui/HomeHeatingMain.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	
	
	
	
    @FXML
    void AddNewOrder(ActionEvent event) {
    	homeHeatingOrder =new HomeHeatingOrderController();
    	runLater(() -> {
    		homeHeatingOrder.start(splitpane, user, "User");
});
}

    @FXML
    void TrackOrders(ActionEvent event) {
    	homeHeatingTrackOrder =new HomeHeatingOrderTrackController();
    	runLater(() -> {
    		homeHeatingTrackOrder.start(splitpane, user, "User");
});
    }
	
	
	
	
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
