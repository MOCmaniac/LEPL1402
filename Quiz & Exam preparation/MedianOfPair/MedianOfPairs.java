import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Question:
 *
 * You are asked to compute the median of a list of pair (see the TODO below)
 *
 * Once it is done, copy-paste the complete class in Inginious also with the imports
 *
 *
 * Feel free to add methods or fields in the class but do not modify
 * the signature and behavior of existing code
 *
 */
public class MedianOfPairs {

    /**
     * Return the median (=middle) element
     * @param list, a list containing a odd number of distinct pairs
     * @return the pair of the list such that
     *      list.size/2 elements smaller, and list.size/2 element are greater
     *      than the returned element using the increasing lexicographic ordering
     * Example: for List((4,1),(3,8),(4,3),(9,1),(3,6)), the median is (4,1)
     *
     * Warning: your algorithm should execute in a time complexity strictly better than O(n^2)
     * where n is the size of the list
     *
     */

    /*
    D'abord on crée un comparator qui va nous permettre de trier la liste en ordre croissant.
    Il s'agit de trier d'abord le premier terme de la pair, et si ils sont égaux regarder le deuxième
    On sait donc que la médiane est au milieu de la liste. On prend donc l'élément
    à l'index list.size()/2.
     */
    public static Pair median(List<Pair> list) {

        list.sort(new PairCompare());

        int listSize = list.size();

        int middle = listSize/2;

        Pair toReturn = list.get(middle);

        return toReturn;
    }

    static class PairCompare implements Comparator{

        @Override
        public int compare(Object o1, Object o2) {

            Pair p1 = (Pair) o1;
            Pair p2 = (Pair) o2;

            if (p1.first > p2.first){

                return 1;
            } else if(p1.first < p2.first){
                return -1;
            }else{

                if(p1.second > p2.second){
                    return 1;
                } else if(p1.second < p2.second){
                    return -1;
                } else{
                    return 0;
                }

            }
        }


    }




    public static class Pair {
        int first, second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }


    }



}

