/*
Author: James Sheehan
Last Modified: 10/11/2017
Description: Implementing the RandomGeneratorService with Blum Blum Shub
             This implementation generates a value M upon construction
             By calculating two large primes and taking the product
             The initial seed must be coPrime with m for this to be effective
             The maximum range supported is "(int) Math.pow(2, 19) * -1" to "(int) Math.pow(2, 19)"
 */

public class BlumBlumShub extends RandomGeneratorService {

    private int m;

    public BlumBlumShub()
    {
        generateM();
        setSeed(1000);
    }

    @Override
    public int generateOne(int lowerBound, int upperBound) {
        if(upperBound < lowerBound)
        {
            throw new IllegalArgumentException("Upper bound MUST be greater than or equal to the lower bound");
        }

        nextSeed();
        return compressToRange(this.seed, lowerBound, upperBound);
    }

    @Override
    public void nextSeed() {
        this.seed = (int)(((long) Math.pow(this.seed, 2)) % m);
    }

    @Override
    public void setSeed(int seed) {
        int mySeed = seed;
        while(true)
        {
            if(Utilities.isCoprime(mySeed, this.m))
            {
                this.seed = mySeed;
                break;
            }
            mySeed++;
        }
    }

    @Override
    public String toString() {
        return  "M: " + this.m + "\n" +
                "Seed: " + this.seed + "\n";
    }

    //Get Modulus m that is the product of two large primes
    private void generateM()
    {
        int prime1;
        int prime2;

        //I bound m to 2^21, this can start to get slow from that point on
        //Uses Miller Rabin for primality testing
        prime1 = this.generatePrimeNumber((int) Math.pow(2, 19), (int) Math.pow(2, 20));
        prime2 = this.generatePrimeNumber((int) Math.pow(2, 19), (int) Math.pow(2, 20));

        this.m = prime1 * prime2;
    }

}
