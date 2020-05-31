package gui;

import java.net.URL;
import java.util.ResourceBundle;

import Entity.CreditCard;
import Entity.User;
import client.ClientConsole;
import client.Func;
import common.Message;
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

public class ClientRegisterController implements Initializable{
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
	    private Button btnNext;

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
	    private Button btnImage;
	    @FXML
		public Label status;
	    
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
    void btnNext(ActionEvent event) {
    	Integer status=0;
    	String rank="Client";
    	User user = new User(txtID.getText(), txtxFirstname.getText(), txtLastname.getText(), txtEmail.getText(), txtUsername.getText(), txtPassword.getText(), rank, status);
    /*	Integer status=0;
    	String rank="Client";
    	User user = new User(txtID.getText(), txtxFirstname.getText(), txtLastname.getText(), txtEmail.getText(), txtUsername.getText(), txtPassword.getText(), rank, status);
    	ClientRegisterController.acainstance.chat.accept(new Message(6, user));
       	String creditCard=txtCreditA.getText()+txtCreditB.getText()+txtCreditC.getText()+txtCreditD.getText();
    	System.out.println("creditcard:"+creditCard);
    	CreditCard card=new CreditCard(txtID.getText(), creditCard, txtMonth.getText(), txtYear.getText(), txtCVV.getText());
    	ClientRegisterController.acainstance.chat.accept(new Message(7, card));*/
    	CarRegisterController car;
    	car = new CarRegisterController();
    	runLater(() -> {
    		car.start(splitpane, user, "User");
});
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
		
	}
}
