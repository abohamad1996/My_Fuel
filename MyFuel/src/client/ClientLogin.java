package client;

import java.io.IOException;

import common.ChatIF;

public class ClientLogin implements ChatIF{
	   public static int DEFAULT_PORT ;
	   ChatClient client;
	  public ClientLogin(String host, int port) 
	  {
	    try 
	    {
	      client= new ChatClient(host, port, this);
	    } 
	    catch(IOException exception) 
	    {
	      System.out.println("Error: Can't setup connection!"+ " Terminating client.");
	      System.exit(1);
	    }
	  }

	  public void accept(Object str) 
	  {
		  client.handleMessageFromClientUI(str);
	  }
	@Override
	public void display(String message) {
	    System.out.println("> " + message);
		
	}

}
