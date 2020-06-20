package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import Entity.OrderConfirmation;
import Entity.Rates;
import Entity.OrderConfirmation;
import Entity.OrderConfirmation;
import Entity.User;
import client.ClientConsole;
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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StationManagerOrderConfirmationController implements Initializable {

	public static StationManagerOrderConfirmationController acainstance;
	public ClientConsole details= new ClientConsole("localhost", 5555);

		@FXML
    	private TableView<OrderConfirmation> orderConfirmationTable;

    	@FXML
	    private TableColumn<OrderConfirmation, String> clmOrderNumber;

	    @FXML
	    private TableColumn<OrderConfirmation, String> clmType;

	    @FXML
	    private TableColumn<OrderConfirmation, String> clmQuantity;
	    @FXML
	    private TableColumn<OrderConfirmation, String> clmStatus;

	    @FXML
	    private TableColumn<OrderConfirmation, String> clmStation;

	    @FXML
	    private TableColumn<OrderConfirmation, String> clmAddress;

	    @FXML
	    private TableColumn<OrderConfirmation, String> clmDate;

	    @FXML
	    private Button btnShowOrder;
	
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	private static User user;
	private static String userrank;
	public static ProfileSettingsController ProfileSetting;
	public static Stage primaryStage;
	 ObservableList<OrderConfirmation> List =FXCollections.observableArrayList(); 
	  final Button addButton  = new Button("Confirm");

	private AnchorPane lowerAnchorPane;
	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		this.user=user;
		this.userrank=userJob;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/StationManagerOrderConfirmation.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}
}
	public void OrderConfirmationAcceptor(ArrayList<OrderConfirmation> odcon) {
		List.addAll(odcon);
		System.out.println(List);
		}
	  @FXML
	    void ShowOrder(ActionEvent event) {
		  orderConfirmationTable.setItems(List);
		//  System.out.println(""+List.size());
	//	  System.out.println(List.get(0).getQuantity());
	    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;		
		details.accept(new Message(26, null));
		clmOrderNumber.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
		clmOrderNumber.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
		clmType.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
		clmType.setCellValueFactory(new PropertyValueFactory<>("Type"));
		clmType.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
		clmQuantity.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
		clmQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
		clmStatus.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
		clmStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
		clmStation.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
		clmStation.setCellValueFactory(new PropertyValueFactory<>("StationName"));
		clmAddress.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
		clmAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
		clmDate.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
		clmDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
		addConfirmButtonToTable();
		addNotConfirmButtonToTable();
	}
	 private void addConfirmButtonToTable() {
	        TableColumn<OrderConfirmation, String> colBtn = new TableColumn("ConfirmOrder");
	       
	        Callback<TableColumn<OrderConfirmation, String>, TableCell<OrderConfirmation, String>> cellFactory = new Callback<TableColumn<OrderConfirmation, String>, TableCell<OrderConfirmation, String>>() {
	            @Override
	            public TableCell<OrderConfirmation, String> call(final TableColumn<OrderConfirmation, String> param) {
	                final TableCell<OrderConfirmation, String> cell = new TableCell<OrderConfirmation, String>() {
	                    private final Button btn = new Button("Confirm");
	                    
	                    {
	                        btn.setOnAction((ActionEvent event) -> {
	                        	OrderConfirmation data = getTableView().getItems().get(getIndex());
	                        	OrderConfirmation order =new OrderConfirmation(data.getOrderNumber(), data.getType(), data.getQuantity(),data.getStatus(),data.getStationName(),data.getAddress(),data.getDate(),data.getManagerID());
	                           System.out.println(order.getOrderNumber()+" " +order.getType()+" "+order.getQuantity());
	                       System.out.println("confirm");
	                       StationManagerOrderConfirmationController.acainstance.details.accept(new Message(46, order));
	                       Platform.runLater(new Runnable() {
	               			@Override
	               			public void run() {
	               				Alert alert = new Alert(AlertType.INFORMATION);
	               				alert.setAlertType(AlertType.INFORMATION); 
	               				alert.setContentText("Order sent to supplier!");
	               				alert.show(); 
	               			}
	               		});
	                       //   NetworkManagerApproveRatesController.acainstance.details.accept(new Message(18, newRates));
	                     //      NetworkManagerApproveRatesController.acainstance.details.accept(new Message(19, newRates));
	                        });
	                    }
	                    public void updateItem(String item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setGraphic(null);
	                        } else {
	                            setGraphic(btn);
	                        }
	                    }
	                };
	                return cell;
	            }
	        };
	        colBtn.setPrefWidth(120);
	        
	        colBtn.setCellFactory(cellFactory);
	        colBtn.setStyle("-fx-background-color: #0E76DD; -fx-text-fill: white;");
	        orderConfirmationTable.getColumns().add(colBtn);
	    }
	    private void addNotConfirmButtonToTable() {
	        TableColumn<OrderConfirmation, String> colBtn = new TableColumn("Not Confirm");

	        Callback<TableColumn<OrderConfirmation, String>, TableCell<OrderConfirmation, String>> cellFactory = new Callback<TableColumn<OrderConfirmation, String>, TableCell<OrderConfirmation, String>>() {
	            @Override
	            public TableCell<OrderConfirmation, String> call(final TableColumn<OrderConfirmation, String> param) {
	                final TableCell<OrderConfirmation, String> cell = new TableCell<OrderConfirmation, String>() {
	                    private final Button btn = new Button("Not Confirm");
	                    {
	                        btn.setOnAction((ActionEvent event) -> {
	                        	OrderConfirmation data = getTableView().getItems().get(getIndex());
	                        	OrderConfirmation order =new OrderConfirmation(data.getOrderNumber(), data.getType(), data.getQuantity(),data.getStatus(),data.getStationName(),data.getAddress(),data.getDate(),data.getManagerID());
	                           System.out.println(order.getOrderNumber()+" " +order.getType()+" "+order.getQuantity());
	                           System.out.println("not cinfirm");
		                       StationManagerOrderConfirmationController.acainstance.details.accept(new Message(47, order));
		                       Platform.runLater(new Runnable() {
		               			@Override
		               			public void run() {
		               				Alert alert = new Alert(AlertType.INFORMATION);
		               				alert.setAlertType(AlertType.INFORMATION); 
		               				alert.setContentText("Order canceled!");
		               				alert.show(); 
		               			}
		               		});
	                           // NetworkManagerApproveRatesController.acainstance.details.accept(new Message(20, newRates));
	                           // System.out.println("selectedData: " + data);
	                        });
	                    }
	                    public void updateItem(String item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setGraphic(null);
	                        } else {
	                            setGraphic(btn);
	                        }
	                    }
	                };
	                return cell;
	            }
	        };
	        colBtn.setPrefWidth(120);
	        colBtn.setCellFactory(cellFactory);
	        colBtn.setStyle("-fx-background-color: #0E76DD; -fx-text-fill: white;");
	        orderConfirmationTable.getColumns().add(colBtn);
	    }
}
