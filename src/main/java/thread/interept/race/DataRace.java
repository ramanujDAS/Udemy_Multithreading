package thread.interept.race;

public class DataRace {


    public static void main(String[] args) throws InterruptedException {

        SharedVariable sharedVariable = new SharedVariable();

        Thread thread1= new Thread(()->{
           for(int i=0;i<Integer.MAX_VALUE;i++)
                sharedVariable.increment();

        });

        Thread thread2= new Thread(()->{
            for(int i=0;i<Integer.MAX_VALUE;i++)
                sharedVariable.checkforDataRace();

        });

       thread1.start();
       thread2.start();


    }

    public static class SharedVariable{
        private  volatile int x=0;
        private  volatile int y=0;

        public void increment()
        {
            x++;
            y++;
        }
        public void checkforDataRace()
        {
            if(y>x)System.out.println("y>x : Thread issue Detected");
        }
    }

}
