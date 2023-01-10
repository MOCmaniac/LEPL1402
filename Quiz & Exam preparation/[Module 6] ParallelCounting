import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  You can ADD any code you like in this class (new members, new methods, etc.).
 *  You can also add imports.
 */

public class ParallelCounting {
    /**
     * Return the number of values equal to v using a parallel algorithm.
     *
     * @param values an array of numbers
     * @param v the value that you want to count
     * @param nThreads is a value between 1 and values.length
     * @return the number of elements equal to v in values (or 0 if no values are provided)
     *
     * Example: For
     *     values = [4.5f,3.2f,5.0f,6.6f,7.2f,1.5f,3.7f,5.8f,6.0f,9.0f,1.3f,2.3f,4.5f,1.5f]
     * and
     *     v = 4.5
     * the method returns 2 because the value 4.5 appears two times in the array.
     *
     * Try to give all threads more or less the same amount of work!
     */
    public static int parallelCount (Optional<float[]> values, float v, int nThreads){
        
        if (values.isPresent() == false){
            return 0;
        }    
        AtomicInteger count = new AtomicInteger();
        float[] array = values.get();
        int len = array.length;
        Thread[] pool = new Thread[nThreads];
        int sequence = len/nThreads;

        if (len == 0 || nThreads < 1){
            return 0;
        }

        for (int i = 0; i< nThreads; i++){
            int begin = i * sequence;
            int finish = ((i == nThreads-1)? len : begin + sequence);
            float[] partial_array = Arrays.copyOfRange(array,begin,finish);
            int p_len = partial_array.length;
            pool[i] = new Thread(()->{
                for(int u = 0 ; u < p_len; u++){
                    if (partial_array[u] == v){
                        count.addAndGet(1);
                    }}});
            pool[i].start();
        }

        for (Thread t : pool){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int result = count.get();
        return result;
    }
}
