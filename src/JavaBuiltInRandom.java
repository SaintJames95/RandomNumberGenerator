/*
Author: James Sheehan
Last Modified: 10/10/2017
Description: Implementing the RandomGeneratorService with java's built in random generator
 */

import java.util.Random;

public class JavaBuiltInRandom extends RandomGeneratorService {

    @Override
    public int generateOne(int lowerBound, int upperBound) {
        Random random = new Random();
        random.setSeed(this.seed);

        int randomInt = random.nextInt(Math.abs(lowerBound) + upperBound + 1);
        randomInt = compressToRange(randomInt, lowerBound, upperBound);

        nextSeed();
        return randomInt;
    }

    @Override
    public void nextSeed() {
        this.seed += 1;
    }

    @Override
    public String toString() {
        return "Seed: " + this.seed;
    }
}
