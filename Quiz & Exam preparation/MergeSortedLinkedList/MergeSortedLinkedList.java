/**
 * Question:
 *
 * You are asked to merge two sorted linked list (see the TODO below)
 *
 * Once it is done, copy-paste the complete class in Inginious also with the imports
 *
 *
 * Feel free to add methods or fields in the class but do not modify
 * the signature and behavior of existing code
 *
 */
public class MergeSortedLinkedList {


    /**
     * You receive two linked list containing elements in increasing order
     * You are asked to return a new linked list that contains the
     * elements of both linkedlist in increasing order.
     * The previous linkedList should remain unchanged
     *
     * The complexity of your method should be in O(n+m)
     * where n = size of list1, m = size of list2
     * @param list1 the first list containing elements in increasing order
     * @param list2 the second list containing elemennt in increasing order
     * @return a list that contains the elements of list1 and list2 in increasing order
     */
    public static Node merge(Node list1, Node list2) {
        if(list2 == null ) return list1;
        if(list1 == null) return list2;
        return list1.value > list2.value ? new Node(list2.value, merge(list1, list2.next)) : new Node(list1.value, merge(list1.next, list2));
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
