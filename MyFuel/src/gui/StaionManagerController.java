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
	    
	    
	    @FXML
	    void HomeButton(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Home");

	    	HomePage = new HomePage();
	    	runLater(() -> {
	    		HomePage.start(splitpane, user, "User");
	});
	    }

	    @FXML
	    void Inventory(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Inventory Managment");

	    	 inventory=new StationManagerInventoryController();
	    	 inventory.start(splitpane, user, "User");
	    }

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

	    @FXML
	    void NewReports(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Reports");

	    	reports = new StationManagerReportController();
	    	runLater(() -> {
	    		reports.start(splitpane, user, "User");
	});
	    }
	    @FXML
	    void OrderConfirmation(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Order Confirmation");

	    	confirmation=new StationManagerOrderConfirmationController();
	    	confirmation.start(splitpane, user, "User");
	    }

	    @FXML
	    void ProfileSettingButton(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Profile Settings");

	    	ProfileSetting = new ProfileSettingsController();
	    	runLater(() -> {
	    		ProfileSetting.start(splitpane, user, "User");
	});
	    }

	    @FXML
	    void btnAbout(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->About");

	     	About = new AboutController();
	    	runLater(() -> {
	    		About.start(splitpane, user, "User");
	});
	    }
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
	    @FXML
	    void NotificationBarClick(MouseEvent event) {
	    	Alert(false);
	    }

	    @FXML
	    void NotificationBarAction(ActionEvent event) {
	    	Alert(false);
			System.out.println("aaaa");
	    }
	    void Alert(boolean a) {
			notificationAlert.setVisible(a);
		}
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
	    @FXML
	    void Station(ActionEvent event) {
	    	  DiroctoryBar.setText("My Fuel->Station Details");

	    	  stationDetailsController = new StationDetailsController();
		    	runLater(() -> {
		    		stationDetailsController.start(splitpane, user, "User");
		});
	    }
	    @FXML
	    void OrderCompleted(ActionEvent event) {
	    	 DiroctoryBar.setText("My Fuel->Order Completed");
	    	 ordercompleted = new StationManagerOrderCompletedController();
		    	runLater(() -> {
		    		ordercompleted.start(splitpane, user, "User");
		});
	    }
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
