package functions;

//with return with argument

public class Area4 {
    public int area(int a,int b) {
    	int c=a*b;
    	return c;
    }
    
	public static void main(String[] args) {
          Area4 o= new Area4();
          int r= o.area(9, 8);
          System.out.println("Area is "+r);

	}

}
