package com.Threading;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ApproachThree extends Thread{
    private volatile BigInteger sum = BigInteger.ZERO;
    private volatile Integer threadNum;
    private volatile Integer numOfThreads;
    private volatile BigInteger upper;

    public ApproachThree(Integer threadNum, BigInteger upper, Integer numOfThreads){
        this.threadNum= threadNum;
        this.upper = upper;
        this.numOfThreads= numOfThreads;
    }

    public void run()
    {
        Instant inst1 = Instant.now();
        for(BigInteger i=BigInteger.valueOf(threadNum);i.compareTo(upper)<=0; i=i.add(BigInteger.valueOf(numOfThreads))){
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
//        BigInteger interval = upperLimit.divide(BigInteger.valueOf(numberOfThreads));

        List<ApproachThree> threadList = new ArrayList<>();

        for(int i=1;i<=numberOfThreads;i++){
            ApproachThree thread = new ApproachThree(i, upperLimit, numberOfThreads);
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


