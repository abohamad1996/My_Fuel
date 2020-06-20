package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.cj.LicenseConfiguration;
import com.sun.scenario.effect.impl.prism.PrImage;
import com.sun.xml.internal.ws.api.message.saaj.SAAJFactory;

import DBconnection.DBconnector;
import javafx.fxml.Initializable;
import Entity.OrderConfirmation;
import Entity.Rates;
import Entity.StationsInventory;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class represents a controller for the gui StaionManager
 * @author  mahmoud odeh
 *
 */
public class StaionManagerController implements Initializable {
    ObservableList<OrderConfirmation> List =FXCollections.observableArrayList(); 
    ObservableList<OrderConfirmation> List2 =FXCollections.observableArrayList(); 

	public static StaionManagerController acainstance;
    @FXML
    private MenuItem StationName;

    @FXML
    private Button btnStationName;
	   @FXML
	    private SplitPane splitpane;
	    @FXML
	    private MenuButton notificationMenu;

	    @FXML
	    private MenuItem notification;

	    @FXML
	    private Button btnNotification;

	    @FXML
	    private ImageView notificationAlert;
	    @FXML
	    private Button btnHome;

	    @FXML
	    private Button btnProfile;

	    @FXML
	    private Button btnAbout;

	    @FXML
	    private Button btnInventory;

	    @FXML
	    private Button btnNewReports;

	    @FXML
	    private Button btnOrderConfirmation;
	    @FXML
	    private Button btnOrderCompleted;
	    @FXML
	    private MenuItem notificationOrder;
	    @FXML
	    private MenuButton UserMenu;
	    @FXML
	    private Button btnHomeHeating;
	    @FXML
	    private MenuItem Rank;

	    @FXML
	    private Button btnRank;

	    @FXML
	    private MenuItem Logout;

	    @FXML
	    private Button btnLogout;
	    @FXML
	    private Label DiroctoryBar;
		public StationsInventory stationsInventory;
		/**
		 * This methode get notification from the chatclient
		 * @param invthis paramet of type station inventory contaians StationID,StationName,StationAddress,GasolineQuantity,DieselQuantity,ScooterQuantity,HomeHeatingQuantity,GasolineThresholdLevel,DieselThresholdLevel,ScooterThresholdLevel,managerIDString;
		 */
		public void FuelAcceptor(ArrayList<StationsInventory> inv) {
			stationsInventory=null;
			/*Diesel=new Inventory(inv.get(0).getFuelType(), inv.get(0).getQuantity(), inv.get(0).getLevel());
			Gasoline=new Inventory(inv.get(1).getFuelType(), inv.get(1).getQuantity(), inv.get(1).getLevel());
			Scotter=new Inventory(inv.get(3).getFuelType(), inv.get(3).getQuantity(), inv.get(3).getLevel());
			HomeHeating=new Inventory(inv.get(2).getFuelType(), inv.get(2).getQuantity(), inv.get(2).getLevel());
			System.out.println(Gasoline.toString());
			System.out.println(Diesel.toString());
			System.out.println(Scotter.toString());
			System.out.println(HomeHeating.toString());*/
			for(StationsInventory temp:inv)
			{
				if(temp.getManagerIDString().equals(user.getId()))
				{
					stationsInventory=temp;
				}
			}
				System.out.println(stationsInventory);
			}
		/**
		 * /**
 * This methode make the button HomeHeating and get us to the gui Inventory
 * @param event
 */
	@FXML
	    void HomeHeating(ActionEvent event) {
	    	  DiroctoryBar.setText("My Fuel->Home Heating Dates");
	    	homeHeatingDates = new StationManagerHomeHeatingDates();
	    	runLater(() -> {
	    		homeHeatingDates.start(splitpane, user, "User");
	});
	    }
	 /**
 * This methode make the button Inventorywork and get us to the gui Inventory
 * @param event
 */
	    @FXML
	    void HomeButton(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Home");

	    	HomePage = new HomePage();
	    	runLater(() -> {
	    		HomePage.start(splitpane, user, "User");
	});
	    }
	    /**
	     * This methode make the button Inventorywork and get us to the gui Inventory
	     * @param event
	     */
	    @FXML
	    void Inventory(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Inventory Managment");

	    	 inventory=new StationManagerInventoryController();
	    	 inventory.start(splitpane, user, "User");
	    }
	    /**
	    * his methode make the user log out from the program
	    */
	    @FXML
	    void Logout(ActionEvent event) {
	    	System.out.println("Logout");
			LogoutController logout=new LogoutController();
			
			logout.start(primaryStage, user);
			try {
				user=DBconnector.StatusLogoutUpdate(DBconnector.getConnection(), user.getUsername(), user.getPassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    /**
	     * This methode make the button NewReportswork and get us to the gui NewReports
	     * @param event
	     */
	    @FXML
	    void NewReports(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Reports");

	    	reports = new StationManagerReportController();
	    	runLater(() -> {
	    		reports.start(splitpane, user, "User");
	});
	    }
	    /**
	    * This methode make the button OrderConfirmationand get us to the gui OrderConfirmation
	    * @param event
	    */
	    @FXML
	    void OrderConfirmation(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Order Confirmation");

	    	confirmation=new StationManagerOrderConfirmationController();
	    	confirmation.start(splitpane, user, "User");
	    }
	    /**
	    * This methode make the button ProfileSettingButtonget us to the gui ProfileSettingButton
	    * @param event
	    */
	    @FXML
	    void ProfileSettingButton(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Profile Settings");

	    	ProfileSetting = new ProfileSettingsController();
	    	runLater(() -> {
	    		ProfileSetting.start(splitpane, user, "User");
	});
	    }
	    /**
	    * This methode make the button btnAboutus to the gui btnAbout
	    * @param event
	    */
	    @FXML
	    void btnAbout(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->About");

	     	About = new AboutController();
	    	runLater(() -> {
	    		About.start(splitpane, user, "User");
	});
	    }
	    /**
	    * This methode send anotification to the station manager to approve rate
	    * @param event
	    */
	    @FXML
	    void OrderApprove(ActionEvent event) {
	    	if(btnNotification.getText().equals("There is no notifications"))
	    	{
	    		Alert alert = new Alert(AlertType.ERROR);
				alert.setAlertType(AlertType.ERROR); 
				alert.setContentText("There is no Updates!!!");
				alert.show(); 
	    	}
	    	else {
	    		confirmation = new StationManagerOrderConfirmationController();
	    	runLater(() -> {
	    		confirmation.start(splitpane, user, "User");
	});}
	    }
	    /**
	    * This methode turn of the red light when we click 
	    * @param event
	    */
	    @FXML
	    void NotificationBarClick(MouseEvent event) {
	    	Alert(false);
	    }
	    /**
	     * This methode to ligth if their is a new notification
	     * @param event
	     */
	    @FXML
	    void NotificationBarAction(ActionEvent event) {
	    	Alert(false);
			System.out.println("aaaa");
	    }
	    /**
	    * This methode turn the light off when we return false and on when true
	    * @param a this is aboolean parameter
	    */
	    void Alert(boolean a) {
			notificationAlert.setVisible(a);
		}
	    /**
	    * This methode get notification from the chat client if there is anew order
	    * @param orderArray this parameter from the type order confirmation arraylist contains ordernumber,type,quantity,status,stationname,address,date,managerid
	    */
	    public void OrderAcceptor(ArrayList<OrderConfirmation> orderArray) {
			List.addAll(orderArray);
			System.out.println(List);
			System.out.println(List.size());
			if(List.size()!=0)
			{
				btnNotification.setText("Threshold level low!!");
				Alert(true);
				
			}
			else {
				System.out.println("null");
			}
			
			}
	    /**
	    * This methode get notification from the chat client 
	    * @param orderArray this parameter from the order confirmation contains ordernumber,type,quantity,status,stationname,address,date,managerid
	    */
	    public void OrderDoneAcceptor(ArrayList<OrderConfirmation> orderArray) {
			List2.addAll(orderArray);
			System.out.println(List2);
			System.out.println("size:"+List2.size());
			if(List2.size()!=0)
			{
				btnOrderCompleted.setText("There is order Done!");
				Alert(true);
			}
			else {
				System.out.println("null");
			}
			
			}
	    /**
	    * This methode make the button Stationto work and go to the gui Station
	    * @param event
	    */
	    @FXML
	    void Station(ActionEvent event) {
	    	  DiroctoryBar.setText("My Fuel->Station Details");

	    	  stationDetailsController = new StationDetailsController();
		    	runLater(() -> {
		    		stationDetailsController.start(splitpane, user, "User");
		});
	    }
	    /**
	    * This methode make the button OrderCompleted work and go to the gui Station
	    * @param event
	    */
	    @FXML
	    void OrderCompleted(ActionEvent event) {
	    	 DiroctoryBar.setText("My Fuel->Order Completed");
	    	 ordercompleted = new StationManagerOrderCompletedController();
		    	runLater(() -> {
		    		ordercompleted.start(splitpane, user, "User");
		});
	    }
	    /**
	     * This methode get notification from the chatclient
	     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance=this;
		 ClientConsole details= new ClientConsole("localhost", 5555);
		 details.accept(new Message(27, null));
		 details.accept(new Message(33, null));
		 details.accept(new Message(54, null));
		 btnStationName.setText(stationsInventory.getStationName());
		btnRank.setText(user.getRank());
	    Rank=new MenuItem(user.getRank());
	       UserMenu.setText(user.getFirstname());
	}
	public static StationManagerHomeHeatingDates homeHeatingDates;
	public static StationManagerOrderCompletedController ordercompleted;
	public static StationDetailsController stationDetailsController;
	public static ClientRegisterController register;
	public static StaionManagerController s;
	private static User user;
	public static Stage primaryStage;
	public static ProfileSettingsController ProfileSetting;
	public static AboutController About;
	public static HomePage HomePage;
	public static StationManagerInventoryController inventory;
	public static StationManagerReportController reports;
	public static StationManagerOrderConfirmationController confirmation;
	/**
	* 
	* @param splitpane this parameter from the type splitpane
	* @param user this paramater from the type user that contains id,firtstname,lastname,email,username,password,rank,clienttype,status,image
	* @param userJob this parameter from the type string
	*/
	public void start(User user) {
		this.user = user;
		s = this;
		primaryStage = LoginController.primaryStage;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					Parent root;
					root = FXMLLoader.load(getClass().getResource("/gui/StaionManager.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/gui/css2.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.setResizable(false);
					primaryStage.setTitle("Home");
					primaryStage.show();
					primaryStage.setOnCloseRequest(event -> {
						try {
						DBconnector.StatusLogoutUpdate(DBconnector.getConnection(), user.getUsername(), user.getPassword());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.exit(0);
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
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
}
