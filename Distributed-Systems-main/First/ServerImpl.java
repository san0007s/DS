import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf{
	
	ServerImpl() throws RemoteException
	{}
	
	public double Addition(double a,double b) throws RemoteException{
		return a+b;
	}
	
	public double Subtraction(double a,double b) throws RemoteException{
		return a-b;
	}
	
	public double Multiplication(double a,double b) throws RemoteException{
		return a*b;
	}
	
	public double Division(double a,double b) throws RemoteException{
		return a/b;
	}
	

}
