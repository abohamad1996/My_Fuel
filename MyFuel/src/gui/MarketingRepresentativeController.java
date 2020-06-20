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
*This class represents Marketing Representative Controller
*
*/ 

public class MarketingRepresentativeController implements Initializable{
	@FXML
    private SplitPane splitpane;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnRegister;

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


    @FXML
    private Label DiroctoryBar;
    @FXML
    private Button btnCar;
    /**
    * This method open the gui of home page 
    * @param event event of button
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
    * This method make the user log out from the program
    * @param event event of button
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
     * This method bring us to profile setting gui
     * @param event event of button
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
     * This method bring us to Register Client gui
     * @param event event of button
     */
    @FXML
    void RegisterClient(ActionEvent event) {
    	DiroctoryBar.setText("My Fuel->Register Client");
    	register = new ClientRegisterController();
    	runLater(() -> {
    		register.start(splitpane, user, "User");
});
    	}
    /**
     * This method bring us to Add Car gui
     * @param event event of button
     */


    @FXML
    void AddCar(ActionEvent event) {
     	DiroctoryBar.setText("My Fuel->Register Car");
    	User user = null;
    	CarRegisterController car;
    	car = new CarRegisterController();
    	runLater(() -> {
    		car.start(splitpane, user, "User");
    	});
    }
    
    /**
     * This method bring us to about gui
    * @param event event of button
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
    * This methode return the primary stage
    * @return primary stage
    */

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public static ClientRegisterController register;
	public static MarketingRepresentativeController s;
	private static User user;
	public static Stage primaryStage;
	public static ProfileSettingsController ProfileSetting;
	public static AboutController About;
	public static HomePage HomePage;
	/**
	* This methode load the information into the gui
	*
	* @param user this paramater from the type user that contains id,firtstname,lastname,email,username,password,rank,clienttype,status,image
	*
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
					root = FXMLLoader.load(getClass().getResource("/gui/MarketingRepresentative.fxml"));
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
	* This method run all the buttons in the gui
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
	* This methode run all the buttons in the gui
	* 
	*/

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnRank.setText(user.getRank());
	    Rank=new MenuItem(user.getRank());
	       UserMenu.setText(user.getFirstname());
		
	}

}
