import java.util.Arrays;

public class MergeSort {
    // https://en.wikipedia.org/wiki/Merge_sort
    // https://www.geeksforgeeks.org/merge-sort/

    // Theses methods work with the given tests but don't work on inginious...

    private static final boolean ANALYZE = true; // Set to true if you want to see what the algorithm does

    public static void main(String[] args) {
        int[][] testArrays = new int[5][];
        testArrays[0] = new int[]{0};
        testArrays[1] = new int[]{1, 0};
        testArrays[2] = new int[]{2, 0, 1};
        testArrays[3] = new int[]{3, 5, 6, 7, 1, 2, 8, 4, 0};
        testArrays[4] = new int[]{7, 3, 0, 6, 4, 9, 1, 2, 5, 8};
        for (int[] i : testArrays) {
            sort(i);
        }
    }

    /**
     * Pre-conditions: a[lo..mid] and a[mid+1..hi] are sorted
     * Post-conditions: a[lo..hi] is sorted
     */
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        if(ANALYZE){
            System.out.print("-> ");
            printSubArray(a, lo, mid);
            System.out.print(" & ");
            printSubArray(a, mid + 1, hi);
            System.out.print(" => ");
        }
        int low1 = lo;
        int low2 = mid + 1;
        int iter = 0;
        while (iter < aux.length) {
            if (low2 > hi || (a[low1] < a[low2] && low1 <= mid)) {
                aux[iter] = a[low1];
                low1++;
            } else {
                aux[iter] = a[low2];
                low2++;
            }
            iter++;
        }
        if(ANALYZE) System.out.println(Arrays.toString(aux));

        System.arraycopy(aux, 0, a, lo, hi - lo + 1);
    }

    /**
     * Rearranges the array in ascending order, using the natural order
     */
    public static void sort(int[] a) {
        System.out.println("Initial : " + Arrays.toString(a));
        mergeSort(a, 0, a.length - 1);
        System.out.println("Result : " + Arrays.toString(a) + "\n");
    }

    //TODO Optionnal additionnal method
    public static void mergeSort(int[] a, int low, int high) {
        if (high > low) {
            int mid = (low + high) / 2;
            if (ANALYZE) printlnAnalyzedArray(a, low, mid, high);
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, new int[high - low + 1], low, mid, high);
        }
    }

    private static void printlnAnalyzedArray(int[] a, int from, int mid, int hi) {
        System.out.print("[" + a[from]);
        for (int i = from + 1; i <= hi; i++) {
            if (i != mid + 1) {
                System.out.print(", " + a[i]);
            } else
                System.out.print("; " + a[i]);
        }
        System.out.println("]");
    }

    private static void printSubArray(int[] a, int from, int to) {
        System.out.print("[" + a[from]);
        for (int i = from + 1; i <= to; i++) {
            System.out.print(", " + a[i]);
        }
        System.out.print("]");
    }

}
