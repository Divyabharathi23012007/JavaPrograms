import java.rmi.*;

public interface MyInterface extends Remote {
    public String sayHello(String name) throws RemoteException;
}
