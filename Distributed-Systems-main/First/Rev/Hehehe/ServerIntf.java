import java.rmi.*;

interface ServerIntf extends Remote{

	public double pow2(int n) throws RemoteException;
	
	public String stcmp(String s,String t) throws RemoteException;
	
	public int cvwl(String s) throws RemoteException;

}
