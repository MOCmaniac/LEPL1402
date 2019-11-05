import java.util.Comparator;
import java.util.List;
import java.awt.Color;

public class Sorter {


    /*
     * Should sort the list and order the Flower by color, in this specific order : red, blue, green
     */
    public static void sortFlowerByColor(List<Flower> list) {
        class sortFlower implements Comparator<Flower> {
            @Override
            public int compare(Flower f1, Flower f2) {
                return f1.getPetalColor().getRGB() - f2.getPetalColor().getRGB();
            }
        }
        list.sort(new sortFlower());
    }

    /*
     * Should sort the Plant by name only
     */
    public static void sortPlantByName(List<Plant> list) {
        list.sort(Comparator.comparing(Plant::getName));
    }

    /*
     * Should sort the list and order the Tree by height
     */
    public static void sortTreeByHeight(List<Tree> list) {
        list.sort(Comparator.comparing(Tree::getHeight));
    }
}
