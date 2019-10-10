import java.util.Arrays;

public class MergeSortBetter {
    // https://en.wikipedia.org/wiki/Merge_sort
    // https://www.geeksforgeeks.org/merge-sort/

    // This code uses the sort method from "MergeSortBetter" from the slides
    // and my merge method (pretty similar with the one in the slides)

    // Theses methods work with the given tests but don't work on inginious...

    // /!\ If your code works on inginious and you want to share it send me a PM !

    private static final boolean ANALYZE = false; // Set to true if you want to see what the algorithm does

    public static void main(String[] args) {
        int[] a = new int[]{22, 49, -82, 23, -16, 18, 86, -56, 5, -4, 15, 23};
        System.out.println("Before sort :\n" + Arrays.toString(a) + "\n");
        sort(a);
        System.out.println("\nAfter sort :\n" + Arrays.toString(a));
    }
    /**
     * Merge the array from lo to mid-1 with array from mid to hi-1
     */
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        if(ANALYZE) {
            System.out.print(" -> ");
            printSubArray(a, lo, mid);
            System.out.print(" & ");
            printSubArray(a, mid, hi);
        }

        int low1 = lo;
        int low2 = mid;
        int iter = 0;

        while (iter < aux.length) {
            if (low2 >= hi || (a[low1] < a[low2] && low1 < mid)) {
                aux[iter] = a[low1];
                low1++;
            } else {
                aux[iter] = a[low2];
                low2++;
            }
            iter++;
        }

        // copy b into a
        int l = lo; // Start at the right place in a
        for (int n : aux) {
            a[l] = n;
            l++;
        }

        if(ANALYZE) {
            System.out.print(" => ");
            System.out.println(Arrays.toString(aux));
        }
    }

    /**
     * Split the array and call merge
     */
    public static void sort(int[] a) {
        mergeSort(0, a.length, a);
    }

    public static void mergeSort(int from, int to, int[] a) {
        if (from + 1 == to) //size 1 is always sorted
            return;
        int mid = (from + to) / 2;

        if(ANALYZE){
            printAnalyzedArray(a, from, mid, to); // Prints all the array the function creates
        }

        mergeSort(from, mid, a);
        mergeSort(mid, to, a);

        int[] aux = new int[to - from];
        merge(a, aux, from, mid, to);
    }

    static void printAnalyzedArray(int[] tab, int l, int m, int h) {
        System.out.print("[");
        System.out.print(tab[l]);
        for (int i = l + 1; i < m; i++) {
            System.out.print(", " + tab[i]);
        }
        System.out.print("; " + tab[m]); // ";" mark where the array will be split

        for (int i = m + 1; i < h; i++) {
            System.out.print(", " + tab[i]);
        }
        System.out.println("]");
    }

    static void printSubArray(int[] tab, int from, int to) {
        System.out.print("[" + tab[from]);
        for (int i = from + 1; i < to; i++) {
            System.out.print(", " + tab[i]);
        }
        System.out.print("]");
    }
}