import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.time.LocalTime;

public class Clock_One
{
	public static void main(String args[])
	{
		try
		{
			ServerIntf s1 = new ServerImpl(LocalTime.parse("07:05:00", AppConstants.formatter));
			Registry reg1 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_1);
			reg1.rebind(ServerImpl.class.getSimpleName(), s1);
			System.out.println("Server 1 Running On Port: "+AppConstants.SERVER_PORT_1);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
