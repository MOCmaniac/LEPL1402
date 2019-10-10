public class MergeSortFromSlide {
    /**
     * Best version from the slides
     *
     * @param from
     * @param to
     * @param values
     */
    public static void mergeSortBetter(int from, int to, int[] values) {
        if (from + 1 == to) //size 1 is always sorted
            return;
        int mid = (from + to) / 2;
        mergeSortBetter(from, mid, values);
        mergeSortBetter(mid, to, values);
        //create a temp array
        int[] tmp = new int[to - from];
        int tmpIdx = 0;
        int aIdx = from;
        int bIdx = mid;
        while (aIdx != mid || bIdx != to) {
            if (bIdx == to || (aIdx != mid && values[aIdx] < values[bIdx])) {
                tmp[tmpIdx] = values[aIdx];
                aIdx++;
                tmpIdx++;
            } else {
                tmp[tmpIdx] = values[bIdx];
                bIdx++;
                tmpIdx++;
            }
        }
        //copy back
        for (int i = 0; i < to - from; i++)
            values[from + i] = tmp[i];
    }

    /**
     * Standard version of mergeSort
     *
     * @param values
     */
    public static void mergeSort(int[] values) {
        if (values.length == 1)
            return;
        int mid = values.length / 2;
        int[] A = new int[mid];
        int[] B = new int[values.length - mid];
        //copy to A
        for (int i = 0; i < mid; i++)
            A[i] = values[i];
        //copy to B
        for (int i = mid; i < values.length; i++)
            B[i - mid] = values[i];
        //sort A and B
        mergeSort(A);
        mergeSort(B);
        //Merge A and B back
        int vIdx = 0;
        int aIdx = 0;
        int bIdx = 0;
        while (aIdx != A.length || bIdx != B.length) {
            if (bIdx == B.length || (aIdx != A.length && A[aIdx] < B[bIdx])) {
                values[vIdx] = A[aIdx];
                aIdx++;
                vIdx++;
            } else {
                values[vIdx] = B[bIdx];
                bIdx++;
                vIdx++;
            }
        }
    }
}
