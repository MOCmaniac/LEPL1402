import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Test {
    
    public static void main(String[] args) {
        if (f1.apply(5) != 7) printError("f1");
        if (mf.apply("Coucou") != 6) printError("mf");
        if(!p1.test(10) || p1.test(11)) printError("p1");
        if(comp.compare("coucou", "coucou") != 0 || comp.compare("coucou", "cou") != 3) printError("comp");
        if(compLength.compare("coucou", "coucou") != 0 || compLength.compare("coucou", "co") != 4) printError("compLength");
    }

    public static void printError(String functionName) {
        System.out.println("Function \"" + functionName + "\" doesn't work properly");
    }

    static Function<Integer, Integer> f1 = (x) -> x + 2;
    static Function<String, Integer> mf = (s) -> s.length();
    static Predicate<Integer> p1 = (i) -> i % 2 == 0;
    static Comparator<String> comp = (s1, s2) -> s1.compareTo(s2);
    static Comparator<String> compLength = (s1, s2) -> s1.length() - s2.length();
}
