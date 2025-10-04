package JavaCollection;


import java.util.ArrayList;
public class Arraylist {
  public static void main(String[] args) {
    ArrayList<String> cars = new ArrayList<String>();
    cars.add("Volvo");
    cars.add("BMW");
    cars.add("Ford");
    cars.add("Mazda");
    cars.add(0, "Mazda");
    System.out.println(cars);
    System.out.println(cars.get(0)); 
    
  }
}
