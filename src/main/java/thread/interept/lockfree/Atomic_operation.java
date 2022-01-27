package thread.interept.lockfree;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic_operation {

// withoutt synchrise the incrementt and decrement it will give randomw value;

    //we will apply atomic operation now.
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger  integer = new AtomicInteger(100);
        InventoryCount inventoryCount = new InventoryCount(integer);

        IncrementThread incrementThread= new IncrementThread(inventoryCount);
        DecrementThread decrementThread = new DecrementThread(inventoryCount);


        incrementThread.start();
        decrementThread.start();

        incrementThread.join();
        decrementThread.join();

       System.out.println("final Item Count"+inventoryCount.getItems()) ;
    }

    public static class DecrementThread extends Thread {
        private  InventoryCount inventoryCount;

        public DecrementThread(InventoryCount inventoryCount) {
            this.inventoryCount = inventoryCount;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++)
                inventoryCount.decrement();
        }

    }


    public static class IncrementThread extends Thread {
        private  InventoryCount inventoryCount;

        public IncrementThread(InventoryCount inventoryCount) {
            this.inventoryCount = inventoryCount;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++)
                inventoryCount.increment();
        }

    }

    private  static class InventoryCount {

        AtomicInteger items;

        public InventoryCount(AtomicInteger items){
            this.items = items;
        }



            public void increment() {

            // System.out.println("increment"+Thread.currentThread().getName());
             //items++;
                items.getAndIncrement();
          }

            public  void decrement() {
              //  System.out.println("decrement"+Thread.currentThread().getName());
              items.getAndDecrement();
        }

        public AtomicInteger getItems() {
            return items;
        }
    }
}
