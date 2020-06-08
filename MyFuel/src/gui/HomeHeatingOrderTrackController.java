package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.Car;
import Entity.HomeHeatingOrder;
import Entity.User;
import client.ClientConsole;
import client.Func;
import common.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeHeatingOrderTrackController implements Initializable{

    public static HomeHeatingOrderTrackController acainstance;

	
	  @FXML
	    private TextField txtQuantity;

	    @FXML
	    private ComboBox<String> comboChooseOrder;

	    @FXML
	    private TextField txtStatus;

	    @FXML
	    private TextField txtPrice;

	    @FXML
	    private TextField txtUrgent;

	    @FXML
	    private TextField txtSypplyDate;
	
	
	
	
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
	ArrayList<HomeHeatingOrder> HomeHeating=new ArrayList<HomeHeatingOrder>();
    ObservableList<String> List =FXCollections.observableArrayList(); 
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/HomeHeatingTrackOrder.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	
	
	
	public void HomeHeatingOrderAcceptor(ArrayList<String> bb) {
		List.addAll(bb);		
	}
	
	
	

	public void HomeHeatingOrderAccept(ArrayList<HomeHeatingOrder> homeHeatingOrders) {
		HomeHeating.addAll(homeHeatingOrders);
		
	}
	
    @FXML
    void ShowDetails(ActionEvent event) {
    	Integer index;
    	String OrderID=comboChooseOrder.getValue();
    	int i=Integer.parseInt(OrderID); 
    	
    
    	System.out.println("Quantity="+HomeHeating.get(1).getQuantity());
    }
	
	
	private int getOrderIDPos(int orderID) {
	    	for(int i = 0; i < this.HomeHeating.size(); ++i) {
	            if(this.HomeHeating.get(i).getOrderID()==orderID) 
	            	return i;
	    	}
	        return -1;
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		acainstance = this;	
		details.accept(new Message(24, null));
		details.accept(new Message(25, null));
		comboChooseOrder.setItems(List);
	}

}
