import java.awt.Desktop;
import java.net.URI;

public class URLBrowser {
         public static void main(String[] args) {
        	 try {
        		 URI uri=new URI("www.google.com");
        		 Desktop.getDesktop().browse(uri);
        		 System.out.println("URL opened in browser");
        	 }catch(Exception e) {
        		 e.printStackTrace();
        	 }
         }
}
