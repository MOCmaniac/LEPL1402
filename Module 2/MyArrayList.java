import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyArrayList<Item> implements Iterable<Item> {

    private Object[] list;
    private int size;

    public static void main(String[] args) {
        MyArrayList<Integer> test = new MyArrayList<>(2);
        test.print();
        for (int i = 0; i < 8; i++) {
            test.enqueue(i);
            test.print();
        }
        for (int i = 0; i < 2; i++) {
            test.remove(1);
            test.print();
        }
        Iterator<Integer> it = test.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    public void print() {
        System.out.print("[");
        if (list.length > 0) {
            System.out.print(list[0]);
        }
        for (int x = 1; x < list.length; x++) {
            System.out.print(", " + list[x]);
        }
        System.out.println("] # of elem : " + size + "\tArray size : " + list.length);
    }

    public MyArrayList(int initSize) throws IndexOutOfBoundsException {
        if (initSize < 0) {
            throw new IndexOutOfBoundsException();
        }
        list = new Object[initSize];
        size = 0;
    }

    /*
     * Checks if 'list' needs to be resized then add the element at the end
     * of the list.
     */
    public void enqueue(Item item) {
        if (size >= list.length) {
            increaseAndCopyArray();
        }
        list[size] = item;
        size++;
    }

    /*
     * Removes the element at the specified position in this list.
     * Returns the element that was removed from the list. You dont need to
     * resize when removing an element.
     */
    public Item remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Item i = (Item) list[index];
        shiftLeft(index + 1, size - 1);
        size--;
        return i;
    }


    public int size() {
        return this.size;
    }

    public void shiftLeft(int from, int to) {
        for (int x = from; x <= to; x++) {
            list[x - 1] = list[x];
        }
        list[to] = null;
    }

    public void increaseAndCopyArray() {
        Object[] newList = new Object[(size + 1) * 2];
        for (int x = 0; x < size; x++) {
            newList[x] = list[x];
        }
        list = newList;
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyArrayListIterator();
    }


    private class MyArrayListIterator implements Iterator<Item> {
        private int sizeInit = size;
        private int index = 0;

        @Override
        public boolean hasNext() {
            return failFastCheck() && index < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                return null;
            }
            return (Item) list[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private boolean failFastCheck() throws ConcurrentModificationException {
            if (size != sizeInit)
                throw new ConcurrentModificationException("ArrayList modified while iterating on it");
            return true;
        }
    }

}