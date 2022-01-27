package thread.interept.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
// re-entrant lock doesn't wait to get the lock , if it does not get the lock it moves further.
public class MyReentrant {
    public static void main(String[] args) {

     TestLock testLock = new TestLock();
     Thread thread1= new Thread(testLock);
     Thread thread2 = new Thread(testLock);
     thread1.start();
     thread2.start();


    }


    public static class TestLock implements Runnable{

         ReentrantLock reentrant1 = new ReentrantLock();
         ReentrantLock reentrant2 = new ReentrantLock();


        @Override
        public void run(){

            System.out.println(""+Thread.currentThread().getState());

           if(reentrant1.tryLock())
            {
                    reentrant1.lock();

                System.out.println("reentrant1 locked" +Thread.currentThread().getName()+"count"+reentrant1.getQueueLength());
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
               reentrant1.unlock();
            }

           if(reentrant2.tryLock())
            {
                 reentrant2.lock();
                 System.out.println("reentrant2 locked"+Thread.currentThread().getName());
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
                reentrant2.unlock();
            }

        }
    }
}
