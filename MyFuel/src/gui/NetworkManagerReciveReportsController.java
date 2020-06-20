package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.Files;
import Entity.OrderConfirmation;
import Entity.StationsInventory;
import Entity.User;
import client.ChatClient;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class NetworkManagerReciveReportsController implements Initializable {
	 ObservableList<Files> FilesList =FXCollections.observableArrayList(); 

	  @FXML
	    private TableView<Files> filestable;

	    @FXML
	    private TableColumn<Files, String> clmName;



	    @FXML
	    private TableColumn<Files, String> clmStatus;

	public ArrayList<Files> filesarr;
	public static NetworkManagerReciveReportsController acainstance;
	public ClientConsole details= new ClientConsole("localhost", 5555);

    @FXML
    private Button btnShowReports;
	@FXML
	private static SplitPane splitpane;
	private FXMLLoader loader;	
	public static Stage primaryStage;
	private AnchorPane lowerAnchorPane;

	public void start(SplitPane splitpane, User user,String userJob) {
		this.splitpane=splitpane;
		primaryStage=LoginController.primaryStage;
		try{	
			loader = new FXMLLoader(getClass().getResource("/gui/NetworkManagerReciveReports.fxml"));
			lowerAnchorPane = loader.load();
			splitpane.getItems().set(1, lowerAnchorPane);
		} catch(Exception e) {
			e.printStackTrace();
	}		
}
	public void FilesAcceptor(ArrayList<Files> files) {
		//filesarr = (ArrayList<Files>)files.clone();
		FilesList.addAll(files);
		}
	
    @FXML
    void Reports(ActionEvent event) {
    		System.out.println("List:"+FilesList);
    		filestable.setItems(FilesList);
    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	acainstance=this;
	details.accept(new Message(63, null));
	clmName.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
	clmName.setCellValueFactory(new PropertyValueFactory<>("Filename"));
	clmStatus.setStyle( "-fx-background-color: #0E76DD; -fx-text-fill: white;");
	clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
	addOpenButtonToTable();
	}
	private void addOpenButtonToTable() {
        TableColumn<Files, String> colBtn = new TableColumn("ConfirmOrder");
       
        Callback<TableColumn<Files, String>, TableCell<Files, String>> cellFactory = new Callback<TableColumn<Files, String>, TableCell<Files, String>>() {
            @Override
            public TableCell<Files, String> call(final TableColumn<Files, String> param) {
                final TableCell<Files, String> cell = new TableCell<Files, String>() {
                    private final Button btn = new Button("Open");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	Files data = getTableView().getItems().get(getIndex());
                        	Files order =new Files(data.getId(),data.getFilename(),data.getPath(),data.getStatus());
                           System.out.println(order.getId()+" " +order.getFilename()+" "+order.getStatus());
                           NetworkManagerReciveReportsController.acainstance.details.accept(new Message(65, order));
                       System.out.println("Open");
                       File file = new File(order.getPath());  
                       Desktop desktop = Desktop.getDesktop();  
                       try {
						desktop.open(file);
                       
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
              
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
        filestable.getColumns().add(colBtn);
    }
}
