/**
 * This example demonstrates
 * How to add two numbers
 * using Java Generics
 *
 */
public class AddNum {

    public static void main(String[] args) {

        Add<Integer> addIntegers = new AddIntegers();
        System.out.println(addIntegers.add(1,2));

        Add<Double> addDoubles = new AddDoubles();
        System.out.println(addDoubles.add(1d,2d));
    }
}


/**
 * Add an interface that accepts a
 * generic type T with bounded
 *  Type Number
 */
interface Add<T extends Number> {
    T add(T number1, T number2);
}


/**
 * Implementation of Add as
 * generic Integer type
 * argument
 */
class AddIntegers implements Add<Integer> {

    public Integer add(Integer no1, Integer no2) {
        return no1 + no2;
    }
}





/**
 * Implementation of Add as
 * generic Double type
 * argument
 */
class AddDoubles implements Add<Double> {

    public Double add(Double no1, Double no2) {
        return no1 + no2;
    }
}