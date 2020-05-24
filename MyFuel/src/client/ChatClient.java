// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import client.*;
import common.ChatIF;
import common.Message;
import gui.EmployeeFrameController;
import gui.HomeController;
import gui.LoginController;
import gui.Employee;
import gui.UpdateRoleController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

import DBconnection.DBconnector;
import Entity.User;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI;   public static boolean awaitResponse = false;

  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
	 
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	  awaitResponse = false;
	  Message recieved =(Message)msg;
	  System.out.println("Recived from server " +recieved.getCode());
	  switch(recieved.getCode()) {
	  case 1:
		  ArrayList<?> employeeArrayList =(ArrayList<?>)recieved.getObject();
			 ArrayList<Employee> b=(ArrayList<Employee>)employeeArrayList;
			 EmployeeFrameController.acainstance.TestAcceptor1(b);
		  break;
		  
	  case 2:
		  String st = (String) recieved.getObject();
		  Platform.runLater(() -> {
			  UpdateRoleController.instance.status.setText(st + " Updated!");
		    });
		  break;
	  
	  case 3:
	 	  User user=(User) recieved.getObject();
			//System.out.println(""+a.getId()+" "+a.getFirstname()+" "+a.getLastname()+" "+a.getEmail()+" "+a.getUsername()+" "+a.getPassword()+" "+a.getRank()+" "+a.getStatus());
	 	  if(user.equals(null))
	 	  {
	 		  System.out.println("not found");
	 	  }
	 	  switch (user.getRank()) {
		case 1:
			System.out.println("1");
			HomeController inspector=new HomeController();
			inspector.start(user);
			break;

		case 2:
			System.out.println("2");
	  break;
	  }
	  }
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param str The message from the UI.    
   */
  public void handleMessageFromClientUI(Object str)  
  {
    try
    {
    	awaitResponse = true;
    	sendToServer(str);
		// wait for response
		while (awaitResponse) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }
    catch(IOException e)
    {
      clientUI.display("Could not send message to server.  Terminating client.");
      quit();
    }
  }

  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
