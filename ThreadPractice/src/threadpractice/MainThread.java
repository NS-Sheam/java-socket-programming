
package threadpractice;


public class MainThread {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("Current Thread: "+ t);
        // change current thread name
        t.setName("My Thread: "+ t);
        System.out.println("After change name: "+ t);
        for (int n = 10; n > 0; n--) {
            System.out.println(n);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Main Thread Interrupted");
            }
            
        }
    }
}
