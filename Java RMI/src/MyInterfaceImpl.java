import java.rmi.*;
import java.rmi.server.*;

public class MyInterfaceImpl extends UnicastRemoteObject implements MyInterface {

    public MyInterfaceImpl() throws RemoteException {
        super();
    }

    public String sayHello(String name) throws RemoteException {
        return "Hello " + name + ", welcome to RMI Multi Thread Server!";
    }
    
}
