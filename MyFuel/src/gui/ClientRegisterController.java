package gui;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.mysql.cj.result.Field;
import com.sun.imageio.plugins.common.I18N;
import com.sun.prism.Image;

import Entity.CreditCard;
import Entity.User;
import client.ClientConsole;
import client.Func;
import common.Message;
import common.MyFile;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ClientRegisterController implements Initializable{
	 MyFile msg= new MyFile("image.jpg");
	public static ClientRegisterController acainstance;
	@FXML
	private static SplitPane splitpane;
	   @FXML
	    private TextField txtxFirstname;

	    @FXML
	    private TextField txtLastname;

	    @FXML
	    private TextField txtEmail;

	    @FXML
	    private TextField txtID;

	    @FXML
	    private Button btnRegister;

	    @FXML
	    private TextField txtMonth;

	    @FXML
	    private TextField txtCreditA;

	    @FXML
	    private TextField txtCreditB;

	    @FXML
	    private TextField txtCreditC;

	    @FXML
	    private TextField txtCreditD;

	    @FXML
	    private TextField txtYear;

	    @FXML
	    private TextField txtCVV;

	    @FXML
	    private TextField txtUsername;

	    @FXML
	    private TextField txtPassword;

	
	    @FXML
		public Label status;
	    @FXML
	    private ComboBox<String> comboType;
	    @FXML
	    private ImageView imageProfile;
	    ObservableList<String> TypeList =FXCollections.observableArrayList(); 

		ArrayList<String> TypeValues=new ArrayList<String>();
		private	FileInputStream fis;
	private 	File file;

		public ClientConsole chat= new ClientConsole("localhost", 5555);
	private FXMLLoader loader;	
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/ClientRegister.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
    @FXML
    void Register(ActionEvent event) {
    	Integer status=0;
    	String rank="Client";
    	User user = new User(txtID.getText(), txtxFirstname.getText(), txtLastname.getText(), txtEmail.getText(), txtUsername.getText(), txtPassword.getText(), rank,comboType.getValue(), status,0);

    	if(txtID.getText().isEmpty() || txtxFirstname.getText().isEmpty()|| txtLastname.getText().isEmpty()|| txtEmail.getText().isEmpty()|| txtUsername.getText().isEmpty() ||txtPassword.getText().isEmpty())
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
    		ClientRegisterController.acainstance.chat.accept(new Message(6, user));
 
 
       	String creditCard=txtCreditA.getText()+txtCreditB.getText()+txtCreditC.getText()+txtCreditD.getText();
    	CreditCard card=new CreditCard(txtID.getText(), creditCard, txtMonth.getText(), txtYear.getText(), txtCVV.getText());
    	ClientRegisterController.acainstance.chat.accept(new Message(7, card));
    	ClientRegisterDetails clientRegister;
    	clientRegister = new ClientRegisterDetails();
    	runLater(() -> {
    		clientRegister.start(splitpane, user, "User");
});}
    }
    	
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
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;
	//	chat.accept(new Message(6, null));
		TypeValues.add("Private Client");
		TypeValues.add("Company Client");
		TypeList.addAll(TypeValues);
		comboType.setItems(TypeList);
	}
}
