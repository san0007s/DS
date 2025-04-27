import java.rmi.*;
import java.rmi.server.*;

public class Server

{
	public static void main(String args[])
	{
		
		try{
		
			ServerImpl serverImpl=new ServerImpl();
			Naming.rebind("Server",serverImpl);
			
			System.out.println("Server Is Started and Listening :)");
		
		}catch(Exception e)
		{
			System.out.println("Exception : "+e.getMessage());
		}
		
	
	
	
	}
	


}
