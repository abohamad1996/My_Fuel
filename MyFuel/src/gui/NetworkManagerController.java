package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.cj.LicenseConfiguration;
import com.sun.scenario.effect.impl.prism.PrImage;

import DBconnection.DBconnector;
import javafx.fxml.Initializable;
import Entity.Files;
import Entity.Rates;
import Entity.User;
import client.ChatClient;
import client.ClientConsole;
import client.Func;
import common.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class NetworkManagerController implements Initializable {

	public static NetworkManagerController acainstance;
		@FXML
		private MenuButton notificationMenu;
	    @FXML
	    private MenuItem Files;

	    @FXML
	    private Button btnFiles;

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
	    public ImageView notificationAlert;
	    @FXML
	    private Button btnRank;

	    @FXML
	    private MenuItem Logout;

	    @FXML
	    private Button btnLogout;
	    @FXML
	    private Label DiroctoryBar;
	    @FXML
	    private MenuItem notification;
	    @FXML
	    private Button btnNotification;
		public ArrayList<Files> filesarr;

	    ObservableList<Rates> List =FXCollections.observableArrayList(); 
	    ObservableList<Files> List2 =FXCollections.observableArrayList(); 

	private static User user;
    @FXML
    private SplitPane splitpane;
	public static Stage primaryStage;
	public static ProfileSettingsController ProfileSetting;
	public static AboutController About;
	public static HomePage HomePage;
	public static  NetworkManagerReciveReportsController reciveReports;
	public static NetworkManagerApproveRatesController approveRates;
	public static NetworkManagerReciveReportsController reports;
	public void start(User user) {
		this.user = user;
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
	  @FXML
	    void ApproveRatesbutton(ActionEvent event) {
		  DiroctoryBar.setText("My Fuel->Approve Rates");
	    	approveRates = new NetworkManagerApproveRatesController();
	    	runLater(() -> {
	    		approveRates.start(splitpane, user, "User");
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
	    void ProfileSettingButton(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Profile Settings");

	    	ProfileSetting = new ProfileSettingsController();
	    	runLater(() -> {
	    		ProfileSetting.start(splitpane, user, "User");
	});
	    }

	    @FXML
	    void ReciveReportsButton(ActionEvent event) {
			  DiroctoryBar.setText("My Fuel->Reports");
	    	reciveReports = new NetworkManagerReciveReportsController();
	    	runLater(() -> {
	    		reciveReports.start(splitpane, user, "User");
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
	    @SuppressWarnings("unlikely-arg-type")
		@FXML
	    void RatesApprove(ActionEvent event) {
	    	if(btnNotification.getText().equals("There is no notifications"))
	    	{
	    		Alert alert = new Alert(AlertType.ERROR);
				alert.setAlertType(AlertType.ERROR); 
				alert.setContentText("There is no Updates!!!");
				alert.show(); 
	    	}
	    	else {
	      	approveRates = new NetworkManagerApproveRatesController();
	    	runLater(() -> {
	    		approveRates.start(splitpane, user, "User");
	});}
	    }
	    @FXML
	    void NotificationBarAction(ActionEvent event) {
	    			Alert(false);
	    			System.out.println("aaaa");
	    }
	    @FXML
	    void NotificationBarClick(MouseEvent event) {
	    	Alert(false);
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
	void Alert(boolean a) {
		notificationAlert.setVisible(a);
	}
	public void RatesAcceptor(ArrayList<Rates> ratesArray) {
		List.addAll(ratesArray);
		System.out.println(List);
		if(List.size()!=0)
		{
			btnNotification.setText("There is new rates request");
			Alert(true);
		}
		else {
			System.out.println("null");
		}
		}
	
	public void FilesAcceptor(ArrayList<Files> files) {
		filesarr = (ArrayList<Files>)files.clone();
		System.out.println(filesarr);
		List2.addAll(files);
		System.out.println(List2);
		if(List2.size()!=0)
		{
			btnFiles.setText("There is new report files");
			Alert(true);
			
		}
		else {
			System.out.println("null");
		}
		}
    @FXML
    void FilesReiceved(ActionEvent event) {
    	if(btnFiles.getText().equals("There is no recieved files"))
    	{
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setAlertType(AlertType.ERROR); 
			alert.setContentText("There is no Updates!!!");
			alert.show(); 
    	}
    	else {
  	reports = new NetworkManagerReciveReportsController();
  	runLater(() -> {
  		reports.start(splitpane, user, "User");
  		});   
  	}
    }
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance=this;
		 ClientConsole details= new ClientConsole("localhost", 5555);
		 details.accept(new Message(21, null));
		 details.accept(new Message(64, null));
		btnRank.setText(user.getRank());
	    Rank=new MenuItem(user.getRank());
	       UserMenu.setText(user.getFirstname());
	}

}
