import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Question:
 *
 * You are asked to merge two sorted linked lists (see the TODO below) but you need to
 * keep only one node for each distinct value
 *
 * Once it is done, copy-paste the complete class in Inginious, including the imports
 *
 *
 * Feel free to add methods or fields in the class, but do not modify
 * the signature and behavior of existing code
 *
 */
public class MergeSortedLinkedListDuplicate {


    /**
     * You receive two linked lists containing elements in increasing order.
     * You are asked to return a new linked list that contains the
     * elements of both linked lists in increasing order but without duplicates.
     * The input linked lists must remain unchanged.
     * Moreover, the final linkedList must not contain duplicate values
     * That is, instead of 1-1-2-5, you must return 1-2-5.
     *
     * The complexity of your method must be in O(n+m)
     * where n = size of list1, m = size of list2
     * @param list1 the first list containing elements in increasing order
     * @param list2 the second list containing elements in increasing order
     * @return a list that contains the elements of list1 and list2 in increasing order without duplicates
     */
    public static Node merge(Node list1, Node list2) {
        Set<Integer> numbers = new HashSet<>();
        while(list1 != null || list2 != null) {
            if(list1 != null) {
                numbers.add(list1.value);
                list1 = list1.next;
            }
            if(list2 != null) {
                numbers.add(list2.value);
                list2 = list2.next;
            }
        }
        return numbers.stream().sorted(Comparator.reverseOrder()).map(v -> new Node(v, null)).reduce((v1, v2) -> new Node(v2.value, v1)).orElse(null);
    }

    private static Node merge(Node list1, Node list2, int lastValue) {
        if(list1 == null && list2 == null) return null;
        if(list1 == null) {
            Node next = merge(null, list2.next, lastValue);
            if(next == null) {
                return list2.value == lastValue ? null : list2;
            }
            return next;
        }
        if(list2 == null) {
            Node next = merge(list1.next, null, lastValue);
            if(next == null) {
                return list1.value == lastValue ? null : list1;
            }
            return next;
        }
        if(list1.value == lastValue && list2.value == lastValue) return merge(list1.next, list2.next, lastValue);
        if(list1.value == lastValue) return merge(list1.next, list2, lastValue);
        if(list2.value == lastValue) return merge(list1, list2.next, lastValue);
        return list1.value > list2.value ? new Node(list2.value, merge(list1, list2.next, list2.value)) : new Node(list1.value, merge(list1.next, list2, list1.value));
    }


    static class Node {

        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

    }
}
