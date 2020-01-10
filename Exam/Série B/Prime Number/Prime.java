import java.util.stream.IntStream;

public class Prime {

    // Suppose x has 2 factors (n1 and n2) and both are bigger than sqrt(x). Then n1*n2 is bigger than x, which is absurd!
    // So x has at least one factor smaller than sqrt(x) if it isn’t prime. That’s what we want to find!
    public static boolean isPrime(Integer x) {
        if(x < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0)
                return false;
        }
        return true;
    }

    public static IntStream infiniteStream(Integer from) {
        return IntStream.iterate(from, i -> i += 1);
    }

    public static IntStream getPrimeStream(Integer from) {
        return infiniteStream(from).filter(Prime::isPrime);
    }

    public static IntStream getPrimeStreamGap(Integer from) {
        return getPrimeStream(from).map(i -> getPrimeStream(i+1).limit(1).sum()-i);
    }
}
