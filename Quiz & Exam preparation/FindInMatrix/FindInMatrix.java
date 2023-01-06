import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// You are allowed to add imports here

public class FindInMatrix {
    // You are allowed to add methods or class members, but do not change the signature
    // of the existing methods and class members.

    public static class Result {
        int row;
        List<Integer> columns;
    }

    /**
     * This method finds the row that contains the most number of occurrences of a
     * certain value and returns the row index of that row and the column indexes
     * (in increasing order) where the value occurs in that row.
     * The matrix is represented by a two-dimensional array.
     *
     * Example: if the method is called with the value "3" and the following matrix
     *     (1   3  2  -4)          // <- there is a "3" in column 1
     *     (-3  4  5  -2)
     *     (3   0  3   2)          // <- there is a "3" in column 0 and column 2
     * then the result of the search is:
     *      row=2 , columns=[0,2]
     * because row 2 contains the most number of occurrences of "3" (in columns 0 and 2).
     *
     * Your solution MUST use a thread pool to accelerate the operation.
     *
     * @param matrix a rectangular matrix
     * @param value the value to find
     * @param poolSize the thread pool size
     * @return the result of the search
     *
     * You can assume that there is exactly one row in the matrix with the
     * most number of occurrences of the value.
     * Catch exceptions and ignore them.
     */
    public static int countNumber(int value, int[] row){
        int count = 0;
        for(int i : row){
            if(i==value){
                count++;
            }
        }
        return count;
    }

    public static List<Integer> getColumns(int[] row, int value){
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < row.length; i++) {
            if(row[i]==value){
                result.add(i);
            }
        }
        return result;
    }

    public static Result findValue(int[][] matrix, int value, int poolSize) {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        Future<Integer>[] futures = new Future[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int finalI = i;
            futures[i] = executor.submit(()->countNumber(value,matrix[finalI]));
        }

        int max_row = 0;
        int max_count = 0;

        for (int i = 0; i < futures.length; i++) {
            try {
                int result = futures[i].get();
                if(result > max_count){
                    max_count = result;
                    max_row = i;
                }
            } catch (InterruptedException | ExecutionException e){

            }
        }

        executor.shutdown();
        Result result = new Result();
        result.row = max_row;
        result.columns = getColumns(matrix[max_row],value);
        return result;
    }
}
