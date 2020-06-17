package gui;

import java.net.URL;
import java.util.ResourceBundle;

import Entity.OrderConfirmation;
import Entity.Sales;
import Entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnalayticSystemController implements Initializable{

	
	
	
	
	@FXML
    private AnchorPane analyticsystemtable;

    @FXML
    private TableView<Sales> analyticsystemTable;

    @FXML
    private TableColumn<Sales,String> clmClientID;

    @FXML
    private TableColumn<Sales,String> clmRating;

    @FXML
    private Button btnshowrates;
	
	
	
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	 ObservableList<OrderConfirmation> List =FXCollections.observableArrayList(); 
	  final Button addButton  = new Button("Confirm");

	private AnchorPane lowerAnchorPane;
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/MarketingManagerAnalyticSystem.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}
}
	
	
	
	
	
	
    @FXML
    void shorrates(ActionEvent event) {
System.out.println("aaa");
    }
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
