import java.util.Arrays;

public class maxSubArray {

    private static final boolean ANALYZE = true; // Set to true if you want kevin to mettre des paillettes in your vie

    public static void main(String[] args) {
        int[] test = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("\nResult : " + Arrays.toString(maxSubArray(test)));
    }

    /*
     *    This method is "intuitive" (at least for me) but not the most efficient one !
     *    It uses 2 nested loops instead of 1 in "maxSubArraySum" to generate all possible subArrays
     *    and compute the sum for each one.
     *
     *    Given the array [-2,1,-3,4,-1,2,1,-5,4]
     *    The contiguous subarray that produces the best result is [4,-1,2,1]
     *    For this array your method should return [6, 3, 6]
     */
    public static int[] maxSubArray(int[] a) {
        int[] data = new int[]{a[0], 0, 0};
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int sum = sumSubArray(a, i, j);

                if(ANALYZE){
                    printSubArray(a, i, j); // Prints the analyzed array and its sum
                    System.out.println(" -> Sum = " + sum);
                }

                if (sum > data[0]) {
                    data[0] = sum;
                    data[1] = i;
                    data[2] = j;
                }
            }
        }
        return data;
    }

    /*
     * Return the sum of all the element between from and to (both included)
     */
    private static int sumSubArray(int[] a, int from, int to) {
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += a[i];
        }
        return sum;
    }

    //Copied from https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/

    public static int[] maxSubArraySum(int a[]) {
        int max = a[0],
                max_ending_here = 0, from = 0,
                to = 0, s = 0;

        for (int i = 0; i < a.length; i++) {
            max_ending_here += a[i];

            if (max < max_ending_here) {
                max = max_ending_here;
                from = s;
                to = i;
            }

            if (max_ending_here < 0) {
                max_ending_here = 0;
                s = i + 1;
            }
        }
        return new int[]{max, from, to};
    }

    private static void printSubArray(int[] tab, int from, int to) {
        System.out.print("[" + tab[from]);
        for (int i = from + 1; i <= to; i++) {
            System.out.print(", " + tab[i]);
        }
        System.out.print("]");
    }
}