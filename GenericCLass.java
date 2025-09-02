class Box<T> {
  T value; // T is a placeholder for any data type, T is a type of object

  void set(T value) {    //receiving values
    this.value = value;
  }

  T get() {     //displaying method
    return value;
  }
}

public class GenericCLass {
  public static void main(String[] args) {
	  
    // Create a Box to hold a String
    Box<String> stringBox = new Box<>(); // typecasting
    stringBox.set("Hello");
    System.out.println("Value: " + stringBox.get());

    // Create a Box to hold an Integer
    Box<Integer> intBox = new Box<>();    //typecasting
    intBox.set(50);
    System.out.println("Value: " + intBox.get());
  }
}
