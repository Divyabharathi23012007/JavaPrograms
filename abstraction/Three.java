package abstraction;
//100% abstraction 
interface one{
	public void disp();
}
 class two implements one{
	 public void disp() {
		 System.out.println("RIT");
	 }
 }
public class Three {
    public static void main(String [] args) {
    	two o= new two();
    	o.disp();
    }
}
