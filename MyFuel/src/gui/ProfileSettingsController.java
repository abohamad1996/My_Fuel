package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Entity.User;
import client.ClientConsole;
import common.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProfileSettingsController implements Initializable{
	  @FXML
	    private TextField txtFirstName;

	    @FXML
	    private TextField txtLastName;

	    @FXML
	    private TextField txtID;

	    @FXML
	    private TextField txtEmail;

	    @FXML
	    private Button btnUpdate;
	    @FXML
	    private Button btnView;

	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/ProfileSetting.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}



    @FXML
    void ViewDetails(ActionEvent event) {
    	System.out.println("aaa");
    	}

    @FXML
    void UpdateDetails(ActionEvent event) {
    	System.out.println("bb");
    }

	


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}



}
