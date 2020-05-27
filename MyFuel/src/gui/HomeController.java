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
public class HomeController implements Initializable{
	
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
	    void HomeButton(ActionEvent event) {

	    }

	    @FXML
	    void HomeHeatingButton(ActionEvent event) {

	    }

	    @FXML
	    void ProfileSettingButton(ActionEvent event) {
System.out.println("Profile");
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

	    @FXML
	    void btnAbout(ActionEvent event) {

	    }
	  
	    
	public static HomeController s;
	private static User user;
	public static Stage primaryStage;
	public static ProfileSettingsController ProfileSetting;
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MenuItem rank = new MenuItem(user.getRank());
	    rank.setStyle("-fx-background-color: #01509f");
		MenuItem logout = new MenuItem("Logout");
	       UserMenu.setText(user.getFirstname());
	        logout.setStyle("-fx-background-color: #01509f");
	        UserMenu.getItems().add(rank);
	       UserMenu.getItems().add(logout);
	}	
	
}