import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.time.LocalTime;

public class MainClock
{
	public static void main(String args[])
	{
		try
		{
			LocalTime localTime = LocalTime.parse("07:00:00", AppConstants.formatter);
			System.out.println("Timer Time: "+AppConstants.formatter.format(localTime));
			
			Registry reg1 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_1, 1500);
			ServerIntf s1 = (ServerIntf) reg1.lookup(ServerImpl.class.getSimpleName());
			System.out.println("Connection established with Server 1");
			LocalTime SERVER_TIME_1 = s1.getTime();
			System.out.println("Server 1 Time is: "+AppConstants.formatter.format(SERVER_TIME_1));
			
			Registry reg2 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_2, 1501);
			ServerIntf s2 = (ServerIntf) reg2.lookup(ServerImpl.class.getSimpleName());
			System.out.println("Connection established with Server 2");
			LocalTime SERVER_TIME_2 = s2.getTime();
			System.out.println("Server 2 Time is: "+AppConstants.formatter.format(SERVER_TIME_2));
			
			Registry reg3 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_3, 1502);
			ServerIntf s3 = (ServerIntf) reg3.lookup(ServerImpl.class.getSimpleName());
			System.out.println("Connection established with Server 3");
			LocalTime SERVER_TIME_3 = s3.getTime();
			System.out.println("Server 3 Time is: "+AppConstants.formatter.format(SERVER_TIME_3));
			
			
			// Calulating Average diff
			long nanoLocal = localTime.toNanoOfDay();
			long diff1 = SERVER_TIME_1.toNanoOfDay() - nanoLocal;
			long diff2 = SERVER_TIME_2.toNanoOfDay() - nanoLocal;
			long diff3 = SERVER_TIME_3.toNanoOfDay() - nanoLocal;
			long avgDiff = (diff1+diff2+diff3)/4;
			
			// Updating Clocks
			s1.adjustClock(localTime, avgDiff);
			s2.adjustClock(localTime, avgDiff);
			s3.adjustClock(localTime, avgDiff);
			localTime = localTime.plusNanos(avgDiff);
			
			System.out.println("Local Time: " + AppConstants.formatter.format(localTime));
			System.out.println("Server 1 Time: " + AppConstants.formatter.format(s1.getTime()));
			System.out.println("Server 2 Time: " + AppConstants.formatter.format(s2.getTime()));
			System.out.println("Server 3 Time: " + AppConstants.formatter.format(s3.getTime()));
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
