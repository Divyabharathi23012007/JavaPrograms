package JavaCollection;

import java.util.HashSet;

public class hashset {
  public static void main(String[] args) {
    HashSet<String> cars = new HashSet<String>();
    cars.add("Volvo");
    cars.add("BMW");
    cars.add("Ford");
    cars.add("BMW");  // Duplicate
    cars.add("Mazda");
    cars.remove("Volvo");
    System.out.println(cars);
    System.out.println(cars.size());
  }
}