import java.util.List;
import java.awt.Color;

public class Sorter {


    /*
     * Should sort the list and order the Flower by color, in this specific order : red, blue, green
     */
    public static void sortFlowerByColor(List<Flower> list){
        list.sort((Flower f1, Flower f2) -> {
            Color c1 = f1.getPetalColor(), c2 = f2.getPetalColor();
            if(c1.getRed() != c2.getRed()) {
                return c1.getRed() - c2.getRed();
            } else if(c1.getBlue() != c2.getBlue()) {
                return c1.getBlue() - c2.getBlue();
            } else {
                return c1.getGreen() - c2.getGreen();
            }
        });
    }
    /*
     * Should sort the Plant by name only
     */
    public static void sortPlantByName(List<Plant> list){
        list.sort((Plant p1, Plant p2) -> {
            String n1 = p1.getName(), n2 = p2.getName();
            for(int i = 0; i < Math.max(n1.length(), n2.length()); ++i) {
                if(i >= n1.length()) {
                    return -1;
                } else if(i >= n2.length()) {
                    return +1;
                } else if(n1.charAt(i) != n2.charAt(i)) {
                    return n1.charAt(i) - n2.charAt(i);
                }
            }
            return 0;
        });
    }

    /*
     * Should sort the list and order the Tree by height
     */
    public static void sortTreeByHeight(List<Tree> list){
        list.sort((Tree t1, Tree t2) -> t1.getHeight() - t2.getHeight());
    }
}
