package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Entity.User;
import client.ClientConsole;
import common.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * This class represents a controller for the gui ProfileSettings
 * @author  mahmoud odeh
 *
 */
public class ProfileSettingsController implements Initializable{
    public static ProfileSettingsController acainstance;
	  @FXML
	    private TextField txtFirstName;
	    @FXML
	    private ImageView imageProfile;
	    @FXML
	    private TextField txtLastName;

	    @FXML
	    private TextField txtID;

	    @FXML
	    private TextField txtEmail;

	    @FXML
	    private Button btnUpdate;
	    @FXML
		public Label status;
	    @FXML
	    private Button btnPasswordChange;

	    @FXML
	    private Label labelPassword;

	    @FXML
	    private Label labelConfirm;

	    @FXML
	    private TextField txxPassword;

	    @FXML
	    private TextField txtConfirm;

	    @FXML
	    private Button btnSetPassword;
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
	User detailsUser;
	/**
	* 
	* @param splitpane this parameter from the type splitpane
	* @param user this paramater from the type user that contains id,firtstname,lastname,email,username,password,rank,clienttype,status,image
	* @param userJob this parameter from the type string
	*/
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
	/**
	 * 
	 * @param user this paramater from the type user that contains id,firtstname,lastname,email,username,password,rank,clienttype,status,image
	 */
	public void Acceptor(User user) {
	 detailsUser=new User(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRank(),user.getClientType(), user.getStatus(),user.getImage());
	}

	/**
	 * This methode get notification from the chatclient
	 * @param event event of button
	 */
    @FXML
    void UpdateDetails(ActionEvent event) {
    	if(txtID.getText().isEmpty() ||txtEmail.getText().isEmpty()|| txtFirstName.getText().isEmpty()|| txtLastName.getText().isEmpty())
    	{
    		Platform.runLater(new Runnable() {
    			@Override
    			public void run() {
    				Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setAlertType(AlertType.INFORMATION); 
    				alert.setContentText("There is missing field");
    				alert.show(); 
    			}
    		});
    		
    		
    		
    	}
    	else {
    	User user=new User(txtID.getText(), txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), null, null, null, null,null,null);
    	ProfileSettingsController.acainstance.details.accept(new Message(5, user));;
    	Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setAlertType(AlertType.INFORMATION); 
					alert.setContentText("Details updated!");
					alert.show(); 
				}
			});
    }
    }
    /**
     * This methode give the user accesbility to change the password
     * @param event event of button
     */
    @FXML
    void ChangePassword(ActionEvent event) {
    	btnPasswordChange.setVisible(true);
    	labelPassword.setVisible(true);
    	labelConfirm.setVisible(true);
    	txxPassword.setVisible(true);
    	txtConfirm.setVisible(true);
    	btnSetPassword.setVisible(true);
    }
    /**
     * This methode make the user to reset the password 
     * @param event event of button
     */
    @FXML
    void SetPassword(ActionEvent event) {
    	System.out.println(txxPassword.getText());
    	System.out.println(txtConfirm.getText());
    	if(txxPassword.getText().isEmpty() || txtConfirm.getText().isEmpty())
    	{
    		
    		Platform.runLater(new Runnable() {
    			@Override
    			public void run() {
    				Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setAlertType(AlertType.INFORMATION); 
    				alert.setContentText("There is missing field");
    				alert.show(); 
    			}
    		});
    	}
    	else if(txxPassword.getText().equals(txtConfirm.getText()))
    	{
        	User user2=new User(txtID.getText(), txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), null, txxPassword.getText(), null, null,null,null);
        	ProfileSettingsController.acainstance.details.accept(new Message(12, user2));;
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setAlertType(AlertType.INFORMATION); 
			alert.setContentText("Password Changes Successfully!!");
			alert.show(); 
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setAlertType(AlertType.ERROR); 
			alert.setContentText("Password not match");
			alert.show(); 
		}
    }
    

    /**
     * This methode get notification from the chatclient
     */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;
		details.accept(new Message(4, null));
		txtID.setText(detailsUser.getId());	
		txtFirstName.setText(detailsUser.getFirstname());
		txtLastName.setText(detailsUser.getLastname());	
		txtEmail.setText(detailsUser.getEmail());
	}

}
