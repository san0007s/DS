package ReverseStringFile;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.*;

public class Server {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args,null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            ReverseImpl servant =  new ReverseImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(servant);
            modelReverse.Reverse ReverseRef = modelReverse.ReverseHelper.narrow(ref);


            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent[] path = ncRef.to_name("ReverseService");
            ncRef.rebind(path,ReverseRef);

            System.out.println("server ready");
            orb.run();


        }
        catch (Exception e)
        {
            System.out.println("error is: "+e.getMessage());
        }
    }
}
