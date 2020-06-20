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
/**
 * This class represents a controller for the gui Supplier
 * @author Mahmoud Odeh
 *
 */
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

  
    /**
     * This methode to ligth if their is a new notification
     * @param event
     */
    @FXML
    void NotificationBarAction(ActionEvent event) {
    	Alert(false);
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
    * This methode turn the light off when we return false and on when true
    * @param a this is aboolean parameter
    */
    void Alert(boolean a) {
 			notificationAlert.setVisible(a);
 		}
    /**
    * This methode show if their is a new orders
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
    		supplierOrder = new SupplierOrderController();
    	runLater(() -> {
    		supplierOrder.start(splitpane, user, "User");
});}
    }
    /**
     * This methode make the button order confirmation work and get us to the gui Order Confirmation
     * @param event
     */
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
    /**
    * This methode make the button home work and get us to the gui home
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
	     * This methode make the user log out from the program
	     * @param event
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
	     * This methode make the button profile setting work and get us to the gui profile setting
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
	     * This methode make the button about work and get us to the gui about
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
	    * This methode get notification from the chat client if there is anew order
	    * @param orderArray this parameter from the type order confirmation arraylist contains ordernumber,type,quantity,status,stationname,address,date,managerid
	    */
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
		
		/**
		 * This methode load the information into the gui
		 * @param userthis paramater from the type user that contains id,firtstname,lastname,email,username,password,rank,clienttype,status,image
		 */
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
		/**
		* This methode run all the buttons in the gui
		* @param f this paramater get a function to run the button
		*/
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
		/**
		* This methode  connect between the controller and the chatclient
		*/
	
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


