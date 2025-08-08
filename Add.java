package examples;

//parameterised constructor


public class Add {

	int a,b;
	double c,d;
	Add(int x,int y){
		a=x;
		b=y;
	}
	
	Add(double e, double f){
		c=e;
		d=f;
	}
	
	public static void main(String[] args) {
		Add o= new Add(3,5);
		Add o1= new Add(2,3.6);
		System.out.println(o.a+o.b);
		System.out.println(o1.c+o1.d);
		
	}

}