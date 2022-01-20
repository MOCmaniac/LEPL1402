public class Convolution  {

    /**
     *
     * @param input is a n1 x m1 non-null rectangular matrix with at least 3 rows and 3 cols
     * @param kernel is a 3 x 3 square matrix
     * @return a matrix M with dimension (n1-2) x (m1-2) with
     *         M[i][j] = sum_{k in 0..2, l in 0..2} input[i+k][j+l]*kernel[k][l]
     *
     *
     * For example, if the input matrix is
     * [[1,2,1,5,6],
     *  [3,1,5,7,2],
     *  [1,4,5,2,1],
     *  [6,7,1,0,3]]
     *
     *  and the kernel is
     *
     *  [[0,0,0],
     *   [0,1,1],
     *   [0,0,0]]
     *
     *   then the output should be
     *
     *   [[6,12,9],
     *    [9,7,3]]
     */
    public static int [][] convolution(int [][] input, int [][] kernel) {

        int nM = input.length - 2;
        int mM = input[0].length - 2;
        int[][] M = new int[input.length-2][input[0].length-2];
        int sum = 0;

        for (int i = 0; i < nM; i++) {
            for (int j = 0; j < mM; j++) {

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        sum += ((input[i+k][j+l])*(kernel[k][l]));
                    }
                }
                M[i][j] = sum;
                sum = 0;
            }
        }

        return M;
    }
}