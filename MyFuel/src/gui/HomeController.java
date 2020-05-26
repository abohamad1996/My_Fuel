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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class HomeController implements Initializable{
	
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
				//	scene.getStylesheets().add(getClass().getResource("/gui/css2.css").toExternalForm());
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


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
