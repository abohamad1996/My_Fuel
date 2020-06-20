package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Server.EchoServer;
import Server.ServerUI;
/**
 * This class represents a controller for the gui ServerPortFrame
 * @author mahmoud odeh
 *
 */
public class ServerPortFrameController  {
	
	String temp="";
	
	@FXML
	private Button btnStart = null;
	@FXML
	private Label lbllist;
	@FXML
	private TextField iptxt;
	@FXML
	private TextField portxt;
    @FXML
    private Label status;
	ObservableList<String> list;
	private String getport()
	{
		return portxt.getText();			
	}
	private String getip()
	{
		return iptxt.getText();			
	}
	/**
	* This methode to get p and ip for the server
	* @param event
	* @throws Exception
	*/
	public void Done(ActionEvent event) throws Exception {
		String p;
		String ip;
		//((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		p=getport();
		ip=getip();
		if(p.trim().isEmpty() || ip.trim().isEmpty())
		{
			System.out.println("You must enter a ip and port");
			Parent root1 = FXMLLoader.load(getClass().getResource("/gui/ServerPort.fxml"));
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("/gui/ServerPort.css").toExternalForm());
			primaryStage.setTitle("MyFuel Server");
			primaryStage.setScene(scene);
			primaryStage.show();		
		}
		else
		{
		    status.setTextFill(Color.web("#2FFE00"));
			status.setText("Server ON");
			ServerUI.runServer(p);
		}
	}
	/**
	 * This methode to start th server
	 * @param primaryStage
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerPort.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui/ServerPort.css").toExternalForm());
		primaryStage.setTitle("MyFuel Server");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	

}