package thread.interept;

import java.math.BigInteger;

public class Compute implements Runnable {

    BigInteger base;
    BigInteger pow;

    public Compute(BigInteger base, BigInteger pow) {
        this.base = base;
        this.pow = pow;
    }

    @Override
    public void run() {
        System.out.println("power result here :"+pow(base,pow));
    }

    private BigInteger pow(BigInteger base, BigInteger pow) {
        BigInteger result = BigInteger.ONE;

        for (BigInteger i = BigInteger.ZERO; i.compareTo(pow) != 0; i = i.add(BigInteger.ONE)) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("thread stopped : interuption");
                return result;
            }
            result = result.multiply(base);

        }


        return result;

    }
}
