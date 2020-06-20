package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.NEW;

import client.ClientConsole;
import client.ClientLogin;
import common.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * 
 *This class represents a controller for the gui 
 *  Login
 *
 */

public class LoginController implements Initializable {
	@FXML    
	private TextField Username;

    @FXML
    private Button btnForget;
	    @FXML
	    private TextField Password;

	    @FXML
	    private Button btnLogin;
		public ClientConsole login= new ClientConsole("localhost", 5555);
		public static ClientConsole client;

		public static Stage primaryStage;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * this method used if the user forget the password 
	 * @param event
	 */

    @FXML
    void Forget(ActionEvent event) {
    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setAlertType(AlertType.INFORMATION); 
				alert.setContentText("Send An Email to Marketing Representative \nEmail:bshara.b94@gmail.com \nPhone Number:0521234567");
				alert.show(); 
			}
		});
    	
    }
    	
    	
    /**
     * 
     * @param event of button
     */

	@FXML
    void LoginAction(ActionEvent event) {
    	String username = Username.getText();
		String password = Password.getText();
		String[] loginMessage = new String[3];
		loginMessage[0] = "login";
		loginMessage[1] = username;
		loginMessage[2] = password;
		login.accept(new Message(3, loginMessage));
    }
	/** This methode load the information into the gui
	*
	*
	* @param primaryStage  appearance of the software
	*/

	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			Parent root;
		    root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/gui/css2.css").toExternalForm());
			this.primaryStage.setScene(scene);
			this.primaryStage.setResizable(false);
			this.primaryStage.setTitle("My Fuel Login");
			this.primaryStage.show();
			this.primaryStage.setOnCloseRequest(event -> {
				System.out.println("My Fuel Closed");
				System.exit(0);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return  how appearance of the software
	 */

	public Stage getStage() {
		return primaryStage;
	}
}
