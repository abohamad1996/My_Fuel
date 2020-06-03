package gui;

import java.net.URL;
import java.util.ResourceBundle;

import Entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OrderConfirmationController implements Initializable {
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/OrderConfirmation.fxml"));
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
