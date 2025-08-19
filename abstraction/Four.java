package abstraction;
//can vary the level of abstraction
abstract class on{
	abstract void disp();
	public void func() {
		 System.out.println("RIT");
	 }
}
 class tw extends on{
	 void disp() {
		 System.out.println("REC");
	 }
	 
 }
public class Four {
  public static void main(String [] args) {
	  tw o=new tw();
	  o.disp();
	  o.func();
  }
}
