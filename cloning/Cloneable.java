//cloneable obj are traceable

package cloning;

class Main implements Cloneable {

	  // declare variables
	  String name;
	  int version;
	  public static void main(String[] args) {

	    // create an object of Main class
	    Main obj1 = new Main();

	    // initialize name and version using obj1
	    obj1.name = "Java";
	    obj1.version = 14;

	    // print variable
	    System.out.println(obj1.name);       // Java
	    System.out.println(obj1.version);    // 14

	    try {

	      // create clone of obj1
	      Main obj2 = (Main)obj1.clone();

	      // print the variables using obj2
	      System.out.println(obj2.name);      // Java
	      System.out.println(obj2.version);   // 14
	    }
	    catch (Exception e) {
	      System.out.println(e);
	    }

	  }
	}