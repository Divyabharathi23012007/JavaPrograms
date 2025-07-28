package functiontype1;

//class definition

public class Area {
	
	//function definition
      public void product(){
    	  
    	  //variable declaration and initialization
          int a=5,b=6;
          int c=a*b;
          System.out.println("Area is "+c);
      }
      public static void main(String[] args) {
    	  
    	  //creating object
    	  Area o= new Area();
    	  
    	  //function call
    	  o.product();   
      }
}
