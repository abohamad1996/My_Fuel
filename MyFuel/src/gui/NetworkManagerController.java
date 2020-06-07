package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.cj.LicenseConfiguration;
import com.sun.scenario.effect.impl.prism.PrImage;

import javafx.fxml.Initializable;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class NetworkManagerController implements Initializable {
	
	    @FXML
	    private SplitPane splitpane;

	    @FXML
	    private Button btnHome;

	    @FXML
	    private Button btnProfile;

	    @FXML
	    private Button btnAbout;

	    @FXML
	    private Button btnApproveRates;

	    @FXML
	    private Button btnReciveReports;

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
	    void ApproveRatesbutton(ActionEvent event) {
	    	approveRates = new NetworkManagerApproveRatesController();
	    	runLater(() -> {
	    		approveRates.start(splitpane, user, "User");
	});
	    }

	    @FXML
	    void HomeButton(ActionEvent event) {
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
	    }

	    @FXML
	    void ProfileSettingButton(ActionEvent event) {
	    	ProfileSetting = new ProfileSettingsController();
	    	runLater(() -> {
	    		ProfileSetting.start(splitpane, user, "User");
	});
	    }

	    @FXML
	    void ReciveReportsButton(ActionEvent event) {
	    	reciveReports = new NetworkManagerReciveReportsController();
	    	runLater(() -> {
	    		reciveReports.start(splitpane, user, "User");
	});
	    }

	    @FXML
	    void btnAbout(ActionEvent event) {
	     	About = new AboutController();
		    	runLater(() -> {
		    		About.start(splitpane, user, "User");
		});
	    }


	public static ClientRegisterController register;
	public static NetworkManagerController s;
	private static User user;
	public static Stage primaryStage;
	public static ProfileSettingsController ProfileSetting;
	public static AboutController About;
	public static HomePage HomePage;
public static  NetworkManagerReciveReportsController reciveReports;
public static NetworkManagerApproveRatesController approveRates;
	public void start(User user) {
		this.user = user;
		s = this;
		primaryStage = LoginController.primaryStage;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					Parent root;
					root = FXMLLoader.load(getClass().getResource("/gui/NetworkManager.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/gui/css2.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.setResizable(false);
					primaryStage.setTitle("Home");
					primaryStage.show();
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
		btnRank.setText(user.getRank());
	    Rank=new MenuItem(user.getRank());
	       UserMenu.setText(user.getFirstname());
	}
}
