/*
Author: James Sheehan
Last Modified: 10/10/2017
Description: RandomGeneratorService Abstract Class
 */

import java.util.Date;

public abstract class RandomGeneratorService {

    protected int seed = 1;

    public abstract int generateOne(int lowerBound, int upperBound);    //Main algorithm
    public abstract void nextSeed();                                    //How the next seed is determined
    public abstract String toString();

    public void setSeed(int seed){
        this.seed = seed;

        //Reseed this twice so that setting the seed to numbers close in value will not give the same first result
        //My implementation of LCG has this problem, but reseeding in general shouldn't hurt any other algorithm
        nextSeed();
        nextSeed();
    }

    public int getSeed(){
        return this.seed;
    }

    //Sets the seed by hashing the current date to an integer
    public void setTimeStampAsSeed(){
        Date date = new Date();
        this.seed = date.toString().hashCode();
    }

    //Calls "generateOne" the specified number of times and returns that many random numbers
    public int[] generateMany(int numNumbers, int lowerBound, int upperBound)
    {
        if(upperBound < lowerBound)
        {
            throw new IllegalArgumentException("Upper bound MUST be greater than or equal to the lower bound!");
        }

        int[] results = new int[numNumbers];

        for(int i = 0; i < numNumbers; i++)
        {
            results[i] = generateOne(lowerBound, upperBound);
        }

        return results;
    }

    //Given a random integer and a bound, ensures that the number fits in the range
    //If the number exceeds in bit length the number of bits needed to represent all numbers in the range,
    //the most significant bits are used
    protected int compressToRange(int randomInt, int lowerBound, int upperBound)
    {
        int result = randomInt;
        int absoluteRange = Math.abs(lowerBound) + upperBound + 1;  //For calculating number of bits needed for the range specified

        //Shift by appropriate number of bits given the specified bounds
        int bitsMax = 0;
        int bitsHave = Utilities.getMostSigBitPos(randomInt);

        //Get the number of bits needed at maximum
        do
        {
            bitsMax++;
        } while (Math.pow(2, bitsMax) < absoluteRange);

        //Compare bits we have with what the maximum needed; shift to most significant
        if(bitsHave > bitsMax)
        {
            int shiftBy = (bitsHave - bitsMax);
            result = result >>> shiftBy;
        }

        //This may still have a larger range than we want
        //Need to mod it down
        result %= absoluteRange;

        //Result is always positive, need to map this to the new range
        if(result > upperBound) {
            result = result * -1 + upperBound;
        }

        return result;
    }
}
