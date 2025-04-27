import java.rmi.*;
import java.util.Scanner;


public class Client
{

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
	try
	{
		String url="rmi://localhost/Server";
		ServerIntf serverintf=(ServerIntf) Naming.lookup(url);
		
		System.out.println("Enter First Number:");
		double a=sc.nextDouble();
		
		System.out.println("Enter Second Number:");
		double b=sc.nextDouble();
		
		System.out.println("First Number:"+a);
		System.out.println("Second Number:"+b);
		
		System.out.println("Addition:"+serverintf.Addition(a,b));
		System.out.println("Subtraction:"+serverintf.Subtraction(a,b));
		System.out.println("Multiplication:"+serverintf.Multiplication(a,b));
		System.out.println("Division:"+serverintf.Division(a,b));
	
	
	}catch(Exception e)
	{
	
		System.out.println("Exception:"+e.getMessage());
		
	}


}
}
