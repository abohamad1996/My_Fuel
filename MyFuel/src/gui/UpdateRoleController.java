package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.ws.client.sei.ValueSetter;

import DBconnection.DBconnector;
import common.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateRoleController implements Initializable{
	
	public static UpdateRoleController instance;
	
    ObservableList<String> List =FXCollections.observableArrayList(); 
ArrayList<String> values=new ArrayList<String>();
    @FXML
    private TextField txtEmpnum;

    @FXML
    private ComboBox<String> boxRole;
    
    @FXML
    private Button btnUpdateRole;
    
    @FXML
    public Label status;

    @FXML
    void Update_Role(ActionEvent event) {
    	Employee employee=new Employee(null, null, txtEmpnum.getText(), null, boxRole.getValue(), null);
  
    	EmployeeFrameController.acainstance.chat.accept(new Message(2, employee));;
}
    @FXML
    void Update_Values(ActionEvent event) {

    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		values.add("Private Client");
		values.add("Company Client");
		values.add("Station Manager");
		values.add("Marketing Manager");
		values.add("Network Manager");
		values.add("Marketing Representative");
		List.addAll(values);
		boxRole.setItems(List);
	}
}