package innerClass;

class Outer {
  int x = 10;

  static class InnerClass {
    int y = 5;
  }
}

public class Main2 {
  public static void main(String[] args) {
    Outer.InnerClass myInner = new Outer.InnerClass();
    System.out.println(myInner.y);
  }
}