import java.rmi.*;

interface ServerIntf extends Remote {

	public double CtoF(double a) throws RemoteException;
	public double MtoK(double a) throws RemoteException;
	public int fact(int a) throws RemoteException;
	public String hel(String a) throws RemoteException;
}
