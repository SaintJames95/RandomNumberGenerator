/*
Author: James Sheehan
Last Modified: 10/10/2017
Description: Implementing the RandomGeneratorService with java's built in random generator
             The maximum range supported is (Integer.MIN_VALUE / 2) to (Integer.MAX_VALUE / 2) - 1
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
        //Nothing to do, handled automatically in generate function
    }

    @Override
    public String toString() {
        return "Seed: " + this.seed;
    }
}
