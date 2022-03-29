package com.Threading;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ApproachTwo extends Thread{
    private volatile BigInteger sum = BigInteger.ZERO;
    private volatile Integer threadNum;
    private volatile BigInteger interval;
    private volatile Integer numOfThreads;
    private volatile BigInteger lower;
    private volatile BigInteger upper;

    public ApproachTwo(Integer threadNum, BigInteger interval, Integer numOfThreads){
        this.threadNum= threadNum;
        this.interval= interval;
        this.numOfThreads= numOfThreads;
    }

    public void run()
    {
        Instant inst1 = Instant.now();
        if(threadNum<numOfThreads-1){
            lower = interval.multiply(BigInteger.valueOf(threadNum)).add(BigInteger.ONE);
            upper = BigInteger.valueOf(threadNum).add(BigInteger.ONE).multiply(interval);
        }
        else{
            lower = interval.multiply(BigInteger.valueOf(threadNum)).add(BigInteger.ONE);
            upper = BigInteger.valueOf(500000000);
        }
        for(BigInteger i=lower;i.compareTo(upper)<=0; i=i.add(BigInteger.ONE)){
            sum = sum.add(i);
        }
        Instant inst2 = Instant.now();
        System.out.println("Running time of thread "+threadNum+" is "+Duration.between(inst1,inst2));
    }
    public BigInteger getValue(){
        return sum;
    }

    public static void main(String args[]) throws InterruptedException {
        Instant inst1 = Instant.now();
        BigInteger totalSum = BigInteger.ZERO;
        BigInteger upperLimit = BigInteger.valueOf(500000000);
        Integer numberOfThreads = 10;
        BigInteger interval = upperLimit.divide(BigInteger.valueOf(numberOfThreads));

        List<ApproachTwo> threadList = new ArrayList<>();

        for(int i=0;i<numberOfThreads;i++){
            ApproachTwo thread = new ApproachTwo(i,interval,numberOfThreads);
            thread.start();
            threadList.add(thread);
        }

        for(int j=0 ; j<numberOfThreads; j++){
            threadList.get(j).join();
            totalSum= totalSum.add(threadList.get(j).getValue());
        }
        Instant inst2 = Instant.now();
        System.out.println(totalSum);
        System.out.println("Parallel Time: "+ Duration.between(inst1,inst2));
    }
}

