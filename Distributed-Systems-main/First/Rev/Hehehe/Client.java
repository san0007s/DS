import java.rmi.*;
import java.util.Scanner;

public class Client{

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
	
		try{
		String url="rmi://localhost/Server";
		ServerIntf serverIntf=(ServerIntf) Naming.lookup(url);
	
		System.out.println("Enter Number");
		int a=sc.nextInt();
			
		System.out.println("Enter String:");
		String b=sc.next();
		
		System.out.println("Enter String:");
		String c=sc.next();
			
		System.out.println("Enter String:");
		String d=sc.next();
		
		System.out.println("Pow: "+serverIntf.pow2(a));
		System.out.println("vowel: "+serverIntf.cvwl(b));
		System.out.println("cmp: "+serverIntf.stcmp(c,d));
		
		
		
		}catch(Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
		}
	
	}





}
