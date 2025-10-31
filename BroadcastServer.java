import java.io.*;
import java.net.*;
import java.util.*;

public class BroadcastServer {

    // List to store all connected clients
    private static ArrayList<Socket> clients = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(12234);
        System.out.println("Server started... Waiting for clients...");

        while (true) {
            Socket client = server.accept();
            clients.add(client);
            System.out.println("Client connected: " + client);

            // Start a thread for each client
            new ClientHandler(client).start();
        }
    }

    static class ClientHandler extends Thread {
        Socket client;
        BufferedReader reader;

        public ClientHandler(Socket socket) throws Exception {
            this.client = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        public void run() {
            try {
                String msg;
                while ((msg = reader.readLine()) != null) {
                    broadcast(msg, client);
                }
            } catch (Exception e) {
                System.out.println("Client disconnected: " + client);
            }
        }

        // Broadcast to all except sender
        private void broadcast(String msg, Socket sender) {
            for (Socket s : clients) {
                try {
                    if (s != sender) {
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        out.println(msg);
                    }
                } catch (Exception e) {}
            }
        }
    }
}
