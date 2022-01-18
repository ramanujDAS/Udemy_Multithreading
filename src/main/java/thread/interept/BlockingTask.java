package thread.interept;

class Mythread1 {

    public static void main(String[] args) {


        Thread thread  = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from "+ Thread.currentThread().getName());
            }
        });


        thread.start();

        thread.interrupt();

    }
}


public class BlockingTask implements Runnable{


    @Override
    public void run() {

    }
}