import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MaxFinder {

    private final int nThreads, length, width, depth;
    private final int[][][] data;
    private final CyclicBarrier barrier;
    private int[] sums;
    private int max;

    /**
     * Worker class, responsible for the computation of some 2D-arrays.
     *
     * The constructor of the worker take only an integer as parameter.
     * For instance:
     *
     *  public Worker(int threadId) {
     *      // some code
     *  }
     *
     * To know which matrix the thread should process, use this formula:
     *
     * threadId + nThread*x with x = 0,1,2,...
     *
     * For instance, if there is 2 threads and threadId = 0, we have that this
     * thread should compute data[0], data[2], data[4], etc.
     *
     * After each computation, the thread must put the result in the `sums` array
     * and wait for the barrier to get the results. Then it continues until there
     * is no more element to compute.
     */
    class Worker implements Runnable {
        int myThreadId;
        Worker (int threadId) {myThreadId = threadId;}

        @Override
        public void run() {
            int matrixIndex = myThreadId;
            while (matrixIndex < ((length/nThreads)+1)*nThreads) {
                sums[myThreadId] = 0;
                if (matrixIndex < length) {
                    for (int[] j : data[matrixIndex]) for (int k : j) sums[myThreadId] += k;
                }
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ex){return;}
                matrixIndex += nThreads;
            }
        }
    }


    /**
     * Initialize the instance variables, start the threads for the computation
     * and create the barrier.
     *
     * Explication for the instances variables:
     *  - nTreahds: number of threads, given as argument
     *  - length: number of 2D-Arrays. First dimension of the matrix
     *  - width: Second dimension of the matrix. First dimension of the 2D-arrays
     *  - depth: Third dimension of the matrix. Second dimension of the 2D-arrays
     *  - data: the 3D-array for the computation. Given as argument
     *  - barrier: The cyclic barrier that will fetch the results.
     *  - sums: the array in which the thread will put their computation. This array
     *          should be of size nThreads
     *  - max: The maximum values found by the threads. Will be update by the barrier
     * 
     */
    private MaxFinder(int[][][] matrix, int nThreads) throws InterruptedException {
        data = matrix;
        length = data.length;
        width = data[0].length;
        depth = data[0][0].length;
        this.nThreads = nThreads;
        sums = new int[this.nThreads];
        max = Integer.MIN_VALUE;

        barrier = new CyclicBarrier(this.nThreads, ()-> {
           for (int i : sums) max = Math.max(max, i);
        });

        Thread[] threads = new Thread[this.nThreads];
        for (int i = 0; i < this.nThreads; i++) {
            threads[i] = new Thread(new Worker(i));
            threads[i].start();
        }

        for (Thread thread : threads) thread.join();
    }
    
    public static int getMaxSum(int[][][] matrix, int nThreads) {
        try {
            MaxFinder mf = new MaxFinder(matrix, nThreads);
            return mf.max;
        } catch (InterruptedException e) {
            return -1;
        }

    }
}
