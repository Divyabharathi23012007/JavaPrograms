//polymorphism 

//Method Over Riding

class rit{
	public void disp() {
		System.out.println("RIT");
	}
	
}
class rec extends rit{
	public void disp() {
		System.out.println("REC");
	}
}

public class Test{
	public static void main(String[] args){
        rit o=new rit();
        
            o.disp();
        rit o1=new rec();
            o1.disp();
        
	}
}
