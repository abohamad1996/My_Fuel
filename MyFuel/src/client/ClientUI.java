package client;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Vector;
import gui.EmployeeFrameController;
import client.ClientConsole;

public class ClientUI extends Application {
	

	public static void main( String args[] ) throws Exception
	   { 
		    launch(args);  
	   } // end main
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
						  		
		EmployeeFrameController aFrame = new EmployeeFrameController(); // create StudentFrame
		 
		aFrame.start(primaryStage);
	}
	
	
}
