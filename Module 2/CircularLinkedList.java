import java.util.ConcurrentModificationException;
import java.util.Iterator;


public class CircularLinkedList<Item> implements Iterable<Item> {

    private int n;          // size of the list
    private Node last;   // trailer of the list

    // helper linked list class
    private class Node {

        private Item item;
        private Node next;

        private Node(Item item) {
            this.item = item;
            this.next = null;
        }

    }

    public CircularLinkedList() {
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Node getLast() {
        return last;
    }

    public Item getItem(Node n) {
        return n.item;
    }

    //Le code fonctionne mais Kévin ne met pas des pailettes dans ta vie là...

    public static void main(String[] args) {
        CircularLinkedList<String> list = new CircularLinkedList<>();
        list.enqueue("1, un");
        list.enqueue("2, deux");
        list.enqueue("3, trois");
        list.enqueue("4, quatre");
        list.enqueue("5, cinq");
        list.enqueue("6, six");
        list.print();
    }

    public void print() {
        for (Item i : this) {
            System.out.println(i);
        }
    }

    /**
     * Append an item at the end of the list
     *
     * @param item the item to append
     */
    public void enqueue(Item item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            last = newNode;
            last.next = last;
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
        n++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > n - 1) {
            throw new IndexOutOfBoundsException();
        }

        if (isEmpty()) {
            return null;
        }

        Node toRemove;

        if (n == 1) {
            toRemove = last;
            last = null;
            n = 0;
            return toRemove.item;
        }

        toRemove = last.next;
        Node beforeToRemove = last;
        int pos = 0;
        while (pos != index && toRemove.next != null) {
            beforeToRemove = toRemove;
            toRemove = toRemove.next;
            pos++;
        }
        beforeToRemove.next = toRemove.next;
        if (index == n - 1) {
            last = beforeToRemove;
        }
        n--;
        return toRemove.item;
    }


    /**
     * Returns an iterator that iterates through the items in FIFO order.
     *
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * Implementation of an iterator that iterates through the items in FIFO order.
     */
    private class ListIterator implements Iterator<Item> {
        private Node current = last;

        private final int nInit = n;
        private int iterationDone = 0;

        private boolean failFastCheck() throws ConcurrentModificationException {
            if (n != nInit)
                throw new ConcurrentModificationException("CLL modified while iterating on it");
            return true;
        }

        public boolean hasNext() {
            return failFastCheck() && current != null && iterationDone < nInit;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            failFastCheck();
            if (!hasNext()) {
                return null;
            }
            if (iterationDone == 0) {
                current = current.next;
            }
            Item item = current.item;
            current = current.next;
            iterationDone++;
            return item;
        }
    }

}