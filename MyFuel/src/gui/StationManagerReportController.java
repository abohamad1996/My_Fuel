package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DBconnection.DBconnector;
import Entity.Rates;
import Entity.Refueling;
import Entity.User;
import client.ClientConsole;
import common.Message;
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
	
	public static StationManagerReportController acainstance;

    @FXML
    private ComboBox<String> comboReportType;
    @FXML
    private ComboBox<String> comboQuartet;

    @FXML
    private ComboBox<String> comboYear;
 
    String currentYear,DateFrom,DateTo,From,To;
    @FXML
    private Button btnSend;
	ArrayList<String> ReportValues=new ArrayList<String>();
	ArrayList<String> Dates=new ArrayList<String>();
	public ArrayList<Refueling> refuelings;
    ObservableList<String> Report =FXCollections.observableArrayList(); 
	ArrayList<String> Quarter=new ArrayList<String>();
    ObservableList<String> QuarterList =FXCollections.observableArrayList(); 
	ArrayList<String> Year=new ArrayList<String>();
    ObservableList<String> YearList =FXCollections.observableArrayList(); 
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
	
	public void RefuelingAcceptor(ArrayList<Refueling> ref) {
		refuelings = (ArrayList<Refueling>)ref.clone();
		//System.out.println(refuelings);
		}
    @FXML
    void SendReport(ActionEvent event) {
    			
    			if(comboReportType.getValue().equals("Quarterly Revenue Report"))
        		{
        			System.out.println(refuelings.get(0).getQunatity());
        			String path = System.getProperty("user.dir")+"/config.xml";
        			File myObj = new File(path);
        		      try {
						if (myObj.createNewFile()) {
						    System.out.println("File created: " + myObj.getName());
						  } else {
						    System.out.println("File already exists.");
						  }
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

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

  
    @FXML
    void SelectReport(ActionEvent event) {
    	
    }
    @FXML
    void SelectQuartet(ActionEvent event) {
    	if(comboQuartet.getValue().equals("Quartet 1 (Between 1-1 To 30-3)"))
    			{
    			DateFrom="01-01";
    			DateTo="03-31";
    			}
    	else if(comboQuartet.getValue().equals("Quartet 2 (Between 1-4 To 31-6)"))
		{
    			DateFrom="04-01";
				DateTo="06-31";
		}
    	else if(comboQuartet.getValue().equals("Quartet 3 (Between 1-7 To 30-9)"))
		{
    			DateFrom="07-01";
    			DateTo="09-31";
		}
    	else if(comboQuartet.getValue().equals("Quartet 4 (Between 1-10 To 31-12)"))
		{
    			DateFrom="10-01";
    			DateTo="12-31";
		}
    	
    }

    @FXML
    void SelectYear(ActionEvent event) {
    	currentYear=comboYear.getValue()+"-";
    	From=currentYear.concat(DateFrom);
		To=currentYear.concat(DateTo);
		Dates.add(From);
		Dates.add(To);
		StationManagerReportController.acainstance.details.accept(new Message(57, Dates));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance=this;
		ReportType.add("Quarterly Revenue Report");
		ReportType.add("Purchases Report");
		ReportType.add("Quantity in stock Report");
		Report.addAll(ReportType);
		comboReportType.setItems(Report);
		Year.add("2019");
		Year.add("2020");
		YearList.addAll(Year);
		comboYear.setItems(YearList);
		Quarter.add("Quartet 1 (Between 1-1 To 30-3)");
		Quarter.add("Quartet 2 (Between 1-4 To 31-6)");
		Quarter.add("Quartet 3 (Between 1-7 To 30-9)");
		Quarter.add("Quartet 4 (Between 1-10 To 31-12)");
		QuarterList.addAll(Quarter);
		comboQuartet.setItems(QuarterList);
		}

}
