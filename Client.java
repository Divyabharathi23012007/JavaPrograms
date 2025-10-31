import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 12234);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String clientName = "Client-" + socket.getPort();
        System.out.println(clientName + " connected to server.");

        // Thread to receive messages
        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println(">> " + msg);
                }
            } catch (Exception e) {}
        }).start();

        // Sending messages
        while (true) {
            System.out.print("You: ");
            String msg = keyboard.readLine();
            out.println(clientName + ": " + msg);
        }
    }
}
