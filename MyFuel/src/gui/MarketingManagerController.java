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

public class MarketingManagerController implements Initializable{
	  @FXML
	    private SplitPane splitpane;

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
	public static RateController rates;
	public static SaleController sales;
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
    void Rates(ActionEvent event) {
       	DiroctoryBar.setText("My Fuel->Rates");
       	rates= new RateController();
    	runLater(() -> {
    		rates.start(splitpane, user, "User");
});
    }

    @FXML
    void Sales(ActionEvent event) {
    	DiroctoryBar.setText("My Fuel->Rates");
    	sales= new SaleController();
    	runLater(() -> {
    		sales.start(splitpane, user, "User");
});
    	
    	
    	
   
    }
    @FXML
    void NewReports(ActionEvent event) {
    	
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
		btnRank.setText(user.getRank());
	    Rank=new MenuItem(user.getRank());
	       UserMenu.setText(user.getFirstname());
		
	}

}
