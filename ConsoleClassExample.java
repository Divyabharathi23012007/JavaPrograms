import java.io.Console;

public class ConsoleClassExample {

  public static void main(String[] args) {
    Console c = System.console();
    System.out.print("Enter your name: ");
    String n = c.readLine(); // string input
    System.out.println("Welcome " + n);
  }
}