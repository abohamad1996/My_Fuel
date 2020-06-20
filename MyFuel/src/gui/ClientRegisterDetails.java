package gui;

import java.net.URL;
import java.util.ResourceBundle;

import Entity.Car;
import Entity.User;
import client.ClientConsole;
import client.Func;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * 
 *  This class represents a controller for the gui 
 *  Client Register Details that the register is successfully
 *
 */

public class ClientRegisterDetails implements Initializable{

    @FXML
    private Label status;

    @FXML
    private TextField txtType;

    @FXML
    private Label labelID;

    @FXML
    private Label labelFirstname;

    @FXML
    private Label labelLastname;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelUser;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelDredit;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelcvv;
	@FXML
	private static SplitPane splitpane;
	
	  @FXML
	    private Button btntest;

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
		loader = new FXMLLoader(getClass().getResource("/gui/ClientRegisterDetails.fxml"));
		lowerAnchorPane = loader.load();
		splitpane.getItems().set(1, lowerAnchorPane);
	} catch(Exception e) {
		e.printStackTrace();
}	
}
	
	
	
	/**
	 *  This method run all the buttons in the gui
     * @param f type for function
	 */

	
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
	}

}
