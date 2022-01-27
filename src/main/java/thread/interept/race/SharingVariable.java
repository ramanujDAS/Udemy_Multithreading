package thread.interept.race;

public class SharingVariable {


    public static void main(String[] args) throws InterruptedException {

        InventoryCount inventoryCount = new InventoryCount(0);

        IncrementThread incrementThread= new IncrementThread(inventoryCount);
        DecrementThread decrementThread = new DecrementThread(inventoryCount);


        incrementThread.start();
        decrementThread.start();
        incrementThread.join();
        decrementThread.join();

       System.out.println("final Item Count"+inventoryCount.items) ;
    }

    public static class DecrementThread extends Thread {
        private final InventoryCount inventoryCount;

        public DecrementThread(InventoryCount inventoryCount) {
            this.inventoryCount = inventoryCount;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++)
                inventoryCount.decrement();
        }

    }


    public static class IncrementThread extends Thread {
        private final InventoryCount inventoryCount;

        public IncrementThread(InventoryCount inventoryCount) {
            this.inventoryCount = inventoryCount;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++)
                inventoryCount.increment();
        }

    }

    private static class InventoryCount {

        private int items;

        public InventoryCount( int items){
            this.items = items;
        }



            public synchronized void increment() {

             System.out.println("increment"+Thread.currentThread().getName());
            items++;
          }

            public synchronized void decrement() {
                System.out.println("decrement"+Thread.currentThread().getName());
            items--;
        }

    }
}
