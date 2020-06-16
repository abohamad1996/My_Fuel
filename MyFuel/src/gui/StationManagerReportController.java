package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.User;
import client.ClientConsole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StationManagerReportController implements Initializable {
	

    @FXML
    private ComboBox<String> comboReportType;

 

    @FXML
    private Button btnSend;
	ArrayList<String> ReportValues=new ArrayList<String>();
    ObservableList<String> Report =FXCollections.observableArrayList(); 
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
	ArrayList<String> ReportType=new ArrayList<String>();
	 ObservableList<String> ReportList =FXCollections.observableArrayList(); 
	User detailsUser;
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/StationManagerNewReports.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	
	
    @FXML
    void SendReport(ActionEvent event) {
    
    }

    
    @FXML
    void SelectReport(ActionEvent event) {
    		if(comboReportType.getValue().equals("Quarterly Revenue Report"))
    		{
    			System.out.println("1");
    		}
    		else if(comboReportType.getValue().equals("Purchases Report"))
    		{
    			
    			System.out.println("2");
    		}
    		else if(comboReportType.getValue().equals("Quantity in stock Report"))
    		{
    			System.out.println("3");
    			}
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ReportType.add("Quarterly Revenue Report");
		ReportType.add("Purchases Report");
		ReportType.add("Quantity in stock Report");
		Report.addAll(ReportType);
		comboReportType.setItems(Report);
		}

}
