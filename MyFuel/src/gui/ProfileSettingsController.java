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
    public static ProfileSettingsController acainstance;
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
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public ClientConsole details= new ClientConsole("localhost", 5555);
	ArrayList<User> userdetails= new ArrayList<User>();
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

	public void Acceptor(ArrayList<User> a) {	
		userdetails.addAll(a);
}


    @FXML
    void UpdateDetails(ActionEvent event) {
    	System.out.println("bb");
    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;
		//details.accept(new Message(4, null));
		details.accept(new Message(4, null));
		txtFirstName.setText(userdetails.get(0).getFirstname());
		txtLastName.setText(userdetails.get(0).getLastname());	
		txtID.setText(userdetails.get(0).getId());	
		txtEmail.setText(userdetails.get(0).getEmail());	
	}



}
