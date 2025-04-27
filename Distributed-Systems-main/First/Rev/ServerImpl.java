import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf{

	ServerImpl() throws RemoteException
	{}
	
	public double CtoF(double a) throws RemoteException
	{
		return (a*9/5+32);
	}
	
	public double MtoK(double a) throws RemoteException
	{
		return (a*1.60934);
	}
	
	public int fact(int a) throws RemoteException
	{
		int f=1;
		for(int i=1;i<=a;i++)
		{
		f=f*i;
		}
		return f;
	}
	
	public String hel(String a) throws RemoteException
	{
		return "Hello"+a;
	}

}
