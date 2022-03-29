package com.Threading;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main extends Thread{
    private volatile BigInteger sum = BigInteger.ZERO;
    private String name;
    private BigInteger lowerLimit;
    private BigInteger upperLimit;

    public Main(String name, BigInteger lowerLimit, BigInteger upperLimit){
        this.name = name;
        this.lowerLimit= lowerLimit;
        this.upperLimit= upperLimit;
    }

    public void run()
    {
        for(BigInteger i=lowerLimit;i.compareTo(upperLimit)<=0; i=i.add(BigInteger.ONE)){
            sum = sum.add(i);
        }
    }
    public BigInteger getValue(){
        return sum;
    }

    public static void main(String args[]) throws InterruptedException {
        Instant inst1 = Instant.now();
        BigInteger totalSum = BigInteger.ZERO;
        BigInteger upperLimit= BigInteger.valueOf(500000000);

        int numberOfThreads= 10;

        BigInteger interval = upperLimit.divide(BigInteger.valueOf(numberOfThreads));

        BigInteger i = BigInteger.ZERO;

        List<Main> threadList = new ArrayList<>();

        while(i.compareTo(upperLimit)<=0){
            BigInteger upper;
            if(i.add(interval).compareTo(upperLimit)>0){
                upper = upperLimit;
            } else {
                upper = i.add(interval);
            }
            Main thread = new Main("thread1", i, upper);
            thread.start();
            threadList.add(thread);

            i = i.add(interval).add(BigInteger.ONE);

        }
        for(int j=0 ; j<numberOfThreads; j++){
            threadList.get(j).join();
            totalSum= totalSum.add(threadList.get(j).getValue());
        }
        Instant inst2 = Instant.now();
        System.out.println(totalSum);
        System.out.println("Parallel Time is "+Duration.between(inst1,inst2));
    }
}
