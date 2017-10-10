public class LCG {

    private long m;
    private long a;
    private long c;
    private int seed;

    public LCG()
    {
        this.m = (long) (Math.pow(2, 32));
        this.a = 1664525;
        this.c = 1013904223;
        this.seed = 1;
    }

    public LCG(long m, long a, long c, int seed)
    {
        this.m = m;
        this.a = a;
        this.c = c;
        this.seed = seed;
    }

    public long getM() {
        return m;
    }

    public void setM(long m) {
        this.m = m;
    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getC() {
        return c;
    }

    public void setC(long c) {
        this.c = c;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;

        //Reseed this once so that setting the seed to numbers close in value will not give a similar first result
        nextSeed();
    }

    public int generateOne(int lowerBound, int upperBound)
    {
        if(upperBound < lowerBound)
        {
            throw new IllegalArgumentException("Upper bound MUST be greater than or equal to the lower bound");
        }

        //Get the next seed
        nextSeed();
        int randomInt = this.seed;

        randomInt = compressToRange(randomInt, lowerBound, upperBound);

        //RandomInt always positive, need to map this to the new range
        if(randomInt > upperBound) {
            randomInt = randomInt * -1 + upperBound;
        }

        return randomInt;
    }

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


    private void nextSeed()
    {
        this.seed = (int) ((a*this.seed + c) % m);
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
