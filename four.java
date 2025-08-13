class fiv{
	fiv(){
		System.out.println("REC");
	}
	
}
public class four extends fiv  {
       four(){
    	   super();
    	   System.out.println("RIT");
       }
       public static void main(String[] args) {
    	   four o=new four();
    	   
       }
}

