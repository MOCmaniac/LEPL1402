//import src.Array2DBuilderInterface;

public class MyBuilder implements Array2DBuilderInterface {
    public int[][] build_from(String s) {
        int[][] matrix = new int[(int) s.chars().filter(ch -> ch =='\n').count()][];
        String[] temp = s.split("\n");
        for(int x=0; x<temp.length; x++){
            temp[x] = temp[x].trim(); // removes whitespace
            String temp2[] = temp[x].split(" ");
            matrix[x] = new int[temp2.length];
            for(int a=0;a<temp2.length;a++){
                matrix[x][a] = Integer.parseInt(temp2[a]);
            }
        }
        return matrix;
    }

    public int sum(int[][] array) {
        int sum = 0;
        for (int[] ints : array) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }
        return sum;
    }

    public int[][] transpose(int[][] matrix) {
        int[][] newMatrix = new int[matrix[0].length][matrix.length];
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                newMatrix[y][x] = matrix[x][y];
            }
        }
        return newMatrix;
    }

    public int[][] product(int[][] matrix1, int[][] matrix2) {
        int[][] product = new int[matrix1.length][matrix2[0].length];
        for (int r = 0; r < matrix1.length; r++) { // all rows a matrix1
            for (int c = 0; c < matrix2[0].length; c++) { // all columns of matrix2
                for (int a = 0; a < matrix1[r].length; a++) {
                    product[r][c] += matrix1[r][a] * matrix2[a][c];
                }
            }
        }
        return product;
    }
}
