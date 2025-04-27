import java.rmi.*;
import java.rmi.server.*;

interface ServerIntf extends Remote

{
	
	public double ad(double a,double b) throws RemoteException;
	
	public double sb(double a,double b) throws RemoteException;
	
	public double ml(double a,double b) throws RemoteException;
	
	public double dv(double a,double b) throws RemoteException;

}
