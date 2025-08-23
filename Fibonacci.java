//Name: Divya Bharathi I
//Reg no: 2117240020096
//To print the Fibonacci Sequence up to the nth term using recursion

import java.util.*;

//defining recursive function
public class Fibonacci {
	public int fib(int n){
		 if (n==0) 
			 return 0;
		 else if (n==1) 
			 return 1;
		 else 
			 return fib(n-1)+fib(n-2);
		 
		 
	}
	public static void main(String[] args){
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter a number: ");
		int n=sc.nextInt();
		Fibonacci o=new Fibonacci();

		if(n<=0) {
			System.out.println("Enter a valid positive integer.");
		}
		
		//looping from 0 to n and print each fib(i)
		for (int i=0;i<n;i++) {
			System.out.print(o.fib(i)+" ");
			
		}
		System.out.println();
		System.out.println("Name: Divya Bharathi I");
		System.out.println("Reg no: 2117240020096");
		
			
		
	}

}

