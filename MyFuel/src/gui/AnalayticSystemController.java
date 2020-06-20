package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.AnalyticSystem;
import Entity.Rating;
import Entity.User;
import client.ClientConsole;
import common.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * 
 * This class represents a controller for the gui AnalayticSystem
 *
 */

public class AnalayticSystemController implements Initializable{

	public static AnalayticSystemController acainstance;
	public ClientConsole details= new ClientConsole("localhost", 5555);
	
	
	@FXML
    private AnchorPane analyticsystemtable;

    @FXML
    private TableView<Rating> analyticsystemTable;
    
    @FXML
    private TableColumn<Rating, String> clmClientID;

    @FXML
    private TableColumn<Rating, String> clmRating;

    @FXML
    private Button btnshowrates;
	
	
	
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	 ObservableList<AnalyticSystem> List1 =FXCollections.observableArrayList(); 
	 ObservableList<Rating> List2 =FXCollections.observableArrayList();
	private AnchorPane lowerAnchorPane;
	/** This methode load the information into the gui
	*
	* @param splitpane this parameter form the type 
	* @param user this paramater from the type user that contains id,firtstname,lastname,email,username,password,rank,clienttype,status,image
	* @param userJob this parameter from the type string 
	*/

	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/MarketingManagerAnalyticSystem.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}
}
	/**
	 *  this method get array of type AnalyticSystem from sql table
	 * @param analyticsystem for type ArrayList
	 */
	 

	
	public void AnalayticSystemAcceptor(ArrayList<AnalyticSystem> analyticsystem) {
		List1.addAll(analyticsystem);
		System.out.println(List1);
		}
	
	/**
	 *  this method get array of type Rating from sql table
	 * @param rating for type ArrayList
	 */

	public void RatingAcceptor(ArrayList<Rating> rating) {
		List2.addAll(rating);
		System.out.println(List2);
		}

	/**
	 *   this function for click showrates 
	 * @param event of button 
	 */
	LocalDate now=LocalDate.now();

    @FXML
    void shorrates(ActionEvent event) {
    	System.out.println(List1);
    	System.out.println(List2);
    	String tempdate=String.valueOf(now.getDayOfWeek());

    	int countgasoline95 = 0,countdiesel = 0,countscooter = 0,countCasualfueling = 0,countRegularmonthly1car = 0,countRegularmonthly1 = 0,countFullmonthlysubscription = 0;
    	String morning="6:00",afternon="14:00",night="22:00";
    	int countmorning = 0,countafternon = 0,countnight = 0;
    	int Rating = 0;
    	String temp;
    	if(tempdate.compareTo("SUNDAY")==0)
		{

    	for(int j=0;j<List2.size();j++)
    	{
         countgasoline95=0;
         countdiesel=0;
         countscooter=0;
         countCasualfueling=0;
         countRegularmonthly1car=0;
         countRegularmonthly1=0;
         countFullmonthlysubscription=0;
         countmorning=0;
         countafternon=0;
         countnight=0;
         Rating=0;
    	
    		for(int i=0;i<List1.size();i++)
    		{
    			if(List2.get(j).getClientid().equals(List1.get(i).getClientID()))
    			{
    						if(List1.get(i).getGastype().equals("Gasoline 95"))
    					countgasoline95++;
    				else	if(List1.get(i).getGastype().equals("Diesel fuel"))
    					countdiesel++;
    				else	if(List1.get(i).getGastype().equals("Scooters fuel"))
    					countscooter++;
    						
    						if(List1.get(i).getService().equals("Casual fueling"))
    					countCasualfueling++;
    				else	if(List1.get(i).getService().equals("Regular monthly 1 car"))
    					countRegularmonthly1car++;
    				else	if(List1.get(i).getService().equals("Regular monthly +1"))	
    					countRegularmonthly1++;
    				else	if(List1.get(i).getService().equals("Full monthly subscription"))
    					countFullmonthlysubscription++;
    						
    					if(List1.get(i).getTime().compareTo(morning)>0 && List1.get(i).getTime().compareTo(afternon)<0)
    						countmorning++;
    					if(List1.get(i).getTime().compareTo(afternon)>0 && List1.get(i).getTime().compareTo(night)<0)
    						countafternon++;
    					if(List1.get(i).getTime().compareTo(night)>0 && List1.get(i).getTime().compareTo(morning)<0)
    						countnight++;
    			}
    			
    				if(countgasoline95>=5) {
    					countgasoline95=1;
    					System.out.println("aa");
    				}
    				if(countdiesel>=4) {
    					countdiesel=1;
    					System.out.println("bb");

    				}
    				if(countscooter>=2) {
    					countscooter=1;
    					System.out.println("cc");

    				}
    				
    				if(countCasualfueling>countRegularmonthly1car && countCasualfueling>countRegularmonthly1&& countCasualfueling>countFullmonthlysubscription)
					{
    					countCasualfueling=1;
    					countRegularmonthly1car=0;
    					countRegularmonthly1=0;
    					countFullmonthlysubscription=0;
					}
    				else	if(countRegularmonthly1car>countCasualfueling && countRegularmonthly1car>countRegularmonthly1&& countRegularmonthly1car>countFullmonthlysubscription)
					{
    					countCasualfueling=0;
    					countRegularmonthly1car=1;
    					countRegularmonthly1=0;
    					countFullmonthlysubscription=0;
					}
    				else	if(countRegularmonthly1>countCasualfueling && countRegularmonthly1>countRegularmonthly1car&& countRegularmonthly1>countFullmonthlysubscription)
					{
    					countCasualfueling=0;
    					countRegularmonthly1car=0;
    					countRegularmonthly1=2;
    					countFullmonthlysubscription=0;
					}
    				else		if(countFullmonthlysubscription>countCasualfueling && countFullmonthlysubscription>countRegularmonthly1car&& countFullmonthlysubscription>countRegularmonthly1)
					{
    					countCasualfueling=0;
    					countRegularmonthly1car=0;
    					countRegularmonthly1=0;
    					countFullmonthlysubscription=3;
					}
    				
    						if(countmorning>countafternon && countmorning>countnight)
    						{
    							countmorning=2;
    							countafternon=0;
    							countnight=0;
    						}
    				else	if(countafternon>countmorning && countafternon>countnight)
    						{
    							countafternon=1;
    							countnight=0;
    							countmorning=0;
    						}
    				else	if(countnight>countmorning && countnight>countafternon)
    						{
    							countnight=4;
    							countmorning=0;
    							countafternon=0;
    						}
    		}
    	
    		Rating=countafternon+countmorning+countnight+countscooter+countdiesel+countgasoline95+countCasualfueling+countRegularmonthly1car+countRegularmonthly1+countFullmonthlysubscription;
    		if(Rating>10)
    	    	Rating=10;
    		System.out.println(Rating);
    		temp=String.valueOf(Rating);
    		System.out.println(temp);
    		List2.get(j).setRate(temp);
    	}
    	
		}
    	
    	System.out.println(List1);
    	System.out.println(List2);
    	analyticsystemTable.setItems(List2);
    	
    
    }
	
	
    /**
	 * this method send notification  to chat client
	 */



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;		
		details.accept(new Message(76, null));
		details.accept(new Message(77, null));
		clmClientID.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
		clmClientID.setCellValueFactory(new PropertyValueFactory<>("clientid"));
		clmRating.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
		clmRating.setCellValueFactory(new PropertyValueFactory<>("rate"));
			
	}
	

}
