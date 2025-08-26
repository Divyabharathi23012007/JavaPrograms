package innerClass;

class OuterClass {
	  int x = 10;

	  class InnerClass {
	    int y = 5;
	  }
	}

	public class Main1 {
	  public static void main(String[] args) {
	    OuterClass myOuter = new OuterClass();
	    OuterClass.InnerClass myInner = myOuter.new InnerClass();
	    System.out.println(myInner.y + myOuter.x);
	  }
	}
	
	