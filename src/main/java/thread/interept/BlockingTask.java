package thread.interept;

import java.math.BigInteger;
import java.util.ConcurrentModificationException;
import java.util.concurrent.ConcurrentHashMap;

class Mythread1 {

    public static void main(String[] args) throws InterruptedException {


        Thread thread  = new Thread(new BlockingTask() );

        Thread thread1 = new Thread(new Compute(new BigInteger("7"),new BigInteger("10000000")));
         thread1.setDaemon(true);
        thread1.start();
        thread1.join();


    }
}


public class BlockingTask implements Runnable{


    @Override
    public void run() {

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            System.out.print("interrupt occured"+e.getMessage());
        }

    }
}
