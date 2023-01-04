import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Ce code passe les tests inginious mais pas le test local 3 ...
 */
public class ParallelCounting {

    private static float[][] subDivide(ArrayList<float[]> values, int t) {
        if(t==1) return values.toArray(new float[0][]);
        float[] vs = values.get(values.size()-1);
        int out;
        if (vs.length%t != 0) out = (vs.length / t) + 1;
        else out = (vs.length / t);
        values.add(0,Arrays.copyOfRange(vs,0,out));
        values.set(values.size()-1,Arrays.copyOfRange(vs,out,vs.length));
        t--;
        return subDivide(values, t);
    }

    private static int count(float[] arr, float v) {
        int count = 0;
        for (float f : arr) if (f==v) count++;
        return count;
    }

    /**
     * Return the number of values equal to v using a parallel algorithm.
     *
     * @param oValues an array of numbers
     * @param v the value that you want to count
     * @param nThreads is a value between 1 and length of values
     * @return the number of elements equal to v in values (or 0 if no values are provided)
     * Example: For
     *     values = [4.5f,3.2f,5.0f,6.6f,7.2f,1.5f,3.7f,5.8f,6.0f,9.0f,1.3f,2.3f,4.5f,1.5f]
     * and
     *     v = 4.5
     * the method returns 2 because the value 4.5 appears two times in the array.
     *
     * Try to give all threads more or less the same amount of work!
     */
    public static int parallelCount (Optional<float[]> oValues, float v, int nThreads) {
        if (!oValues.isPresent()) return 0;
        float[][] values = subDivide(new ArrayList<float[]>(Collections.singleton(oValues.get())), nThreads);
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        List<Future<Integer>> futures = new ArrayList<>();
        for (float[] arr : values) futures.add(executor.submit(() -> count(arr, v)));

        Integer counter = 0;
        for (Future<Integer> f: futures) {
            try {
                counter += f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
        return counter;
    }
}
