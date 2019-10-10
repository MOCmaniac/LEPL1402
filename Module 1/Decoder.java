import java.util.ArrayList;

public class Decoder {

    /*
     * Forbidden characters are passed as an array of int.
     * Each element of this array correspond to the decimal ASCII code
     * of a forbidden character OR null if there's no forbidden character
     * If you encounter one of these forbidden character
     * you must ignore it when you translate your sentence.
     *
     * the 2D array "sentences" contain a set of decimal ASCII code we want you
     * to translate. Each sub-element of this array is a different sentence.
     * Ex : if we pass this array : [ ["42", "72", "88"], ["98", "99", "111", "47", "55"]]
     * to your decode method, you should return : [ "*HX", "bco/7" ]
     *
     * You should NEVER return null or an array containing null
     */
    public static String[] decode(int[] forbidden, String[][] sentences) {
        ArrayList<Integer> forbidden2 = new ArrayList<>();
        if (forbidden != null) {
            for (int i : forbidden) { // Transforme forbidden en ArrayList (plus facile de chercher dans un ArrayList que dans un array)
                forbidden2.add(i);
            }
        }

        String[] str = new String[sentences.length];

        for (int r = 0; r < str.length; r++) {
            str[r] = ""; // empêche de retourner un string null;
            for (int c = 0; c < sentences[r].length; c++) {
                int check = Integer.parseInt(sentences[r][c]);
                if (!forbidden2.contains(check)) { // Plus facile que de vérifier dans tout forbidden avec une boucle while
                    str[r] += (char) check;
                }
            }
        }
        return str;
    }

}