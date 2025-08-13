//super with variable

class two{
	int a=5;
}
public class one extends two {
           public void disp() {
        	   System.out.println(super.a);           }
           public static void main(String[] args) {
        	   one o=new one();
        	   o.disp();
           }
}
