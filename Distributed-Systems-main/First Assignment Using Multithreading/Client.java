import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;

public class Client extends Thread

{

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		
		try
		{
			String url="rmi://localhost/Server";
			ServerIntf serverIntf=(ServerIntf) Naming.lookup(url);
			
			System.out.println("Enter Number 1 : ");
			int a=sc.nextInt();
			
			System.out.println("Enter Number 2 : ");
			int b=sc.nextInt();
			
			System.out.println("First Number : " + a);
			System.out.println("Second Number : " + b);
			
			Thread add=new Thread(()->
			{
				try
				{
					Thread.sleep(2000);
					double ans=serverIntf.ad(a,b);
					System.out.println("Addition ---> "+ans);
				}catch(Exception e)
				{
					System.out.println("Exception : "+e.getMessage());
				}	
			});
			
			Thread sub=new Thread(()->
			{
				try{
					Thread.sleep(4000);
					double ans=serverIntf.sb(a,b);
					System.out.println("Subtraction ---> "+ans);
				}catch(Exception e)
				{
					System.out.println("Exception : "+e.getMessage());
				}
			
			});
			
			Thread mul=new Thread(()->
			{
				try
				{
					Thread.sleep(6000);
					double ans=serverIntf.ml(a,b);
					System.out.println("Multiplication ---> "+ans);
				}catch(Exception e)
				{
					System.out.println("Exception : "+e.getMessage());
				}	
			});
			
			Thread div=new Thread(()->
			{
				try{
					Thread.sleep(8000);
					double ans=serverIntf.dv(a,b);
					System.out.println("Division ---> "+ans);
				}catch(Exception e)
				{
					System.out.println("Exception : "+e.getMessage());
				}
			
			});
			
			add.start();
			sub.start();
			mul.start();
			div.start();
			
			add.join();
			sub.join();
			mul.join();
			div.join();
			
		}catch(Exception e)
		{
			System.out.println("Exception : "+e.getMessage());
		}
	}
	
}
