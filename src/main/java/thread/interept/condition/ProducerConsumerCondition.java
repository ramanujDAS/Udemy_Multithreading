package thread.interept.condition;

import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class ProducerConsumerCondition {

    public static void main(String[] args) throws InterruptedException {

      // Queue<Integer> queue = new LinkedList<>();
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        Producer_Consumer producer_consumer = new Producer_Consumer(queue);

       Thread producer[] = new Thread[10];
       Thread consumer[] = new Thread[10];

        Random random = new Random();

       for(int i=0;i<10;i++)
       {
           MyProducer myProducer = new MyProducer(producer_consumer,random.nextInt(100));
           producer[i]= new Thread(myProducer);
           MyConsumer myConsumer= new MyConsumer(producer_consumer);
           consumer[i]=new Thread(myConsumer);
           producer[i].start();
           consumer[i].start();

       }

    }

    public static class  Producer_Consumer
    {
        //Semaphore full = new Semaphore(0);
       // Semaphore empty = new Semaphore(3);

        Object full = new Object();
        Object empty = new Object();

       SynchronousQueue<Integer> queue;
        public Producer_Consumer(SynchronousQueue q)
        {
            this.queue=q;
        }

        public void produce(Integer e) throws InterruptedException {
            //empty.acquire();
           synchronized (this) {
               queue.add(e);
               full.notify();
           }
           // full.release();
        }

        public Integer consume() throws InterruptedException {
            Integer released;
            //full.acquire();
            synchronized (this) {
                full.wait();
                released = queue.poll();
            }
           // empty.release();
            return released;
        }



    }


    private static class MyProducer implements Runnable {
        private final Producer_Consumer producer_consumer;
        Integer insertedVal;
        public MyProducer(Producer_Consumer producer_consumer,Integer e) {
            this.producer_consumer = producer_consumer;
            this.insertedVal=e;
        }

        @Override
        public void run() {
            try {
                producer_consumer.produce(insertedVal);

                System.out.println("Data inserted "+insertedVal+" by"+Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyConsumer implements Runnable {
        private final Producer_Consumer producer_consumer;

        public MyConsumer(Producer_Consumer producer_consumer) {
            this.producer_consumer = producer_consumer;
        }

        @Override
        public void run() {
            try {
                System.out.println("consumed int  "+ producer_consumer.consume()+" by"+Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
