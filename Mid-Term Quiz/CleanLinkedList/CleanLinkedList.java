import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * Question:
 *
 * You are asked to clean a increasing sorted linked List (see the TODO below)
 * Cleaning the linkedList means keeping only one occurrence of each value.
 *
 * For instance cleaning: 3,3,3,4,5,5,6,6,6,7,9,9,9,9,10,10
 * Gives: 3,4,5,6,7,9,10
 *
 * Your algorithm should execute in Theta(n)
 * where n are the number of elements in the original list
 *
 */
public class CleanLinkedList {

    Node first = null;
    Node last = null;
    int size = 0;

    public void add(int v) {
        Node toAdd = new Node(v,null);
        if (this.size == 0){
            this.first = toAdd;
        }else {

            this.last.next = toAdd;

        }
        this.last = toAdd;
        this.size++;

    }

    public void add(int ... values) {
        for (int v: values) {
            add(v);

        }
    }


    /**
     * Given the increasingly sorted list, it removes the duplicates
     * @return an increasingly sorted list containing the same set
     *         of elements as list but without duplicates.
     */
    public CleanLinkedList clean() {
        

        CleanLinkedList toReturn = new CleanLinkedList();

        Node current = this.first;

        for (int i = 0; i < this.size-1; i++) {

            if (current.v != current.next.v){
                toReturn.add(current.v);
            }
            current = current.next;

        }
        toReturn.add(current.v);

        return toReturn;
    }


    class Node {
        int v;
        Node next;
        Node(int v, Node next) {
            this.v = v;
            this.next = next;
        }
    }


}

