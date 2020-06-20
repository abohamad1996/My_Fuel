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
 * this class represents about Client Controller which display all the steps that client can do. 
 * 
 *
 */

public class ClientController implements Initializable{
    @FXML
    private Label DiroctoryBar;

    @FXML
    private SplitPane splitpane;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnRefuling;

    @FXML
    private Button btnOrderHeating;

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
     *   
     * @param event of button 
     * this method open event when clicking on the button Home 
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
     *  
     * @param  event of button
       this method open event when clicking on the button Home Heating
     */


    @FXML
    void HomeHeatingButton(ActionEvent event) {
    	DiroctoryBar.setText("My Fuel->Hoome Heating");
    	homeHeating =new HomeHeatingController();
    	runLater(() -> {
    		homeHeating.start(splitpane, user, "User");
});
    }
    /**
     * 
     * @param  event of button
    this method open event when clicking on the button log out
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
     * 
     * @param  event of button 
     * this method open event when clicking on the button  Profile Setting
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
     * 
     * @param event of button
     * this method open event when clicking on the button Purchase Plan
     */

    @FXML
    void PurchasePlanButton(ActionEvent event) {
    	DiroctoryBar.setText("My Fuel->Purchase Plan");
    	pController=new PurchasePlanController();
    	runLater(() -> {
    		pController.start(splitpane, user, "User");
});
    }
    /**
     * this method open event when clicking on the button Refuling
     * @param  event of button
     */

    @FXML
    void RefulingButton(ActionEvent event) {
    	DiroctoryBar.setText("My Fuel->Refueling");
    	refueling=new RefuelingController();
    	runLater(() -> {
    		refueling.start(splitpane, user, "User");
});
    }
    /**
     * this method open event when clicking on the button About
     * @param  event of button
     */

    @FXML
    void btnAbout(ActionEvent event) {
    	DiroctoryBar.setText("My Fuel->About");
    	About = new AboutController();
    	runLater(() -> {
    		About.start(splitpane, user, "User");
});
    }
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	private static User user;
	public static Stage primaryStage;
	public static ProfileSettingsController ProfileSetting;
	public static RefuelingController refueling;
	public static AboutController About;
	public static HomePage HomePage;
	public static PurchasePlanController pController;
	public static	HomeHeatingController  homeHeating;
	/**
	* This method load the information into the gui
	*
	
	* @param user this paramater from the type user that contains id,firtstname,lastname,email,username,password,rank,clienttype,status,image
	*/

	public void start(User user) {
		this.user = user;
		primaryStage = LoginController.primaryStage;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					Parent root;
					root = FXMLLoader.load(getClass().getResource("/gui/ClientGUI.fxml"));
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
	 * @param f type for function
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
	 * this method send notification  to chat client
	 */

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnRank.setText(user.getRank());
	    Rank=new MenuItem(user.getRank());
	       UserMenu.setText(user.getFirstname());
		
	}

}
