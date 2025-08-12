//Name: Dhivyadharshini S
//Reg no: 2117240020088

public class Fan {
    private String brand;

    // Constructor
    public Fan(String brand) {
        this.brand = brand;
        System.out.println(brand + " fan object is created.");
    }

    // finalize() method — called by GC before object is destroyed
    @Override
    protected void finalize() throws Throwable {
        System.out.println(brand + " fan object is garbage collected.");
    }

    public static void main(String[] args) {
        // Create two fan objects
        Fan fan1 = new Fan("Crompton");
        Fan fan2 = new Fan("Orient");

        // Set objects to null to make them eligible for GC
        fan1 = null;
        fan2 = null;

        // Request garbage collection
        System.gc();

        // Sleep to allow time for GC to possibly run
        try {
            Thread.sleep(1000); // 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main method is ending.");
        System.out.println("Name: Dhivyadharshini S");
        System.out.println("Reg no: 2117240020088");
    }
}



