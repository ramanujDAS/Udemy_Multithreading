package thread.interept.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//syncronised block is also reenttrant llock
public class CheckReentrant {

    public static void main(String[] args) {


        Thread thread= new Thread(new MyReentrantlock());
        thread.start();

    }


    public static class MyReentrantlock implements Runnable
    {
        Lock lock = new ReentrantLock(true);

        public synchronized void doSomething(){
            //lock.tryLock();

                System.out.println("lock acquired");
                something();

          // lock.unlock();

    }
       public synchronized void something(){
            //lock.tryLock();
           {
               System.out.println("again lock acquired");
               doSomething();
              // lock.unlock();
           }
    }

        @Override
        public void run() {
           doSomething();

        }
    }
}
