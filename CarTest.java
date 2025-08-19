//Name: Divya Bharathi I
//Reg no: 2117240020096
//to define a class Car with attributes like model, color and methods to start and stop the car


class Car {
	String model;
	String color;
	
	Car(String model, String color){
		this.model=model;
		this.color=color;
	}
	
	public void start() {
		if(model==null || model.isEmpty()) {
			System.out.println("Car started (but model not shown)");
		}
		else {
			System.out.println("Car started");
		}
	}
	
	public void stop() {
		System.out.println("Car stopped");
	}
	
}

public class CarTest{
	public static void main(String[] args) {
		Car car1=new Car("Tesla","Red");
		car1.start();
		System.out.println();
		
		Car car2=new Car("BMW","Black");
		car2.start();
		System.out.println();
		
		Car car3=new Car("","Pink");
		car3.start();
		System.out.println();
		
		Car car4=new Car("Ford","Grey");
		System.out.println("Start method not called");
		System.out.println();
		
		Car car5=new Car("Tesla","White");
		car5.stop();
		System.out.println();
		System.out.println("Name: Divya Bharathi I");
		System.out.println("Reg no: 2117240020096");	
	}
}
