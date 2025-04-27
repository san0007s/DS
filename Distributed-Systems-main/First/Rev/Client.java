import java.rmi.*;
import java.util.Scanner;

public class Client{

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		try{
		String url="rmi://localhost/Server";
		ServerIntf serverIntf=(ServerIntf) Naming.lookup(url);
		
		System.out.println("Enter Celsius:");
		double a=sc.nextDouble();

		System.out.println("Enter Miles:");
		double b=sc.nextDouble();
		
		System.out.println("Enter Factorial:");
		int c=sc.nextInt();
		
		System.out.println("Enter String:");
		String d=sc.next();		
		
		System.out.println("1:"+serverIntf.CtoF(a));
		
		System.out.println("2:"+serverIntf.MtoK(b));
		System.out.println("3:"+serverIntf.fact(c));
		System.out.println("4:"+serverIntf.hel(d));
		
		
		}catch(Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
		}
	}
}
