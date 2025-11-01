import java.rmi.*;

public class Client{
    public static void main(String[] args) {
    	System.out.println("Name: Divya Bharathi I");
    	System.out.println("Reg no: 2117240020096");
    	System.out.println();
    	try {
            for(int i = 1; i <= 2; i++) {
                int id = i;
                new Thread(() -> {
                    try {
                        MyInterface stub = (MyInterface) java.rmi.Naming.lookup("rmi://localhost:1096/MyService");
                        System.out.println(stub.sayHello("Client-" + id));
                    } catch (Exception e) { System.out.println(e); }
                }).start();
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

