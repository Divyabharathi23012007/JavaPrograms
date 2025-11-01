import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String args[]) {
    	System.out.println("Name: Divya Bharathi I");
    	System.out.println("Reg no: 2117240020096");
    	System.out.println();
        try {
            MyInterfaceImpl obj = new MyInterfaceImpl();

            LocateRegistry.createRegistry(1095); 
            Naming.rebind("rmi://localhost:1095/MyService", obj);

            System.out.println("RMI Server Started ...");
        } catch (Exception e) {
            System.out.println("Server Exception: " + e);
        }
        
    }
}
