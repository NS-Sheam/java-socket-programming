
package threadpractice;

import java.util.logging.Level;
import java.util.logging.Logger;

class NewThread implements Runnable{

    Thread t;
    int threadNo;
    
    NewThread(int threadNo){
        t = new Thread(this, "Runnable Thread");
        this.threadNo = threadNo;
        t.start();
        
    }
    
    @Override
    public void run() {
        for (int n = 10; n > 0; n--) {
            System.out.println(threadNo+ "-Child Thread: "+ n);
           try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(threadNo+ "-Child Thread Interrupted");
            }
            
        }
        System.out.println("Exiting the Child Thread...");
    }

}
public class RunnableThread {
    public static void main(String[] args) {
        System.out.println("Main Thread Started...");
        NewThread obj1 = new NewThread(1);
        NewThread obj2 = new NewThread(2);
        System.out.println("Thread 1 is alive: "+ obj1.t.isAlive());
        System.out.println("Thread 2 is alive: "+ obj2.t.isAlive());
        
        try {
            obj1.t.join();
            obj2.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread 1 is alive: "+ obj1.t.isAlive());
        System.out.println("Thread 2 is alive: "+ obj2.t.isAlive());
        System.out.println("Main Thread exited...");
    }
}
