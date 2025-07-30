package functions;

//with static , no need to create object, directly we can go for function call

public class Area5 {
	
	public static int area(int a,int b) {
    	int c=a*b;
    	return c;
    }
    
	public static void main(String[] args) {
       
          int r= area(9, 8);
          System.out.println("Area is "+r);
	}

}
