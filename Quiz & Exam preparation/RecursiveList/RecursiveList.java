import java.util.*;


/**
 * Question:
 *
 * You are asked to implement the reverse method of
 * the recursive list (see the TODO below)
 *
 * Once it is done, copy-paste the complete class in Inginious also with the imports
 *
 *
 * Feel free to add methods or fields in the class but do not modify
 * the signature and behavior of existing code
 *
 */
public abstract class RecursiveList<A> implements Iterable<A> {



    class Pair {
        RecursiveList<A> first;
        RecursiveList<A> last;
        public Pair(RecursiveList<A> first, RecursiveList<A> last) {
            this.first = first;
            this.last = last;
        }
    }

    /**
     * Reverse the list.
     * For instance the list A->B->C->D becomes D->C->B->A
     * @return the reverse list, the current list is unchanged
     */
    public RecursiveList<A> reverse() {
         ArrayList<A> reversed = new ArrayList<A>();

        for (A node:
             this) {
            reversed.add(node);
        }
        Collections.reverse(reversed);

        RecursiveList<A> newList = (RecursiveList<A>) toList(reversed.toArray());

        return newList;


    }

    public final boolean isEmpty() {
        return this instanceof Nil;
    }

    public abstract A head();

    public abstract RecursiveList<A> tail();

    public final RecursiveList<A> cons(final A a) {
        return new Cons(a, this);
    }

    public static <A> RecursiveList<A> nil() {
        return (Nil<A>) Nil.INSTANCE;
    }

    public static <A> RecursiveList<A> toList(A... elements) {
        return toList(elements,0);
    }

    public static <A> RecursiveList<A> toList(A[] elements, int i) {
        if (i >= elements.length) return nil();
        else return toList(elements,i+1).cons(elements[i]);
    }


    public Iterator<A> iterator() {
        return new Iterator<A>() {
            // intially current = list on which the iterator is required
            private RecursiveList<A> current = RecursiveList.this;

            public boolean hasNext() {
                return !current.isEmpty();
            }

            public A next() {
                if (current.isEmpty())
                    throw new NoSuchElementException("no next");
                else {
                    A item = current.head();
                    current = current.tail();
                    return item;
                }
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    private static final class Nil<A> extends RecursiveList<A> {
        public static final Nil<Object> INSTANCE = new Nil();

        @Override
        public A head() {
            throw new NoSuchElementException("empty list");
        }

        @Override
        public RecursiveList<A> tail() {
            throw new NoSuchElementException("empty list");
        }
    }

    private static final class Cons<A> extends RecursiveList<A> {
        private final A head;
        private final RecursiveList<A> tail;

        Cons(final A head, final RecursiveList<A> tail) {
            this.head = head;
            this.tail = tail;
        }

        public A head() {
            return head;
        }

        public RecursiveList<A> tail() {
            return tail;
        }
    }





}
