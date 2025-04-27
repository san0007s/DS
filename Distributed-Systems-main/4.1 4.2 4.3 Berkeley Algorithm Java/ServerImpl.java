import java.rmi.*;
import java.rmi.server.*;
import java.time.LocalTime;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf
{
	private LocalTime currTime;
	
	public ServerImpl(LocalTime currTime) throws RemoteException
	{
		this.currTime = currTime;
	}
	
	public LocalTime getTime() throws RemoteException
	{
		return currTime;
	}
	
	public void adjustClock(LocalTime timer, long avgDiff) throws RemoteException
	{
		long timerNano = timer.toNanoOfDay();
		long slaveTime = this.getTime().toNanoOfDay();
		long newNanos = slaveTime - timerNano;
		newNanos = newNanos * -1 + avgDiff + slaveTime;
		LocalTime newLocalTime = LocalTime.ofNanoOfDay(newNanos);
		System.out.println("Updated Time: "+AppConstants.formatter.format(newLocalTime));
		this.currTime = newLocalTime;
	}
}
