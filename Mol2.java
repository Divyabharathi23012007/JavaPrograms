package examples;

//polymorphism overloading with diff number of data values
public class Mol2 {
	public void vol(int a, int b) {
		  int c=a*b;
		  System.out.println(c);
	  }
	  public void vol(int e, int d,int g) {
		  int f=e*d*g;
		  System.out.println(f);
	  }
	  public static void main(String[] args) {
		  Mol2 o= new Mol2();
		  o.vol(2, 3);
		  o.vol(5, 6, 8);
	  }
}
