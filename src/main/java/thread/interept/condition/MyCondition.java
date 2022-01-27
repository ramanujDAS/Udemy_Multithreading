package thread.interept.condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MyCondition {

    public static void main(String[] args) {

        Object lock1 = new Object();
        Object lock2= new Object();
        Queue<Integer>  queue = new LinkedList<>();
        Thread thread1= new Thread(new Runnable(){
            @Override
            public void run() {

                synchronized (lock1)
                {
                    try {
                       // Thread.sleep(5000);
                        lock1.wait();
                        System.out.println("Thread "+Thread.currentThread().getName()+" executed");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread thread2= new Thread(new Runnable() {
            @Override
            public void run(){

                synchronized (lock1)
                {
                    try {

                        lock1.wait();
                        System.out.println("Thread "+Thread.currentThread().getName()+" executed");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        thread1.start();
        thread2.start();
        System.out.println(thread1.getState());
        synchronized (lock1){
          lock1.notify();
        }

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
        thread2.interrupt();
    }


}
