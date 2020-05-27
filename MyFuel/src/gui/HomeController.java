package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;


import Entity.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class HomeController implements Initializable{
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
    @FXML
    private MenuButton UserMenu;
	  @FXML
	    private AnchorPane btnPurchase;
	    @FXML
	    private Button btnAbout;

	    @FXML
	    private Button btnHome;

	    @FXML
	    private Button btnProfile;

	    @FXML
	    private Button btnRegister;

	    @FXML
	    private Button btnOrderHeating;

	    @FXML
	    private Button btnRefuling;

	    @FXML
	    private Button btnRegister1;

	    @FXML
	    private Label labelDir;
	    
	    @FXML
	    void AboutButton(ActionEvent event) {
	    	System.out.println("About");
	    }

	    @FXML
	    void HomeButton(ActionEvent event) {
	    	System.out.println("Home");
	    }

	    @FXML
	    void HomeHeating(ActionEvent event) {
	    	System.out.println("HomeHeating");
	    }

	    @FXML
	    void ProfileSettingButton(ActionEvent event) {
	    	System.out.println("ProfileSettings");
	    }

	    @FXML
	    void PurchasePlanButton(ActionEvent event) {
	    	System.out.println("Purchase Plan");
	    }

	    @FXML
	    void RefulingButton(ActionEvent event) {
	    	System.out.println("Refuling");
	    }

	    @FXML
	    void RegisterClientButton(ActionEvent event) {
	    	System.out.println("Register Client");
	    }
	    
	    
	public static HomeController s;
	public static Stage primaryStage;
	public void start(User inspector) {
		s = this;
		primaryStage = LoginController.primaryStage;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					Parent root;
					root = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/gui/css2.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.setResizable(false);
					primaryStage.setTitle("Home");
					primaryStage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}



}
