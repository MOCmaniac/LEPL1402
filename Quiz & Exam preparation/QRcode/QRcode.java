import java.util.Arrays;

/**
 * Make sure that the equal method consider
 * two QR codes as identical if they have been flipped
 * by 0,1,2 or 3 quarter rotations
 *
 * For instance the two following matrices should be
 * considered equals if you flip your head by 180 degrees
 *
 *         boolean [][] t0 = new boolean[][] {
 *                 {false,true,false,false},
 *                 {false,false,true,true},
 *                 {true,false,false,true},
 *                 {true,true,false,true}
 *         };
 *
 *         // t2 is a version of t2 with two quarter rotations
 *         boolean [][] t2 = new boolean[][] {
 *                 {true,false,true,true},
 *                 {true,false,false,true},
 *                 {true,true,false,false},
 *                 {false,false,true,false}
 *         };
 */
public class QRcode {

    protected boolean [][] data;

    /**
     * Create a QR code object
     * @param data is a n x n square matrix
     */
    public QRcode(boolean [][] data) {
        this.data = data;
    }


    /**
     * Return true if the other matrix is identical up to
     * 0, 1, 2 or 3 number of rotations
     * @param o the other matrix to compare to
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof QRcode){
            QRcode toCompare = (QRcode) o;

            for (int i = 0; i < 4; i++) {
                if(MyEquals(this, toCompare)) {
                    return true;
                }
                toCompare = rotation(toCompare);
            }
            return MyEquals(this, toCompare);

        }
        return  false;
    }
    public QRcode rotation(QRcode toRotate){
        int n = toRotate.data.length;
        boolean[][] data = new boolean[n][n];
        QRcode rotatedMatrix = new QRcode(data);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotatedMatrix.data[n - j - 1][i] = toRotate.data[i][j];
            }
        }
        return rotatedMatrix;
    }
    public boolean MyEquals(QRcode M1,QRcode M2){
        int n = M1.data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if(M1.data[i][j] != M2.data[i][j]){
                    return false;
                }

            }
        }
        return true;
    }

}
