public class Search {

    public static void main(String[] args) {
        int[] test = new int[]{-52, -45, -1, 48, 84, 112, 2048};
        int key = 49;
        System.out.println(recursiveSearch(test, key, 0, test.length));
    }

    /**
     * NO Recursive Implementation, works on inginious but isn't the most efficient
     *
     * @param tab is an ordered array of int.
     * @return index of elem or -1
     */
    public static int search(int[] tab, int elem) {
        int low = 0;
        int high = tab.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midVal = tab[mid];
            if (midVal < elem)
                low = mid + 1;
            else if (midVal > elem)
                high = mid - 1;
            else
                return mid; // elem found
        }
        return -1; // elem not found
    }

    // Recursive Implementation, doesn't work on inginious but is more efficient in practice
    public static int search2(int[] tab, int elem) {
        return recursiveSearch(tab, elem, 0, tab.length - 1);
    }

    private static int recursiveSearch(int[] a, int key, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = a[mid];
            if (midVal < key)
                return recursiveSearch(a, key, mid + 1, high);
            else if (midVal > key)
                return recursiveSearch(a, key, low, mid - 1);
            else
                return mid; // key found
        } else {
            return -1; // key not found.
        }
    }
}
