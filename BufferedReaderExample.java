import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class BufferedReaderExample {
   public static void main(String[] args) throws IOException {
       InputStreamReader input = new InputStreamReader(System.in);
       BufferedReader reader = new BufferedReader(input);
 
       System.out.print("Please enter the input: ");
       String name = reader.readLine(); // input string
       System.out.print("You entered: ");
       System.out.println(name);
          
       // Closing to avoid memory leak.
       input.close();
   }
}
