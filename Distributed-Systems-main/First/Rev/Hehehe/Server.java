import java.rmi.*;
import java.util.Scanner;

public class Server{

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		
		try{
		ServerImpl serverImpl=new ServerImpl();
		Naming.rebind("Server",serverImpl);
		System.out.println("Server Started: ");
		
		}catch(Exception e)
		{
			System.out.println("Exception: "+e.getMessage());	
		}
	}

}
