import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf{

	ServerImpl() throws RemoteException{}
	
	public double pow2(int a) throws RemoteException
	{
		return Math.pow(2,a);
	}

	public String stcmp(String s,String t) throws RemoteException
	{
		int cmp=s.compareTo(t);
		if(cmp>=0)
		{
			return s;
		}
		return t;
	}
	
	public int cvwl(String a) throws RemoteException
	{
		int clc=0;
		int cuc=0;
		for(int i=0;i<a.length();i++)
		{
			char ch=a.charAt(i);
			if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u')
			{
				clc++;	
			}
			else if(ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U')
			{
				cuc++;
			}
		}
		return clc+cuc;
	}

}
