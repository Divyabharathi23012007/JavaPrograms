
class ThreadDemo1 extends Thread {
   private Thread t;
   private String threadName;
   ThreadDemo1( String name) {
      threadName = name;
      System.out.println("Thread: " + threadName + ", " + "State: New");
   }
   public void run() {
      System.out.println("Thread: " + threadName + ", " + "State: Running");
      for(int i = 4; i > 0; i--) {
         System.out.println("Thread: " + threadName + ", " + i);
      }
      System.out.println("Thread: " + threadName + ", " + "State: Dead");
   }
   public void start () {
      System.out.println("Thread: " + threadName + ", " + "State: Start");
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}
public class LifeCycle {
   public static void main(String args[]) {
      ThreadDemo1 thread1 = new ThreadDemo1( "Thread-1");
      ThreadDemo1 thread2 = new ThreadDemo1( "Thread-2");
      thread1.start();
      thread2.start();
   }   
}
