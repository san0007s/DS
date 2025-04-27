import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.time.LocalTime;

public class Clock_Three
{
	public static void main(String args[])
	{	
		try
		{
			ServerIntf s3 = new ServerImpl(LocalTime.parse("07:02:50", AppConstants.formatter));
			Registry reg3 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_3);
			reg3.rebind(ServerImpl.class.getSimpleName(), s3);
			System.out.println("Server 3 Created on Port: "+AppConstants.SERVER_PORT_3);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
		
