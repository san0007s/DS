package ReverseStringFile;
import java.util.*;
import modelReverse.Reverse;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Client {
    public static void main(String [] args)
    {
        try{
            ORB orb = ORB.init(args,null);

//           POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
//           rootpoa.the_POAManager().activate();

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            modelReverse.Reverse ref = modelReverse.ReverseHelper.narrow(ncRef.resolve_str("ReverseService"));

            Scanner sc = new Scanner(System.in);

            System.out.print("entre the String: ");
            System.out.println("");
            String s = sc.nextLine();

            System.out.println(ref.reverseString(s));
        }
        catch (Exception e)
        {
            System.out.println("error is : "+e.getMessage());
        }
    }
}
