package cloning;

class Mai implements Cloneable {

	  // declare variables
	  String name;
	  int version;
	  public static void main(String[] args) {

	    // create an object of Main class
	    Mai obj1 = new Mai();

	    // initialize name and version using obj1
	    obj1.name = "Java";
	    obj1.version = 14;

	    // print variable
	    System.out.println(obj1.name);        // Java
	    System.out.println(obj1.version);     // 14

	    try {

	      // create a clone of obj1
	      Mai obj2 = (Mai)obj1.clone(); //typecasting to make the obj exhibiting same nature

	      // print the variables using obj2
	      System.out.println(obj2.name);      // Java
	      System.out.println(obj2.version);   // 14

	      // changing value of name
	      // using obj2
	      obj2.name = "Python";
	      System.out.println(obj2.name);      // Python

	      // check if value associated 
	      // with obj1 is changed
	      System.out.println(obj1.name);      // Java
	    }
	    catch (Exception e) {
	      System.out.println(e);
	    }

	  }
	}