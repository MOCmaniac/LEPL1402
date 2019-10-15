public class StringUtils {


    /*
     * Split the input string 'str' w.r.t the character 'marker' in an array of String
     * for example split("test-test", '-') => {"test", "test"}
     * Must return null if there is no occurrence of 'marker' in 'str'
     */
    public static String[] split(String str, char marker) {
        if (!str.contains(Character.toString(marker))) {
            return null;
        }
        int count = 1;
        for (int x = 0; x < str.length(); x++) {
            if (str.charAt(x) == marker) {
                count++;
            }
        }
        String[] splitted = new String[count];
        int iter = 0;
        int begin = 0;
        for (int x = 0; x < str.length(); x++) {
            if (str.charAt(x) == marker) {
                splitted[iter] = str.substring(begin, x);
                begin = x + 1;
                iter++;
            }
            if (x == str.length() - 1) {
                splitted[iter] = str.substring(begin, x + 1);
            }
        }
        return splitted;
    }


    /*
     * Returns the index of the first occurrence of sub in str
     * or -1 if there is no occurrence of sub in str at all.
     * Be careful, we ask you to make CASE SENSITIVE comparison between str and sub.
     */
    public static int indexOf(String str, String sub) {
        int len = str.length();
        int lenSub = sub.length();
        for (int x = 0; x <= len - lenSub; x++) {
            if (str.substring(x, x + lenSub).equals(sub)) { // substring doesn't take the char at x+lenSub (stops before)
                return x;
            }
        }
        return -1;
    }


    public static String toLowerCase(String str) {
        char[] s1 = str.toCharArray();
        int diff = 'a' - 'A';

        for (int i=0; i < s1.length / 2 ; i++) {
            if (s1[i] >= 'A' && s1[i] <= 'Z') {
                s1[i] += diff;
            }
        }
        return String.valueOf(s1);
    }


    /*
     * Returns true if the string 'str' is a palindrome (a string that reads the same from
     * left to right AND from right to left).
     */
    public static boolean palindrome(String str) {
        int l = str.length();
        for (int x = 0; x < l; x++) {
            if (str.charAt(x) != str.charAt(l - 1 - x)) {
                return false;
            }
        }
        return true;
    }


}
