package gui;

import java.net.URL;
import java.util.ResourceBundle;

import Entity.User;
import client.ClientConsole;
import client.Func;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CarRegisterDetails implements Initializable {
	
	
	@FXML
	private static SplitPane splitpane;
	String a;
    User user;
	public ClientConsole chat= new ClientConsole("localhost", 5555);
	private FXMLLoader loader;	
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;

	public void start(SplitPane splitpane, User user,String userJob) {
	this.splitpane=splitpane;
	this.user=user;
	primaryStage=LoginController.primaryStage;
	try{	
		loader = new FXMLLoader(getClass().getResource("/gui/CarRegisterDetails.fxml"));
		lowerAnchorPane = loader.load();
		splitpane.getItems().set(1, lowerAnchorPane);
	} catch(Exception e) {
		e.printStackTrace();
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
