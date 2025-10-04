// Demonstrating synchronized keyword with multiplication table
class Table {
    // synchronized method ensures one thread at a time
	synchronized void printTable(int n) {
        System.out.println("Multiplication Table of " + n);
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
            try {
                Thread.sleep(200); // just to slow it down
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        System.out.println("-----------------------");
    }
}

// Thread class
class MyThread extends Thread {
    Table t;
    int number;

    MyThread(Table t, int number) {
        this.t = t;
        this.number = number;
    }

    public void run() {
        t.printTable(number);
    }
}

public class Synchronization {
    public static void main(String[] args) {
        Table obj = new Table(); // only one object

        // Create threads for different tables
        MyThread t1 = new MyThread(obj, 2);
        MyThread t2 = new MyThread(obj, 3);
        MyThread t3 = new MyThread(obj, 5);

        // Start threads
        t1.start();
        t2.start();
        t3.start();
    }
}