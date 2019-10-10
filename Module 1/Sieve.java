import java.util.BitSet;
import java.lang.Math;

import static java.lang.System.nanoTime;

public class Sieve {

    public static BitSet bits; //You should work on this BitSet

    public static int numberOfPrime2(int n) {
        // This method isn't as efficient as numberOfPrime (need less changes in the BitSet)
        bits = new BitSet(n); // Creates a bit set whose initial size = n (all values set to false)
        for (int a = 0; a < n; a++) {
            bits.set(a, true);
        }

        bits.flip(0, 2); // 0 and 1 are not pair (flip from false to true)

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (bits.get(i)) {
                for (int j = 2 * i; j < n; j += i) {
                    bits.set(j, false);
                }
            }
        }
        return bits.cardinality(); // Returns the number of bits set to true in the BitSet
    }


    public static int numberOfPrime(int n) {
        // Doesn't work with the provided tests BUT it works on inginious !!!
        bits = new BitSet(n); // Creates a bit set whose initial size = n (all values set to false)
        /*for(int a=0; a<n; a++){ // Useless (already false)
            bits.set(a, false);
        }*/

        bits.flip(0, 2); // 0 and 1 are not pair (flip from false to true)

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (!bits.get(i)) {
                for (int j = 2 * i; j < n; j += i) {
                    bits.set(j, true);
                }
            }
        }
        return n - bits.cardinality(); // Returns the number of bits set to true in the BitSet
    }

    public static void main(String[] args) {
        // Run this with different "n" to see the difference in time
        int n = 10000; //Integer.MAX_VALUE - 50000

        long beginTime2 = nanoTime();
        int primes2 = numberOfPrime2(n);
        long elapsedTime2 = nanoTime() - beginTime2;
        System.out.println("There is " + primes2 + " prime numbers between 0 and " + n);
        System.out.println("\"numberOfPrime2\" took " + elapsedTime2 + " nanoseconds to execute");
        //printBitSet();

        long beginTime = nanoTime();
        int primes = numberOfPrime(n);
        long elapsedTime = nanoTime() - beginTime;
        System.out.println("\n\nThere is " + primes + " prime numbers between 0 and " + n);
        System.out.println("\"numerOfPrime\" took " + elapsedTime + " nanoseconds to execute");
        //printBitSet();

        System.out.printf("\n\"numerOfPrime\" is %.4f times faster than \"numberOfPrime2\".\n", (float) elapsedTime2 / elapsedTime);
    }

    public static void printBitSet() {
        // Prints a representation of the BitSet
        System.out.println("\nBitSet represenation :");
        for (int i = 0; i < bits.length(); i++) {
            System.out.print(bits.get(i) + "\t");
        }
        System.out.println();
        for (int i = 0; i < bits.length(); i++) {
            System.out.print(i + "\t\t");
        }
        System.out.println();
    }
}

/*
Algorithm found on Wikipedia

Input: an integer n > 1.

 Let A be an array of Boolean values, indexed by integers 2 to n,
 initially all set to true.

 for i = 2, 3, 4, ..., not exceeding √n:
   if A[i] is true:
     for j = i2, i2+i, i2+2i, i2+3i, ..., not exceeding n:
       A[j] := false.

 Output: all i such that A[i] is true.
*/