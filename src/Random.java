public abstract class Random {

    private int seed = 1;

    public abstract int generateOne(int lowerBound, int upperBound);
    public abstract void setSeed(int seed);
    public abstract void nextSeed();

    public int[] generateMany(int numNumbers, int lowerBound, int upperBound)
    {
        if(upperBound < lowerBound)
        {
            throw new IllegalArgumentException("Upper bound MUST be greater than or equal to the lower bound");
        }

        int[] results = new int[numNumbers];

        for(int i = 0; i < numNumbers; i++)
        {
            results[i] = generateOne(lowerBound, upperBound);
        }

        return results;
    }

    private int compressToRange(int randomInt, int lowerBound, int upperBound)
    {
        //For calculating number of bits needed for the range specified
        int absoluteRange = Math.abs(lowerBound) + upperBound + 1;

        //Shift by appropriate number of bits given the specified bounds
        int exp = 0;
        int shiftBy = 32;
        do
        {
            shiftBy--;
            exp++;
        } while (Math.pow(2, exp) < absoluteRange);

        int result = randomInt >>> shiftBy;

        //This may still have a larger range than we want
        //Need to mod it down
        result %= absoluteRange;

        return result;
    }
}
