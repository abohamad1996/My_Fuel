package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.Car;
import Entity.Rates;
import Entity.StationsInventory;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CarRegisterController implements Initializable{
	public static CarRegisterController acainstance;
    ObservableList<String> planList =FXCollections.observableArrayList(); 
    ObservableList<String> serviceList =FXCollections.observableArrayList(); 
    ObservableList<String> gasList =FXCollections.observableArrayList(); 
    ObservableList<String> gastypeList =FXCollections.observableArrayList(); 
    ObservableList<String> paylist =FXCollections.observableArrayList(); 
	ArrayList<String> gasValues=new ArrayList<String>();
	ArrayList<String> planValues=new ArrayList<String>();
	ArrayList<String> serviceValues=new ArrayList<String>();
	ArrayList<String> gastypeValues=new ArrayList<String>();
	ArrayList<String> pay=new ArrayList<String>();
    @FXML
    private ComboBox<String> comboID;
	@FXML
	    private Button btnNext;

	    @FXML
	    private TextField txtCarNumber;

	    @FXML
	    private ComboBox<String> comboPlan;

	    @FXML
	    private ComboBox<String> comboServices;
	    @FXML
	    private ComboBox<String> comboStation;

	    @FXML
	    private ComboBox<String> comboStation2;

	    @FXML
	    private ComboBox<String> comboStation3;
	    @FXML
	    private Label gasLabel1;

	    @FXML
	    private Label star;

	    @FXML
	    private Label gasLabel;

	    @FXML
	    private Label star2;
	    @FXML
	    private ComboBox<String> comboPrePaying;
	    @FXML
	    private Label gasLabel2;
	    @FXML
	    private ComboBox<String> comboGastype;
	@FXML
	private static SplitPane splitpane;
    @FXML
    public Label status;
    
    ObservableList<String> List =FXCollections.observableArrayList(); 
	public ClientConsole chat= new ClientConsole("localhost", 5555);
	private FXMLLoader loader;	
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
	public ArrayList<Rates> ratesUpdateArrayList;
	public void start(SplitPane splitpane, User user,String userJob) {
	this.splitpane=splitpane;
	primaryStage=LoginController.primaryStage;
	try{	
		loader = new FXMLLoader(getClass().getResource("/gui/CarRegister.fxml"));
		lowerAnchorPane = loader.load();
		splitpane.getItems().set(1, lowerAnchorPane);
	} catch(Exception e) {
		e.printStackTrace();
}	
}

	public void IDAcceptor(ArrayList<String> bb) {
		List.addAll(bb);		
	}
	
	public void RatesAcceptor(ArrayList<Rates> rates) {
		ratesUpdateArrayList = (ArrayList<Rates>)rates.clone();
		}
	
    @FXML
    void Next(ActionEvent event) {
    	String gastype;
    	Car car ;
    	String Services,Rate=null;
    System.out.println(comboPlan.getValue());
    if(txtCarNumber.getText().isEmpty() || comboPlan.getSelectionModel().isEmpty()|| comboServices.getSelectionModel().isEmpty()|| comboStation.getSelectionModel().isEmpty()||comboID.getSelectionModel().isEmpty())
    {
    	
    	
    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setAlertType(AlertType.INFORMATION); 
				alert.setContentText("There is missing field");
				alert.show(); 
			}
		});
    }
    		
    else {
    	car=new Car(comboID.getValue(), txtCarNumber.getText(), comboPlan.getValue(), comboServices.getValue(),comboGastype.getValue(), comboStation.getValue(), comboStation2.getValue(), comboStation3.getValue(),Rate);
    	double rateNew = 0;
    	gastype=comboGastype.getValue();
    	Services=comboServices.getValue();
    	System.out.println(Services);
    	System.out.println(ratesUpdateArrayList);
    	if(comboServices.getValue().equals("Casual fueling"))
    	{
    		comboPrePaying.setVisible(false);
    		for(int i=0;i<4;i++)
    		{
    			 if(comboGastype.getValue().equals(ratesUpdateArrayList.get(i).getFuelType())){
    			Rate=ratesUpdateArrayList.get(i).getPrice();
    			}
    		}
    		double d=Double.parseDouble(Rate);
    		
    			rateNew=d;
    	}
    	else if(comboServices.getValue().equals("Regular monthly 1 car"))
    	{
    		comboPrePaying.setVisible(false);

    		for(int i=0;i<4;i++)
    		{
    			 if(comboGastype.getValue().equals(ratesUpdateArrayList.get(i).getFuelType())){
    			Rate=ratesUpdateArrayList.get(i).getPrice();
    			}
    		}
    		double d=Double.parseDouble(Rate);
    		double newRate=car.calculate_car_rate(d, "Regular monthly 1 car", null);
    			rateNew=newRate;
    	}
    	else if(comboServices.getValue().equals("Regular monthly +1"))
    	{
    		comboPrePaying.setVisible(false);
    		for(int i=0;i<4;i++)
    		{
    			 if(comboGastype.getValue().equals(ratesUpdateArrayList.get(i).getFuelType())){
    			Rate=ratesUpdateArrayList.get(i).getPrice();
    			}
    		}
    		double d=Double.parseDouble(Rate);
    		double newRate=car.calculate_car_rate(d, "Regular monthly +1", null);
    		rateNew=newRate;
    	}
    	else if(comboServices.getValue().equals("Full monthly"))
    	{
    		for(int i=0;i<4;i++)
    		{
    			 if(comboGastype.getValue().equals(ratesUpdateArrayList.get(i).getFuelType())){
    			Rate=ratesUpdateArrayList.get(i).getPrice();
    			}
    		}
    		double d=Double.parseDouble(Rate);
    		double newRate=car.calculate_car_rate(d, "Full monthly", comboPrePaying.getValue());
    		rateNew=newRate;
    	}
    	System.out.println(rateNew);
    	String s=String.valueOf(rateNew);  
    	car.setRateForCar(s);
    	System.out.println(car);
    	CarRegisterController.acainstance.chat.accept(new Message(8, car));
    	CarRegisterDetails clientRegister;
    	clientRegister = new CarRegisterDetails();
    	runLater(() -> {
    		clientRegister.start(splitpane, null, "User");
});}
    }
    
    @FXML
    void DisplayPrePaying(ActionEvent event) {
    	if(comboServices.getValue().equals("Full monthly"))
    	{
    		System.out.println("aaaa");
    		comboPrePaying.setVisible(true);
    	}
    	else {
    		comboPrePaying.setVisible(false);
		}
    }
    
    @FXML
    void PlanChoose(ActionEvent event) {
    	String currentLevel=comboPlan.getValue();
    	if(currentLevel.equals("Level 1"))
    	{
      		comboStation.setVisible(true);
        	gasLabel.setVisible(true);
        	gasLabel.setVisible(true);
        	star2.setVisible(true);
    		comboStation2.setVisible(false);
    		comboStation3.setVisible(false);
    	gasLabel1.setVisible(false);
    	gasLabel2.setVisible(false);
    	star.setVisible(false);
    	}
    	else if(currentLevel.equals("Level 2 (2-3 Gas stations)"))
    	{
     		comboStation.setVisible(true);
        	gasLabel.setVisible(true);
        	gasLabel.setVisible(true);
        	star2.setVisible(true);
    		//comboStation2.setValue("Null");
    		comboStation2.setVisible(true);
    		comboStation3.setVisible(true);
    	gasLabel1.setVisible(true);
    	gasLabel2.setVisible(true);
    	star.setVisible(true);
    	}
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
		acainstance = this;
		chat.accept(new Message(9, null));
		chat.accept(new Message(35, null));
		comboID.setItems(List);
		planValues.add("Level 1");
		planValues.add("Level 2 (2-3 Gas stations)");
		serviceValues.add("Casual fueling");
		serviceValues.add("Regular monthly 1 car");
		serviceValues.add("Regular monthly +1");
		serviceValues.add("Full monthly");
		gasValues.add("Paz Station");
		gasValues.add("Yellow Station");
		gasValues.add("Ten Station");
		gastypeValues.add("Gasoline 95");
		gastypeValues.add("Diesel fuel");
		gastypeValues.add("Scooters fuel");
		planList.addAll(planValues);
		serviceList.addAll(serviceValues);
		gasList.addAll(gasValues);
		gastypeList.addAll(gastypeValues);
		comboPlan.setItems(planList);
		comboServices.setItems(serviceList);
		comboStation.setItems(gasList);
		comboStation2.setItems(gasList);
		comboStation3.setItems(gasList);
		comboGastype.setItems(gastypeList);
		pay.add("Yes");
		pay.add("No");
		paylist.addAll(pay);
		comboPrePaying.setItems(paylist);
	}


}
