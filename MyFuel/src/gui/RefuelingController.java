package gui;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.omg.CORBA.PUBLIC_MEMBER;

import Entity.Car;
import Entity.Refueling;
import Entity.User;
import client.ClientConsole;
import client.Func;
import common.Message;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import jdk.internal.dynalink.beans.StaticClass;

public class RefuelingController implements Initializable{
	
	public int j;
    public static RefuelingController acainstance;
    @FXML
    private TextField txtQuantity;

	 @FXML
	    private Button btnStart;

	    @FXML
	    private Label labelCarNumber;
	    @FXML
	    private Label labelFuelTyple;

	    @FXML
	    private Label labelGasStation;
	    @FXML
	    private Button brnDetails;
	    @FXML
	    private Label labelPump;
	    @FXML
	    private ProgressBar RefuelingPrgeess;
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static  ClientRefuelingDetailsController clientRefuelingDetails;
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public ClientConsole details= new ClientConsole("localhost", 5555);
	ArrayList<User> userdetails= new ArrayList<User>();
	User detailsUser;
	ArrayList<Car> car2=new ArrayList<Car>();
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/ClientRefuling.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	
    @FXML
    void SeeDetails(ActionEvent event) {
    	clientRefuelingDetails = new ClientRefuelingDetailsController();
    	runLater(() -> {
    		clientRefuelingDetails.start(splitpane, user, "User");
});
    }
	        
	public void CarAcceptor(ArrayList<Car> car) {
		car2.addAll(car);
		//CarList.addAll(car);
	}
    @FXML
    void StartRefueilng(ActionEvent event) {
    	Calendar rightNow = Calendar.getInstance();
        int y = rightNow.get(Calendar.YEAR);
        int m = rightNow.get(Calendar.MONTH) + 1;
        int d = rightNow.get(Calendar.DAY_OF_MONTH);
        String date=y+"-"+m+"-"+d;
    	Refueling refueling;
		refueling=new Refueling(labelCarNumber.getText(), labelGasStation.getText(), labelFuelTyple.getText(), null, txtQuantity.getText(), null, date, labelPump.getText());
		Task <Void> t = new Task <Void> () {
    		    protected Void call() throws Exception {
    		     for (int i = 0; i < 10; i++) {
    		      updateProgress(i, 9);
    		      Thread.sleep(500);
    		      if(i==9)
    		      {
    		    	  brnDetails.setVisible(true);
    		    	  System.out.println(refueling);
    		      }
    		     }
    		     return null;
    		    }
    		   };
    		   RefuelingPrgeess.progressProperty().bind(t.progressProperty());
    		   //new Thread(t).run(); // wrong
    		   new Thread(t).start(); // right
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
		acainstance = this;		
		details.accept(new Message(15, null));
		Random rn = new Random();
		int maximum =(car2.size()-1);
		System.out.println(maximum);
		int minimum=0;
		int range = maximum - minimum + 1;
		int randomNum =  rn.nextInt(range) + minimum;
		String s=String.valueOf(randomNum+1);
		labelCarNumber.setText(car2.get(randomNum).getCarNumber());
		labelCarNumber.setVisible(true);
		labelFuelTyple.setText(car2.get(randomNum).getGastype());
		labelFuelTyple.setVisible(true);
		labelGasStation.setText(car2.get(randomNum).getGasStation1());
		labelGasStation.setVisible(true);
		labelPump.setText(s);
		labelPump.setVisible(true);
		//labelQuantity.setVisible(true);
		}
}
