import java.rmi.*;
import java.time.LocalTime;

public interface ServerIntf extends Remote
{
	public LocalTime getTime() throws RemoteException;
	public void adjustClock(LocalTime timer, long avgDiff) throws RemoteException;
}
