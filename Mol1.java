package examples;
// polymorphism with overloading diff data types
public class Mol1 {
  public void area(int a, int b) {
	  int c=a*b;
	  System.out.println(c);
  }
  public void area(double e, double d) {
	  double f=e*d;
	  System.out.println(f);
  }
  public static void main(String[] args) {
	  Mol1 o= new Mol1();
	  o.area(2, 3);
	  o.area(3.5, 5.5);
  }
}
