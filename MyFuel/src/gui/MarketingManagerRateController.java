package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Entity.Rates;
import Entity.User;
import client.ClientConsole;
import client.Func;
import common.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MarketingManagerRateController implements Initializable {
	public static MarketingManagerRateController acainstance;
  
	
	@FXML
	public Label status;
    @FXML
    private ComboBox<String> comboFuelType;

    @FXML
    private TextField txtRate;
    @FXML
    private Button btnSend;
	@FXML
	private static SplitPane splitpane;
    public ClientConsole chat= new ClientConsole("localhost", 5555);
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
    ObservableList<String> gastypeList =FXCollections.observableArrayList(); 	
		ArrayList<String> gastypeValues=new ArrayList<String>();
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/MarketingManagerRate.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}

    @FXML
    void SendToApprove(ActionEvent event) {
    	Rates rates=new Rates(comboFuelType.getValue(), txtRate.getText());
    	MarketingManagerRateController.acainstance.chat.accept(new Message(16, rates));
    }
	@SuppressWarnings("unused")
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
		acainstance=this;
		gastypeValues.add("Gasoline 95");
		gastypeValues.add("Diesel fuel");
		gastypeValues.add("Scooters fuel");
		gastypeValues.add("Home Heating fuel");
		gastypeList.addAll(gastypeValues);
		comboFuelType.setItems(gastypeList);
	}


}
