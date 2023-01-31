import org.junit.Test;
import static org.junit.Assert.*;

public class TestSuite {

    @Test
    public void test() {
        //Exercise.binarySearch(...);
    }

    /*
     * This method returns:
     *             index of elem if it is between the "low" and "high" indexes.
     *             -1 if elem is not the there
     *             -2 if the parameters do not respect the preconditions.
     *
     * @pre low >= 0, high <= |arr|, low <= high
     * @post returns :
     *            index in which the searched element is located,
     *            -1 if it is not present.
     *            -2 if it does not respect @pre
     */
    @Test
    public void secondTest() {
        int[] a = new int[]{0, 1, 2, 3, 4};
        assertEquals( -2, Exercise.binarySearch(a, -1, 4, 1)); // low < 0
        assertEquals( -2, Exercise.binarySearch(a, 0, 10, 1)); // high > |a|
        assertEquals(-2, Exercise.binarySearch(a, 3, 0, 1)); // low > high
        assertEquals(-1, Exercise.binarySearch(a, 0, 3, -5)); // Not found
        assertEquals( 2, Exercise.binarySearch(a, 1, 3, 2)); // Found
        assertEquals( -2, Exercise.binarySearch(null, 0, 3, 2)); // null array
    }
}
