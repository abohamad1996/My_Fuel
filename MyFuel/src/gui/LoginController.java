package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.NEW;

import client.ClientConsole;
import client.ClientLogin;
import common.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	@FXML    
	private TextField Username;

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
	public Stage getStage() {
		return primaryStage;
	}

}
