import java.sql.Array;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 *  You can ADD any code you like in this class (new members, new methods, etc.).
 *  You can also add imports.
 */


//Seulement 83.33%, besoin d'amélioration

public class ParallelCounting {

    public static int search(int min, int max, float[] array, float v){
        int counter = 0;

        for (int i = min; i < max ; i++) {
            if (array[i] == v){
                counter++;
            }
        }


        return counter;
    }
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
    public static int parallelCount (Optional<float[]> values, float v, int nThreads) {



        List<float[]> toList = values.map(Collections::singletonList).orElseGet(Collections::emptyList);



        if (toList.size() == 0){
            return 0;
        } else {

            int valuesLength = toList.get(0).length;
            int threadTaskLength = valuesLength/nThreads;

            int min = 0;
            int max = threadTaskLength;

            ExecutorService executor = Executors.newFixedThreadPool(nThreads);

            Future<Integer>[] array = new Future[nThreads];

            int counter = 0;


            for (int i = 0; i < nThreads; i++) {
                int temp = i;
                Future<Integer> future = executor.submit( () -> search(temp*max,(temp+1)*max,toList.get(0),v) );

                array[i] = future;

            }


            for (int i = 0; i < nThreads; i++) {

                int result = 0;
                try {
                    result = array[i].get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                counter += result;

            }

            executor.shutdown();





            return counter;

        }





    }


}


