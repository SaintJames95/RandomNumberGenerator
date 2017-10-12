/*
Author: James Sheehan
Last Modified: 10/11/2017
Description: Driver for the RandomGenerator Service.
             This runs each of the implemented algorithms which are the java built-in random generator,
             Linear Congruential Generation (LCG), and Blum Blum Shub.
             These test cases all use the current Date as the seed, so they will produce different results each time
             Blum Blum Shub will always produce different results on different runs because m is generated on construction

             The service expects integer bounds (negative is okay) and generates integers. Doubles are not allowed.

             The maximum bounds are as follows (these are abo=sol:
                Java built-in: maximum range supported is (Integer.MIN_VALUE / 2) to (Integer.MAX_VALUE / 2) - 1
                LCG: maximum range supported is (Integer.MIN_VALUE / 2) to (Integer.MAX_VALUE / 2) - 1
                Blum Blum Shub: The maximum range supported is "(int) Math.pow(2, 19) * -1" to "(int) Math.pow(2, 19)"
 */

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //Run Built In Java Random Generator
        JavaBuiltInRandom javaBuiltInRandom = new JavaBuiltInRandom();
        javaBuiltInRandom.setTimeStampAsSeed();

        int numNumbers = 5;
        int lowerBound = -50000;
        int upperBound = 50000;

        System.out.println("Displaying " + numNumbers + " random numbers from the service using java built in generator");
        System.out.println("Bounded from " + lowerBound + " to " + upperBound + " inclusive");
        System.out.println("Seeded with Timestamp: " + javaBuiltInRandom.getSeed());
        System.out.println("Results: " + Arrays.toString(javaBuiltInRandom.generateMany(numNumbers, lowerBound, upperBound)));
        System.out.println();

        //Run LCG
        LCG lcg = new LCG();
        lcg.setTimeStampAsSeed();

        numNumbers = 5;
        lowerBound = -100000;
        upperBound = 50000;

        System.out.println("Displaying " + numNumbers + " random numbers from the service using LCG");
        System.out.println("Bounded from " + lowerBound + " to " + upperBound + " inclusive");
        System.out.println("Seeded with Timestamp: " + lcg.getSeed());
        System.out.println("Results: " + Arrays.toString(lcg.generateMany(numNumbers, lowerBound, upperBound)));
        System.out.println();

        //Run Blum Blum Shub
        BlumBlumShub blumBlumShub = new BlumBlumShub();
        blumBlumShub.setTimeStampAsSeed();

        numNumbers = 5;
        lowerBound = -25000;
        upperBound = 100000;

        System.out.println("Displaying " + numNumbers + " random numbers from the service using BlumBlumShub");
        System.out.println("Bounded from " + lowerBound + " to " + upperBound + " inclusive");
        System.out.println("Seeded with Timestamp: " + blumBlumShub.getSeed());
        System.out.println("Results: " + Arrays.toString(blumBlumShub.generateMany(numNumbers, lowerBound, upperBound)));
        System.out.println();
    }
}
