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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
/**
*This class represents a controller for the gui USERHOME
* 
*
*
*/
public class UserHomeController implements Initializable{
	
	   @FXML
	    private SplitPane splitpane;

	    @FXML
	    private Button btnHome;

	    @FXML
	    private Button btnRefuling;

	    @FXML
	    private Button btnOrderHeating;

	    @FXML
	    private Button btnRegister;

	    @FXML
	    private Button btnPurchase;

	    @FXML
	    private Button btnProfile;

	    @FXML
	    private Button btnAbout;

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
	    /**
	    * This methode bring us back to the home page of the gui 
	    * @param event
	    */
	    @FXML
	    void HomeButton(ActionEvent event) {
	       	HomePage = new HomePage();
	    	runLater(() -> {
	    		HomePage.start(splitpane, user, "User");
	});
	    }
	    

	    @FXML
	    void HomeHeatingButton(ActionEvent event) {

	    }

	    /**
	     * This methode bring us to profile srtting gui
	     * @param event
	     */
	    @FXML
	    void ProfileSettingButton(ActionEvent event) {
	    	ProfileSetting = new ProfileSettingsController();
	    	runLater(() -> {
	    		ProfileSetting.start(splitpane, user, "User");
	});
	    }

	    @FXML
	    void PurchasePlanButton(ActionEvent event) {

	    }

	    @FXML
	    void RefulingButton(ActionEvent event) {

	    }

	    @FXML
	    void RegisterClientButton(ActionEvent event) {

	    }
	    /**
	     * This methode bring us to about gui
	    * @param event
	    */
	    @FXML
	    void btnAbout(ActionEvent event) {
	    	About = new AboutController();
	    	runLater(() -> {
	    		About.start(splitpane, user, "User");
	});
	    }
	  
	    
	public static UserHomeController s;
	private static User user;
	public static Stage primaryStage;
	public static ProfileSettingsController ProfileSetting;
	public static AboutController About;
	public static HomePage HomePage;
	/**
	 * This methode load the information into the gui
	 * @param user this paramater from the type user that contains id,firtstname,lastname,email,username,password,rank,clienttype,status,image
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
					root = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
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
    * This methode return the primary stage
    * @return primary stage
    */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	/**
	* This methode connect between the controller and the gui
	*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnRank.setText(user.getRank());
	    Rank=new MenuItem(user.getRank());
	       UserMenu.setText(user.getFirstname());
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
}