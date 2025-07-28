package userinput;
import java.util.*;
public class Main {
      public static void main(String[] args) {
    	  Scanner o= new Scanner(System.in);
    	  int a= o.nextInt();
    	  int b= o.nextInt();		
    	  int c=a*b;
    	  System.out.println("The area of rectangle of dimensions "+a+" and "+b+" units is "+c);
      }
}
