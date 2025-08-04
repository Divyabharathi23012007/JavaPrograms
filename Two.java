package examples;
//single inheritance
class One {
    int a=3;
}
public class Two extends One{
	int b=5;
	public static void main(String[] args) {
		Two o= new Two();
		System.out.println(o.a * o.b);
	}

}
