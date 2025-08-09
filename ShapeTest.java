//to create an abstract class Shape with abstract method calculateArea(), implement in Rectangle and Circle

//abstract class
abstract class Shape{
	abstract double calculateArea();          //abstract method
}

class Rectangle extends Shape{
	double length,breadth;    //instance variable
	
	//constructor
	 Rectangle(double length,double breadth){
		 this.length=length;
		 this.breadth=breadth;
		 
	 }
		 double calculateArea() {
			 return length*breadth;
			 
		 }
	 
}

class Circle extends Shape{
	double radius;  //instance variable
	 
	 //constructor
	Circle(double radius){
		 this.radius=radius;
	 }
		 double calculateArea() {
			 return 3.14*radius*radius;
			 
		 }
	 
}

public class ShapeTest {
      public static void main(String[] args) {
    	  
    	  //creating object to access
    	  Shape o= new Rectangle(3.2,4);
    	  System.out.println("Rectangle Area:"+ o.calculateArea() );
    	  Shape o1=new Circle(2.7);
    	  System.out.println("Circle Area:"+ o1.calculateArea());
    	  System.out.println("Name: Divya Bharathi I");
    	  System.out.println("Reg no: 2117240020096");
      }
}
