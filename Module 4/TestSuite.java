import org.junit.Test;
import static org.junit.Assert.*;

public class TestSuite {
    // Many thanks to Simon C.

    /*@Test
    public void test(){
        // parameters for parseExp
        char[] in = new char[]{*//*Some chars here*//*};
        // also possible to use .toCharArray from String class for that :
        // char[] in = myString.toCharArray();
        int offset = 3*//*Some value HERE*//*;
        int len = 6*//*Some value HERE*//*;
        // run the program with the given situation
        BigDecimal.parseExp(in, offset, len);
    }*/

    //INSTRUCTION:    102/105
    //BRANCH: 23/26
    //Inginious grading : 100%
    @Test
    public void bigDecimalTest() {
        // char format : number in E-notation : 1.257e42
        // offset : number of char in the decimal part : 1.257 = 5
        // length : number of digit in the exponent + 1 : e42 = 3
        // length + offset = char[] length
        assertEquals(-2, BigDecimal.parseExp("1.23E-2".toCharArray(), 4, 3)); // E-2
        assertEquals(+2, BigDecimal.parseExp("1.23E+2".toCharArray(), 4, 3)); // E+2
        assertTrue(assertException("1.23E+2".toCharArray(), 4, 0)); // len = 0
        assertEquals(42, BigDecimal.parseExp("1E000000000042".toCharArray(), 2, 12)); // len > 10 & 000...
        assertTrue(assertException("1E12345123451".toCharArray(), 2, 12)); // lan > 10 with no 0 (too big exponent)
        assertTrue(assertException("1.23E1a3".toCharArray(), 4, 4)); // E1a4 is not an exponent
    }

    // Do you have a better solution and want to share it ? Send me a PM or make a pull request
    private boolean assertException(char[] in, int offset, int len) {
        Exception e = null;
        try {
            BigDecimal.parseExp(in, offset, len);
        } catch (Exception caughtException) {
            e = caughtException;
        }
        return e != null; // True if exception was thrown as expected
    }
}