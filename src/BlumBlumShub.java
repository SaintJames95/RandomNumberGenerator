import java.util.Date;

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
            if(Utilities.euclideanGCD(mySeed, this.m) == 1)
            {
                this.seed = mySeed;
                break;
            }
            mySeed++;
        }
    }

    @Override
    public void setTimeStampAsSeed()
    {
        Date date = new Date();
        int mySeed = date.toString().hashCode();

        while(true)
        {
            if(Utilities.euclideanGCD(mySeed, this.m) == 1)
            {
                this.seed = mySeed;
                break;
            }
            mySeed++;
        }
    }

    @Override
    public String toString() {
        return null;
    }

    //Get Modulus m that is the product of two large primes
    private void generateM()
    {
        int prime1;
        int prime2;

        //I bound m to 2^22, this can start to get slow from that point on
        prime1 = this.generatePrimeNumber((int) Math.pow(2, 20), (int) Math.pow(2, 21));
        prime2 = this.generatePrimeNumber((int) Math.pow(2, 20), (int) Math.pow(2, 21));

        this.m = prime1 * prime2;
    }

}
