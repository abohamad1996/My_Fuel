package gui;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Button btnCar;
    
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
    void RegisterClient(ActionEvent event) {
    	register = new ClientRegisterController();
    	runLater(() -> {
    		register.start(splitpane, user, "User");
});
    	}
    
    @FXML
    void AddCar(ActionEvent event) {

    	User user = null;
    	CarRegisterController car;
    	car = new CarRegisterController();
    	runLater(() -> {
    		car.start(splitpane, user, "User");
    	});
    }
    @FXML
    void btnAbout(ActionEvent event) {
    	About = new AboutController();
    	runLater(() -> {
    		About.start(splitpane, user, "User");
});
    }
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnRank.setText(user.getRank());
	    Rank=new MenuItem(user.getRank());
	       UserMenu.setText(user.getFirstname());
		
	}

}
