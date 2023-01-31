import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort<E> extends RecursiveAction {

    private volatile E[] array, aux;
    private int lo, hi;
    private Comparator<? super E> comp;

    private static final int threshold = 128;

    // Take a look at the following link (especially the first example),
    // it really helped me and was a good foundation to start my code
    // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RecursiveAction.html

    public ParallelMergeSort(E[] a, int lo, int hi, E[] aux, Comparator<? super E> comp) {
        array = a;
        this.lo = lo;
        this.hi = hi;
        this.aux = aux;
        this.comp = comp;
    }

    /*
     * Run a normal sort when the difference between hi and lo is under the threshold
     * Otherwise : Split the sub array in two and start the sort on each part of the array simultaneously
     */
    @Override
    protected void compute() {
        if (hi - lo < threshold)
            sort(lo, hi); // Arrays.sort(array, lo, hi, comp); is prohibited :-(
        else {
            int mid = (lo + hi) / 2;
            invokeAll(new ParallelMergeSort<E>(array, lo, mid, aux, comp),
                    new ParallelMergeSort<E>(array, mid + 1, hi, aux, comp));
            merge(lo, mid, hi);
        }
    }

    //Sort array between lo and hi using merge sort
    private void sort(int lo, int hi) {
        if (hi - lo > 0) {
            int mid = (lo + hi) / 2;
            sort(lo, mid);
            sort(mid + 1, hi);
            merge(lo, mid, hi);
        }

    }

    //merge two subarray and keep them sorted
    private void merge(int lo, int mid, int hi) {
        // Store the ordered elements in `aux`
        int auxid = lo, i = lo, j = mid+1;
        while(auxid <= hi) {
            if(i <= mid && j <= hi) {
                if(comp.compare(array[j], array[i]) > 0) {
                    aux[auxid++] = array[i++];
                } else {
                    aux[auxid++] = array[j++];
                }
            } else if(i <= mid) {
                aux[auxid++] = array[i++];
            } else {
                aux[auxid++] = array[j++];
            }
        }
        // Copy the relevant part of `aux` into `a`
        System.arraycopy(aux, lo, array, lo, hi-lo+1);
    }

}
