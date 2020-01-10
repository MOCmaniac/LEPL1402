import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void testIsPrime() {
        int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                73, 79, 83, 89, 97};
        for (int i : primes) {
            assertTrue(Prime.isPrime(i));
        }

    }

    @Test
    public void testIsNotPrime() {
        int[] notPrime = new int[]{0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28,
                30, 32, 33, 34, 35, 36, 38, 39, 40, 42, 44, 45, 46, 48, 49, 50, 51, 52, 54, 55, 56, 57, 58, 60};
        for (int i : notPrime) {
            assertFalse(Prime.isPrime(i));
        }

    }

    @Test
    public void testIsPrimeBigPrime() {
        int[] primes = new int[]{7001, 7013, 7019, 7027, 7039, 7043, 7057, 7069, 7079, 7103,
                7109, 7121, 7127, 7129, 7151, 7159, 7177, 7187, 7193, 7207};
        for (int i : primes) {
            assertTrue(Prime.isPrime(i));
        }

    }

    @Test
    public void testInfiniteStream() {
        int[] result = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(result, Prime.infiniteStream(0).limit(10).toArray());
    }

    @Test
    public void testInfiniteStreamFrom15() {
        int[] result = new int[]{15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        assertArrayEquals(result, Prime.infiniteStream(15).limit(10).toArray());
    }

    @Test
    public void testInfiniteStreamBigNumber() {
        int[] result = new int[]{2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009};
        assertArrayEquals(result, Prime.infiniteStream(0).skip(2000).limit(10).toArray());
    }

    @Test
    public void testPrimeStream() {
        int[] result = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                73, 79, 83, 89, 97};
        assertArrayEquals(result, Prime.getPrimeStream(0).limit(25).toArray());
    }

    @Test
    public void testPrimeStreamFrom30() {
        int[] result = new int[]{31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        assertArrayEquals(result, Prime.getPrimeStream(30).limit(15).toArray());
    }

    @Test
    public void testPrimeStreamBigPrime() {
        int[] result = new int[]{2003, 2011, 2017, 2027, 2029, 2039, 2053, 2063, 2069, 2081};
        assertArrayEquals(result, Prime.getPrimeStream(2000).limit(10).toArray());
    }

    @Test
    public void testPrimeGap() {
        int[] gaps = new int[]{1, 2, 2, 4, 2, 4, 2, 4, 6, 2};
        assertArrayEquals(gaps, Prime.getPrimeStreamGap(0).limit(10).toArray());
    }

}
