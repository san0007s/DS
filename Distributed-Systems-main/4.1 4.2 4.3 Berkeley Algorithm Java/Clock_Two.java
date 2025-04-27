import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.time.LocalTime;

public class Clock_Two
{
	public static void main(String args[])
	{	
		try
		{
			ServerIntf s2 = new ServerImpl(LocalTime.parse("07:02:50", AppConstants.formatter));
			Registry reg2 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_2);
			reg2.rebind(ServerImpl.class.getSimpleName(), s2);
			System.out.println("Server 2 Created on Port: "+AppConstants.SERVER_PORT_2);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
		
