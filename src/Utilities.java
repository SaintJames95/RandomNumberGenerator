/*
Author: James Sheehan
Last Modified: 10/10/2017
Description: Helper functions for the RandomGeneratorService that could also be used elsewhere
 */

public class Utilities {

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
