package gui;

import java.net.URL;
import java.util.ResourceBundle;

import Entity.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * This class represents a controller for the gui  Login 
 * when the user click on button logout
 *
 */

public class LogoutController implements Initializable{
	private Stage primaryStage;
	/** This methode load the information into the gui
	*
	* @param user this paramater from the type user that contains id,firtstname,lastname,email,username,password,rank,clienttype,status,image
	* @param primaryStage  appearance of the software
	*/

	public void start(Stage primaryStage, User user) {

		try {
			this.primaryStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("My Fuel Login");
			primaryStage.show();
			primaryStage.setOnCloseRequest(event -> {
				System.out.println("My Fuel Exit");
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
					
	
		
	
	}
	
	
}

