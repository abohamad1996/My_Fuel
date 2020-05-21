package gui;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1;
import com.sun.corba.se.pept.transport.Connection;
import com.sun.corba.se.spi.activation.Server;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import DBconnection.DBconnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Server.EchoServer;
import Server.EchoServer.sqlcon;
import client.ChatClient;
import client.ClientConsole;
import common.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public  class EmployeeFrameController implements Initializable {

       public static EmployeeFrameController acainstance;
	   @FXML
	    private TableView<Employee> table;

	    @FXML
	    private TableColumn<Employee, String> firstname;

	    @FXML
	    private TableColumn<Employee, String> lasetname;

	    @FXML
	    private TableColumn<Employee, String> numberemp;

	    @FXML
	    private TableColumn<Employee, String> email;

	    @FXML
	    private TableColumn<Employee, String> role;

	    @FXML
	    private TableColumn<Employee, String> org;
	@FXML
	private Button btnSend = null;
    @FXML
    private Button btnUpdate;
    ObservableList<Employee> List =FXCollections.observableArrayList(); 
	public ClientConsole chat= new ClientConsole("localhost", 5555);

	public void TestAcceptor1(ArrayList<Employee> a) {	
			List.addAll(a);
	}
	public void Send(ActionEvent event) throws Exception {
		List.clear();
		chat.accept(new Message(1, null));
		table.setItems(List);
		
		}
		
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("/gui/EmployeeFrame.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui/EmployeeFrame.css").toExternalForm());
		primaryStage.setTitle("MyFuel Client");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
    @FXML
    void UpdateRole(ActionEvent event) {
    	try {
    	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateRole.fxml"));
    	    Parent root1 = (Parent) fxmlLoader.load();
    	    Stage stage = new Stage();
    	    stage.setTitle("Update Employee");
    	    stage.setScene(new Scene(root1));  
    	    stage.show();
    	    
    	}
    	catch (Exception e) {
			// TODO: handle exception
		}
    }
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;
		chat.accept(new Message(1, null));
		firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
		lasetname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		numberemp.setCellValueFactory(new PropertyValueFactory<>("empnum"));		
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		role.setCellValueFactory(new PropertyValueFactory<>("role"));
		org.setCellValueFactory(new PropertyValueFactory<>("org"));
	}
}
	

