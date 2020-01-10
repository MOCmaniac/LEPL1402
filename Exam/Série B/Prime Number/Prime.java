import java.util.stream.IntStream;

public class Prime {

    // Suppose x has 2 factors (n1 and n2) and both are bigger than sqrt(x). Then n1*n2 is bigger than x, which is absurd!
    // So x has at least one factor smaller than sqrt(x) if it isn’t prime. That’s what we want to find!
    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(1,(int) Math.sqrt(number)).filter(d -> number%d == 0).count() == 1 && number > 1;
    }

    public static IntStream infiniteStream(int from) {
        return IntStream.iterate(from, i -> i+1);
    }

    public static IntStream getPrimeStream(int from) {
        return infiniteStream(from).filter(Prime::isPrime);
    }

    public static IntStream getPrimeStreamGap(int from) {
        return getPrimeStream(from).map(i -> getPrimeStream(i+1).limit(1).sum()-i);
    }

    public static void main(String[] args) {
        getPrimeStream(0).limit(20).forEach(System.out::println);
    }
}
