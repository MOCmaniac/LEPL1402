import java.util.ArrayList;

public class MagicSquare {


    /**
     * A magic square is an (n x n) matrix such that:
     *
     * - all the positive numbers 1,2, ..., n*n are present (thus each number appears exactly once)
     * - the sums of the numbers in each row, each column and both main diagonals are the same
     *
     *   For instance a 3 x 3 magic square is the following
     *
     *   2 7 6
     *   9 5 1
     *   4 3 8
     *
     *   You have to implement the method that verifies if a matrix is a valid magic square
     */

    /**
     *
     * @param matrix a square matrix of size n x n
     * @return true if matrix is a n x n magic square, false otherwise
     */
    public static boolean isMagicSquare(int [][] matrix) {
        // TODO Implement the body of this method, feel free to add other methods but do not change the signature of isMagiSquare
        // Initialisation de la liste qui contiendra les éléments de la matrice
        ArrayList list_elements = new ArrayList<>();
        // Vecteurs où chaque élément est respectivement la somme des éléments des colonnes/lignes.
        int[] colonnes = new int[matrix.length];
        int[] lignes = new int[matrix.length];
        int diagonale = 0;
        // Initialisation de la valeur des sommes.
        int total = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                // Check si l'élément appartient bien à [1,n*n]
                if(matrix[i][j] < 1 | matrix.length* matrix.length < matrix[i][j]){
                    return false;
                }
                // Check si on avait pas déjà eu l'élément
                if(list_elements.contains(matrix[i][j])){
                    return false;
                }
                list_elements.add(matrix[i][j]);

                // Fait la somme sur les lignes/colonnes/ la diagonale
                lignes[i] += matrix[i][j];
                colonnes[j] += matrix[i][j];
                if(i == j){
                    diagonale += matrix[i][j];
                }
            }
            // On check la somme de ligne qui vient d'être faite
            // Si première ligne on initialise la somme
            if (i == 0){
                total = lignes[i];
            }
            if(total != lignes[i]){
                return false;
            }
        }
        // Check diagonale
        if (total != diagonale){
            return  false;
        }
        // Check colonnes à la fin
        for (int i = 0; i < matrix.length; i++) {
            if(total != colonnes[i]){
                return false;
            }
        }
        return true;
    }
}
