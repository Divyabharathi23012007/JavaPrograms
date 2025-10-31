import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            // Get the local host address
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Local Host Name: " + localHost.getHostName());
            System.out.println("Local Host Address: " + localHost.getHostAddress());

            // Get the InetAddress of a remote host (e.g., google.com)
            InetAddress remoteHost = InetAddress.getByName("www.google.com");
            System.out.println("\nRemote Host Name: " + remoteHost.getHostName());
            System.out.println("Remote Host Address: " + remoteHost.getHostAddress());

            // Get all IP addresses associated with a domain
            InetAddress[] addresses = InetAddress.getAllByName("www.microsoft.com");
            System.out.println("\nAll IP addresses for www.microsoft.com:");
            for (InetAddress addr : addresses) {
                System.out.println(addr);
            }

        } catch (UnknownHostException e) {
            System.out.println("Host not found: " + e.getMessage());
        }
    }
}