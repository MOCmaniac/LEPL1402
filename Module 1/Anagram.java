import java.util.Arrays;

public class Anagram {


    public static void main(String[] args) {
        System.out.println((int) 'a');

        int a = 300;
        byte b = (byte) a;

        System.out.println(b);

    }

    /**
     * Count the number of occurrences of each letter of the alphabet
     * (from 'A' to 'Z') in the given string. The function must return
     * an array containing 26 elements: The item with index 0 contains
     * the number of 'A' in the string, the item with index 1 contains
     * the number of 'B', and so on.
     *
     * This function must be case-insensitive, i.e. the character 'f'
     * must be considered as the same as character 'F'.
     *
     * Characters that are neither in the range 'a' to 'z', nor in the
     * range 'A' to 'Z' must be ignored.
     * 
     * @param s The string of interest.
     * @return An array counting the number of occurrences of each
     * letter.
     **/
    public static int[] countAlphabet(String s) {
        int[] occ = new int[26];
        String[] str = s.split("");

        String[] d = new String[]{"A","B","C","D","E","F","G", "H", "I", "J", "K", "L", "M","N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < 26; j++) {
                if ((str[i].toUpperCase()).equals(d[j])){
                    occ[j]++;
                    break;
                }
            }
        }


        return occ;
    }


    /**
     * Check whether one string is an anagram of another string. It is
     * strongly advised to use the function "countAlphabet()" as a
     * building block for function "isAnagram()".
     * @param s1 The first string.
     * @param s2 The second string.
     * @return <code>true</code> iff. the two strings are anagrams.
     **/
    public static boolean isAnagram(String s1,
                                    String s2) {
        boolean a = Arrays.equals(countAlphabet(s1),countAlphabet(s2));
        return a;
    }
}
