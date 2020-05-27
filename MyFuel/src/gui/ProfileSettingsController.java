package gui;

import java.net.URL;
import java.util.ResourceBundle;

import Entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProfileSettingsController implements Initializable{


	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public void start(SplitPane splitpane, User user,String userrank) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userrank;
		primaryStage=LoginController.primaryStage;
		Object[] msg=new String[3];
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/ProfileSetting.fxml"));
			splitpane=loader.load();
			msg[0]="ProfileSetting";
			msg[1]=user.getUsername();
			msg[2]=userrank;
			System.out.println("ProfileSettingContoller");
			System.out.println(msg);

		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
