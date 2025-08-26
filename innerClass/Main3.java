package innerClass;

class Class {
	  int x = 10;

	  class InnerClass {
	    public int myInnerMethod() {
	      return x;
	    }
	  }
	}

	public class Main3 {
	  public static void main(String[] args) {
	    Class myOuter = new Class();
	    Class.InnerClass myInner = myOuter.new InnerClass();
	    System.out.println(myInner.myInnerMethod());
	  }
	}
