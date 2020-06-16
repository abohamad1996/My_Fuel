package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DBconnection.DBconnector;
import Entity.OrderConfirmation;
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

public class SupplierController implements Initializable{

	public static SupplierController acainstance;

    ObservableList<OrderConfirmation> List =FXCollections.observableArrayList(); 

    @FXML
    private ImageView notificationAlert;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnAbout;

    @FXML
    private Button btnOrderConfirmation;

    @FXML
    private Label DiroctoryBar;

    @FXML
    private MenuButton UserMenu;

    @FXML
    private MenuItem Rank;

    @FXML
    private Button btnRank;

    @FXML
    private MenuItem StationName;


    @FXML
    private MenuItem Logout;

    @FXML
    private Button btnLogout;

    @FXML
    private MenuButton notificationMenu;

    @FXML
    private MenuItem notification;

    @FXML
    private Button btnNotification;

  

    @FXML
    void NotificationBarAction(ActionEvent event) {
    	Alert(false);
    }

    @FXML
    void NotificationBarClick(MouseEvent event) {
    	Alert(false);

    }
    void Alert(boolean a) {
 			notificationAlert.setVisible(a);
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
    		supplierOrder = new SupplierOrderController();
    	runLater(() -> {
    		supplierOrder.start(splitpane, user, "User");
});}
    }

    @FXML
    void OrderConfirmation(ActionEvent event) {
    	  DiroctoryBar.setText("My Fuel->Order Confirmation");

    	  supplierOrder = new SupplierOrderController();
	    	runLater(() -> {
	    		supplierOrder.start(splitpane, user, "User");
	});
    }



    @FXML
    void Station(ActionEvent event) {

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
	
	    public void OrderAcceptor(ArrayList<OrderConfirmation> orderArray) {
			List.addAll(orderArray);
			System.out.println(List);
			System.out.println(List.size());
			if(List.size()!=0)
			{
				btnNotification.setText("There is new order request");
				Alert(true);
				
			}
			else {
				System.out.println("null");
			}
			}
	    private static User user;
	    @FXML
	    private SplitPane splitpane;
		public static Stage primaryStage;
		public static ProfileSettingsController ProfileSetting;
		public static AboutController About;
		public static HomePage HomePage;
		public static  NetworkManagerReciveReportsController reciveReports;
		public static NetworkManagerApproveRatesController approveRates;
		public static SupplierOrderController supplierOrder;
		

		public void start(User user) {
			this.user = user;
			primaryStage = LoginController.primaryStage;
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						Parent root;
						root = FXMLLoader.load(getClass().getResource("/gui/SupplierGui.fxml"));
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance=this;
		 ClientConsole details= new ClientConsole("localhost", 5555);
		 details.accept(new Message(48, null));
		btnRank.setText(user.getRank());
	    Rank=new MenuItem(user.getRank());
	       UserMenu.setText(user.getFirstname());
	}
	}


