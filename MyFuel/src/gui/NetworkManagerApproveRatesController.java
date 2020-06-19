package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.accessibility.internal.resources.accessibility;
import com.sun.prism.paint.Color;

import Entity.Inventory;
import Entity.Rates;
import Entity.User;
import client.ClientConsole;
import common.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class NetworkManagerApproveRatesController implements Initializable {
    @FXML
    public Label status;
	  @FXML
	    private Button btnRate;
    @FXML
    private TableView<Rates> ratesTable;

    @FXML
    private TableColumn<Rates, String> clmFuelType;

    @FXML
    private TableColumn<Rates, String> clmPrice;

    @FXML
    private TableColumn<Rates, String> clmStatus;

    @FXML
    private Button btnRefresh;
	int j;
	int ratesLentgh;
	  final Button addButton  = new Button("Confirm");
	public static NetworkManagerApproveRatesController acainstance;
    @FXML
    private Button btnConfirm;
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;
    ObservableList<Rates> List =FXCollections.observableArrayList(); 
	public ClientConsole details= new ClientConsole("localhost", 5555);
	Rates[] rates = new Rates[10];

	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/NetworkManagerApproveRates.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	
	public void RatesAcceptor(ArrayList<Rates> ratesArray) {
		List.addAll(ratesArray);
		System.out.println(List);
		/*int i;
		System.out.println(ratesArray.size());
		for(i=0;i<ratesArray.size();i++) {
		rates[i] = new Rates(ratesArray.get(i).getFuelType(), ratesArray.get(i).getPrice());
		ratesLentgh=i+1;
		}*/
		}
    @FXML
    void OpenRequests(ActionEvent event) {
		//details.accept(new Message(17, null));
    	ratesTable.setItems(List);
    }
    @FXML
    void Refresh(ActionEvent event) {
    	List.clear();
    	ratesTable.setItems(List);
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acainstance = this;		
		details.accept(new Message(17, null));
		clmFuelType.setStyle( "-fx-background-color: #01509f; -fx-text-fill: white;");
		clmFuelType.setCellValueFactory(new PropertyValueFactory<>("FuelType"));
		clmPrice.setStyle( "-fx-background-color: #01509f; -fx-text-fill: white;");
		clmPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		addConfirmButtonToTable();
		addNotConfirmButtonToTable();
		
	}
	
    private void addConfirmButtonToTable() {
        TableColumn<Rates, String> colBtn = new TableColumn("Confirm Rate");
       
        Callback<TableColumn<Rates, String>, TableCell<Rates, String>> cellFactory = new Callback<TableColumn<Rates, String>, TableCell<Rates, String>>() {
            @Override
            public TableCell<Rates, String> call(final TableColumn<Rates, String> param) {
                final TableCell<Rates, String> cell = new TableCell<Rates, String>() {
                    private final Button btn = new Button("Confirm");
                    
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Rates data = getTableView().getItems().get(getIndex());
                            Rates newRates =new Rates(data.getFuelType(), data.getPrice());
                       //    System.out.println(newRates.getFuelType() +" "+newRates.getPrice());
                           NetworkManagerApproveRatesController.acainstance.details.accept(new Message(18, newRates));
                           NetworkManagerApproveRatesController.acainstance.details.accept(new Message(19, newRates));
                           Alert alert = new Alert(AlertType.INFORMATION);
           				alert.setAlertType(AlertType.INFORMATION); 
           				alert.setContentText("Rate changed successfully!");
           				alert.show(); 
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
        colBtn.setStyle("-fx-background-color: #01509f; -fx-text-fill: white;");
        ratesTable.getColumns().add(colBtn);
    }
    private void addNotConfirmButtonToTable() {
        TableColumn<Rates, String> colBtn = new TableColumn("Confirm Rate");

        Callback<TableColumn<Rates, String>, TableCell<Rates, String>> cellFactory = new Callback<TableColumn<Rates, String>, TableCell<Rates, String>>() {
            @Override
            public TableCell<Rates, String> call(final TableColumn<Rates, String> param) {
                final TableCell<Rates, String> cell = new TableCell<Rates, String>() {
                    private final Button btn = new Button("Not Confirm");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Rates data = getTableView().getItems().get(getIndex());
                            Rates newRates =new Rates(data.getFuelType(), data.getPrice());
                            System.out.println("aaaaaaaaaaaaaaaaaaaa");
                            NetworkManagerApproveRatesController.acainstance.details.accept(new Message(20, newRates));
                            Alert alert = new Alert(AlertType.INFORMATION);
               				alert.setAlertType(AlertType.INFORMATION); 
               				alert.setContentText("Rate not changed!!");
               				alert.show(); 
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
        colBtn.setStyle("-fx-background-color: #01509f; -fx-text-fill: white;");
        ratesTable.getColumns().add(colBtn);
    }
}
