package thread.interept.lockfree;

import java.util.concurrent.atomic.AtomicInteger;

public class LockFree_Atomic {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(10);

        System.out.println(atomicInteger);

    }
}
