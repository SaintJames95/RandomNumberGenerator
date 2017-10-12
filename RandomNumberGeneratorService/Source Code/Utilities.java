/*
Author: James Sheehan
Last Modified: 10/10/2017
Description: Helper functions used in the RandomGeneratorService that could also be useful elsewhere
             These are all static functions for doing some computational work
 */

import java.util.Random;

public class Utilities {

    //For quickly computing "x^c mod n" for large values
    public static long squareAndMultiply(long x, long c, long n)
    {
        long base = x % n;
        long result = 1;

        String binaryStringOfC = Long.toBinaryString(c);

        if((binaryStringOfC.charAt(binaryStringOfC.length() - 1)) == '1')
        {
            result = result * base;
        }
        binaryStringOfC = binaryStringOfC.substring(0, (binaryStringOfC.length() - 1));

        while(!binaryStringOfC.equals(""))
        {
            base = ((long) Math.pow(base, 2)) % n;

            if (binaryStringOfC.charAt(binaryStringOfC.length() - 1) == '1')
            {
                result = result * base;
            }

            binaryStringOfC = binaryStringOfC.substring(0, (binaryStringOfC.length() - 1));
        }

        result = result % n;
        return result;
    }

    //For checking if number is prime
    public static boolean millerRabin(int p)
    {
        // If p is even definitely not prime, and don't want 1 or 2
        if (p % 2 == 0 || p == 1)
        {
            return false;
        }

        int m = (p - 1);
        int n = m;
        int k = 0;

        //Divide m until odd and count
        while(m % 2 == 0)
        {
            m = m / 2;
            k += 1;
        }

        Random r = new Random();

        //Get random number between 2 and n - 1 inclusive
        int a = r.nextInt((n-2)) + 2;
        long b = Utilities.squareAndMultiply(a,m,p);

        if(b == 1)
        {
            return true;
        }
        else
        {
            for(int i = 0; i < k; i++)
            {
                if (b == -1 || b == n)
                {
                    return true;
                }
                else
                {
                    b = Utilities.squareAndMultiply(b,2, p);
                }
            }
        }
        return false;
    }

    //Checking if two integers are co-prime
    public static boolean isCoprime(int x, int y)
    {
        if(Utilities.euclideanGCD(x, y) == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static int euclideanGCD(int x, int y)
    {
        if (y == 0)
        {
            return x;
        }
        else
        {
            return euclideanGCD(y, x % y);
        }
    }

    //THIS FUNCTION WAS NOT WRITTEN BY ME
    //Source: https://stackoverflow.com/questions/9117793/bitwise-most-significant-set-
    //Author: Michael McGowan
    //Desc: Gets the most significant bit of the integer passed in
    public static int getMostSigBitPos(int number) {
        int mask = 1 << 31;
        for (int bitIndex = 31; bitIndex >= 0; bitIndex--) {
            if ((number & mask) != 0) {
                return bitIndex;
            }
            mask >>>= 1;
        }
        return 0;
    }
}
