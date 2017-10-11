/*
Author: James Sheehan
Last Modified: 10/11/2017
Description: Driver for the RandomGenerator Service.
             This Runs each of the implemented algorithms which are the java built-in random generator,
             Linear Congruential Generation (LCG), and Blum Blum Shub.
 */

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //Run Built In Java Random Generator
        JavaBuiltInRandom javaBuiltInRandom = new JavaBuiltInRandom();
        javaBuiltInRandom.setTimeStampAsSeed();

        int numNumbers = 5;

        //This is the maximum range supported
        int lowerBound = (Integer.MIN_VALUE / 2);
        int upperBound = (Integer.MAX_VALUE / 2) - 1;

        System.out.println("Displaying " + numNumbers + " random numbers from the service using java built in generator");
        System.out.println("Bounded from " + lowerBound + " to " + upperBound + " inclusive");
        System.out.println("Seeded with Timestamp: " + javaBuiltInRandom.getSeed());
        System.out.println("Results: " + Arrays.toString(javaBuiltInRandom.generateMany(numNumbers, lowerBound, upperBound)));
        System.out.println();

        //Run LCG
        LCG lcg = new LCG();
        lcg.setTimeStampAsSeed();

        numNumbers = 5;

        //This is the maximum range supported
        lowerBound = (Integer.MIN_VALUE / 2);
        upperBound = (Integer.MAX_VALUE / 2) - 1;

        System.out.println("Displaying " + numNumbers + " random numbers from the service using LCG");
        System.out.println("Bounded from " + lowerBound + " to " + upperBound + " inclusive");
        System.out.println("Seeded with Timestamp: " + lcg.getSeed());
        System.out.println("Results: " + Arrays.toString(lcg.generateMany(numNumbers, lowerBound, upperBound)));
        System.out.println();

        //Run Blum Blum Shub
        BlumBlumShub blumBlumShub = new BlumBlumShub();
        blumBlumShub.setTimeStampAsSeed();

        numNumbers = 5;

        //This is the maximum range supported
        lowerBound = (int) Math.pow(2, 20) * -1;
        upperBound = (int) Math.pow(2, 20);

        System.out.println("Displaying " + numNumbers + " random numbers from the service using BlumBlumShub");
        System.out.println("Bounded from " + lowerBound + " to " + upperBound + " inclusive");
        System.out.println("Seeded with Timestamp: " + blumBlumShub.getSeed());
        System.out.println("Results: " + Arrays.toString(blumBlumShub.generateMany(numNumbers, lowerBound, upperBound)));
        System.out.println();
    }
}
