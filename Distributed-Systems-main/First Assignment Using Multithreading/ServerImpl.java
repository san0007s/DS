import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf

{

	public ServerImpl() throws RemoteException
	{
	
	}

	public double ad(double a,double b) throws RemoteException
	{
		return a+b;
	}
	
	
	public double sb(double a,double b) throws RemoteException
	{
		return a-b;
	}
	
	public double ml(double a,double b) throws RemoteException
	{
		return a*b;
	}
	
	public double dv(double a,double b) throws RemoteException
	{
		return a/b;
	}

}
