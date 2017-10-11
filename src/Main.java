/*
Author: James Sheehan
Last Modified: 10/10/2017
Description: Driver for the RandomGenerator Service. Runs each of the implemented algorithms
 */

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int numNumbers = 2;
        int lowerBound = -2500;
        int upperBound = 5000;

        //Run Built In Java Random Generator
        JavaBuiltInRandom javaBuiltInRandom = new JavaBuiltInRandom();
        javaBuiltInRandom.setTimeStampAsSeed();

        System.out.println("Displaying " + numNumbers + " random numbers from the service using java built in generator");
        System.out.println("Bounded from " + lowerBound + " to " + upperBound + " inclusive");
        System.out.println("Seeded with Timestamp: " + javaBuiltInRandom.getSeed());
        System.out.println("Results: " + Arrays.toString(javaBuiltInRandom.generateMany(numNumbers, lowerBound, upperBound)));
        System.out.println();

        numNumbers = 3;
        lowerBound = -1000;
        upperBound = 10000;

        //Run LCG
        LCG lcg = new LCG();
        lcg.setTimeStampAsSeed();

        System.out.println("Displaying " + numNumbers + " random numbers from the service using LCG");
        System.out.println("Bounded from " + lowerBound + " to " + upperBound + " inclusive");
        System.out.println("Seeded with Timestamp: " + lcg.getSeed());
        System.out.println("Results: " + Arrays.toString(lcg.generateMany(numNumbers, lowerBound, upperBound)));
        System.out.println();
    }
}
