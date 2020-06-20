package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DBconnection.DBconnector;
import Entity.User;
import client.Func;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
/**
 * This class represents a controller for the gui RepresentativeTransport
 * @author mahmoud odeh 
 *
 */
public class RepresentativeTransportController implements Initializable{

	  @FXML
	    private SplitPane splitpane;

	    @FXML
	    private Button btnHome;

	    @FXML
	    private Button btnProfile;

	    @FXML
	    private Button btnAbout;

	    @FXML
	    private Button btnMaxPrice;

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
	     * This methode make the button SetPriceus to the gui SetPrice
	     * @param event
	     */
	    @FXML
	    void SetPrice(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Set Maximum Price");

	    	setMaxPrice = new RepresentativeTransportSetMaxPrice();
	    	runLater(() -> {
	    		setMaxPrice.start(splitpane, user, "User");
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
	    
	    
	    
	    
	    
	    
	public static RepresentativeTransportController s;
	private static User user;
	public static Stage primaryStage;
	public static ProfileSettingsController ProfileSetting;
	public static AboutController About;
	public static HomePage HomePage;
	RepresentativeTransportSetMaxPrice setMaxPrice;
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
					root = FXMLLoader.load(getClass().getResource("/gui/RepresentativeTransport.fxml"));
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
	 * This methode get notification from the chatclient
	 */
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnRank.setText(user.getRank());
	    Rank=new MenuItem(user.getRank());
	       UserMenu.setText(user.getFirstname());		
	}

}
