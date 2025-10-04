package JavaCollection;

import java.util.LinkedList;

public class linkedlist {
  public static void main(String[] args) {
    LinkedList<String> cars = new LinkedList<String>();
    cars.add("Volvo");
    cars.add("BMW");
    cars.add("Ford");
    cars.addFirst("Mazda");
    cars.addLast("Mazda");
    cars.removeFirst();
    cars.removeLast();
    System.out.println(cars);
    System.out.println(cars.getFirst());
    System.out.println(cars.getLast());
  
  }
}

