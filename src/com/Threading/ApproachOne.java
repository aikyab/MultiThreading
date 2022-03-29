package com.Threading;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

public class ApproachOne {

    public static void main(String[] args) {
        Instant inst1 = Instant.now();
        BigInteger A;
        BigInteger B = new BigInteger("0");

        A  = new BigInteger("500000000");
        for (BigInteger bi = A;
             bi.compareTo(BigInteger.ZERO) > 0;
             bi = bi.subtract(BigInteger.ONE)) {
            B = B.add(bi);
        }
        Instant inst2 = Instant.now();
        System.out.println(B);
        System.out.println("Serial Time is "+ Duration.between(inst1,inst2));
    }
    }

