package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import DBconnection.DBconnector;
import Entity.User;
import client.Func;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * 
 * This class represents a controller for the gui HomeHeating
 *
 */

public class MarketingManagerController implements Initializable{
	  @FXML
	    private SplitPane splitpane;

	    @FXML
	    private Button btnSystem;

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
	    private Button btnRecievrReport;
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
	public static MarketingManagerRateController rates;
	public static MarketingManagerSaleController sales;
	public static AnalayticSystemController system;
	public static MarketingManagerNewReports report;
	public static MarketingManagerRecieveReports reportFile;
	/**
	 * This method run all the buttons in the gui MarketingManager
	 */

	public void start(User user) {
		this.user = user;
		primaryStage = LoginController.primaryStage;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					Parent root;
					root = FXMLLoader.load(getClass().getResource("/gui/MarketingManager.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/gui/css2.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.setResizable(false);
					primaryStage.setTitle("Marketing Manager");
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
				}
				 catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * this method for Home button
	 * @param event of button

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
	 * this method for RecieveReports button
	 * @param event of button

	 */

    @FXML
    void RecieveReports(ActionEvent event) {
      	DiroctoryBar.setText("My Fuel->Reports Recieved");
    	reportFile = new  MarketingManagerRecieveReports();
    	runLater(() -> {
    		reportFile.start(splitpane, user, "User");
});
    }
    /**
	 * this method for Logout button
	 * @param event of button

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
   	 * this method for Rates button
   	 * @param event of button

   	 */


    @FXML
    void Rates(ActionEvent event) {
       	DiroctoryBar.setText("My Fuel->Rates");
       	rates= new MarketingManagerRateController();
    	runLater(() -> {
    		rates.start(splitpane, user, "User");
});
    }
    /**
   	 * this method for Sales button
   	 * @param event of button

   	 */

    @FXML
    void Sales(ActionEvent event) {
    	DiroctoryBar.setText("My Fuel->Sales");
    	sales= new MarketingManagerSaleController();
    	runLater(() -> {
    		sales.start(splitpane, user, "User");
});
    
    	
    
    }
    /**
   	 * this method for NewReports button
   	 * @param event of button

   	 */

    @FXML
    void NewReports(ActionEvent event) {
    	DiroctoryBar.setText("My Fuel->Reports");
    	report= new MarketingManagerNewReports();
    	runLater(() -> {
    		report.start(splitpane, user, "User");
    	});
        
    }
    /**
   	 * this method for ProfileSetting button
   	 * @param event of button

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
   	 * this method for Aboutx button
   	 * @param event of button

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
	 * `this method for Diroctory bar that show to the user  were he is
	 * @param  event of button
	 */

	
	   @FXML
	    void System(ActionEvent event) {
		   DiroctoryBar.setText("My Fuel->Analaytic System");
		   system = new AnalayticSystemController();
	    	runLater(() -> {
	    		system.start(splitpane, user, "User");
	});	    }
	   /** 
		 * this method send notification  to chat client
		 */

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnRank.setText(user.getRank());
	    Rank=new MenuItem(user.getRank());
	       UserMenu.setText(user.getFirstname());
	   
		
	}

}
