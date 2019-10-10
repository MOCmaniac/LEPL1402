public class MergeSort {

    /**
     * Merge the array from lo to hi
     */
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        // Store the ordered elements in `aux`
        int auxid = lo, i = lo, j = mid+1;
        while(auxid <= hi) {
            if(i <= mid && j <= hi) {
                if(a[i] < a[j]) {
                    aux[auxid++] = a[i++];
                } else {
                    aux[auxid++] = a[j++];
                }
            } else if(i <= mid) {
                aux[auxid++] = a[i++];
            } else {
                aux[auxid++] = a[j++];
            }
        }
        // Copy the relevant part of `aux` into `a`
        System.arraycopy(aux, lo, a, lo, hi-lo+1);
    }
    /**
     *  Split the array and call merge
     */
    public static void sort(int[] a) {
        mergeSort(a, new int[a.length], 0,a.length-1);
    }

    //TODO Optionnal additionnal method
    private static void mergeSort(int[] a, int[] aux, int lo, int hi) {
        if(hi - lo > 0) {
            int mid = (lo+hi)/2;
            mergeSort(a, aux, lo, mid);
            mergeSort(a, aux, mid+1, hi);
            merge(a, aux, lo, mid, hi);
        }
    }

}
