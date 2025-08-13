//super with method(+ polymorphism)

class fne{
	public void disp() {
		System.out.println("RIT");
	}
}
public class three extends fne{
   public void disp() {
	   System.out.println("REC");
	   super.disp();
   }
   public static void main(String[] args) {
	   three o= new three();
	   o.disp();
   }
   

}
