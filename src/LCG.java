/*
Author: James Sheehan
Last Modified: 10/10/2017
Description: Implementation of the Linear Congruential Generator using the RandomGeneratorService
             The maximum range supported is (Integer.MIN_VALUE / 2) to (Integer.MAX_VALUE / 2) - 1
 */

public class LCG extends RandomGeneratorService {

    private long m;
    private long a;
    private long c;

    public LCG()
    {
        this.m = (long) (Math.pow(2, 32));
        this.a = 1664525;
        this.c = 1013904223;
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

    @Override
    public void nextSeed()
    {
        this.seed = (int) ((a*this.seed + c) % m);
    }

    @Override
    public int generateOne(int lowerBound, int upperBound)
    {
        if(upperBound < lowerBound)
        {
            throw new IllegalArgumentException("Upper bound MUST be greater than or equal to the lower bound");
        }

        nextSeed();
        return compressToRange(this.seed, lowerBound, upperBound);
    }

    @Override
    public String toString() {
        return  "M: " + this.m + "\n" +
                "A: " + this.a + "\n" +
                "C: " + this.c + "\n" +
                "Seed: " + this.seed + "\n";
    }
}
