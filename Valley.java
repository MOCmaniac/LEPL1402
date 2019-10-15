import java.util.ArrayList;
import java.util.Arrays;

public class Valley {

    public static void main(String[] args) {
        int[] test = new int[]{1,1,1,1,1,-1,-1,-1,-1,-1};
        System.out.println(Arrays.toString(valley(test)));
    }

    /*
     * Example:
     * [1, 1, 1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, -1]
     * Should return
     * [5, 3]
     */
    public static int[] valley(int[] array) {
        int[] biggestValleyMountain = new int[]{0, 0};
        int[] slopeChangement = new int[array.length];
        int iter = 1;

        slopeChangement[0] = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] != array[i]) {
                slopeChangement[iter] = i;
                iter++;
            }
        }
        slopeChangement[iter] = array.length;
        //System.out.println(Arrays.toString(slopeChangement));

        for (int i = 0; i < iter - 1; i++) {
            int biggest = Math.min(slopeChangement[i + 1] - slopeChangement[i], slopeChangement[i + 2] - slopeChangement[i + 1]);
            if(array[slopeChangement[i]] > 0){
                biggestValleyMountain[1] = Math.max(biggestValleyMountain[1], biggest);
            } else {
                biggestValleyMountain[0] = Math.max(biggestValleyMountain[0], biggest);
            }
        }

        return biggestValleyMountain;
    }

    // Need less memory but doesn't work with inginious because of the package import
    public static int[] valleyWithArrayList(int[] array) {
        int[] biggestValleyMountain = new int[]{0, 0};
        ArrayList<Integer> slopeChangement = new ArrayList<>();

        slopeChangement.add(0);
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] != array[i]) {
                slopeChangement.add(i);
            }
        }
        slopeChangement.add(array.length);

        for (int i = 0; i < slopeChangement.size() - 2; i++) {
            int biggest = Math.min(slopeChangement.get(i + 1) - slopeChangement.get(i), slopeChangement.get(i + 2) - slopeChangement.get(i + 1));
            if(array[slopeChangement.get(i)] > 0){
                biggestValleyMountain[1] = Math.max(biggestValleyMountain[1], biggest);
            } else {
                biggestValleyMountain[0] = Math.max(biggestValleyMountain[0], biggest);
            }
        }

        return biggestValleyMountain;
    }
}
