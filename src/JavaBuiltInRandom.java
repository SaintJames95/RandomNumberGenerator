/*
Author: James Sheehan
Last Modified: 10/10/2017
Description: Implementing the RandomGeneratorService with java's built in random generator
 */

import java.util.Random;

public class JavaBuiltInRandom extends RandomGeneratorService {

    Random random;

    public JavaBuiltInRandom()
    {
        this.random = new Random();
    }

    @Override
    public int generateOne(int lowerBound, int upperBound) {

        int randomInt = random.nextInt(Math.abs(lowerBound) + upperBound + 1);
        randomInt = compressToRange(randomInt, lowerBound, upperBound);
        return randomInt;
    }

    @Override
    public void nextSeed() {
        //Nothing to do
    }

    @Override
    public void setSeed(int seed) {
        this.random.setSeed(seed);
    }

    @Override
    public String toString() {
        return "Seed: " + this.seed;
    }
}
