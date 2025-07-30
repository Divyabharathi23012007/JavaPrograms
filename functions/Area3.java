package functions;

//with argument without return

public class Area3 {
    public void area(int a, int b) {
            int c=a*b;
            System.out.println("Area is "+c);
    }
    public static void main(String[] args) {
    	    Area3 o = new Area3();
    	    o.area(7, 5);
    }
}
