import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

abstract class FList<A> implements Iterable<A> {
    /**
     * Returns an empty FList
     */
    public static <A> FList<A> nil() {
        return (Nil<A>) Nil.INSTANCE;
    }

    /**
     * Returns a new FList obtained by prepending a to this list
     */
    public final FList<A> cons(final A a) {
        return new Cons(a, this);
    }

    /**
     * @return the number of elements in this list
     */
    public abstract int length();

    /**
     * @return true if the list is empty, false otherwise
     */
    public abstract boolean isEmpty();

    /**
     * @return the head of the list.
     * @throws NoSuchElementException if the list is empty
     */
    public abstract A head();

    /**
     * @return the tail of the list (i.e. the sublist without the first element of this list)
     * @throws NoSuchElementException if the list is empty
     */
    public abstract FList<A> tail();

    /**
     * Returns a new list containing the outputs obtained by applying function f to each element of this list
     */
    public abstract <B> FList<B> map(Function<A,B> f);

    /**
     * Returns a new list containing only the elements from this list that fullfill predicate f (i.e. f(elem) == true)
     */
    public abstract FList<A> filter(Predicate<A> f);


    public Iterator<A> iterator() {

        return new Iterator<A>() {
            FList<A> list = FList.this;
            public boolean hasNext() {
                return !list.isEmpty();
            }

            public A next() {
                if (list.isEmpty()){
                    throw new NoSuchElementException("La liste est vide");
                }
                A next = list.head();
                list = list.tail();
                return next;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static final class Nil<A> extends FList<A> {
        public static final Nil<Object> INSTANCE = new Nil<>();

        @Override
        public int length() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public A head() {
            throw new NoSuchElementException();
        }

        @Override
        public FList<A> tail() {
            throw new NoSuchElementException();
        }

        @Override
        public <B> FList<B> map(Function<A, B> f) {
            return nil();
        }

        @Override
        public FList<A> filter(Predicate<A> f) {
            return nil();
        }
        // TODO
    }

    private static final class Cons<A> extends FList<A> {
        public final A v;
        public final FList<A> next;

        public Cons(A val, FList<A> next) {
            this.v = val;
            this.next = next;
        }

        @Override
        public int length() {
            return this.next.length() + 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public A head() {
            return this.v;
        }

        @Override
        public FList<A> tail() {
            return this.next;
        }

        @Override
        public <B> FList<B> map(Function<A, B> f) {
            return new Cons<>(f.apply(v), this.next.map(f));
        }

        @Override
        public FList<A> filter(Predicate<A> f) {
            if (f.test(v)) {
                return new Cons<>(v, this.next.filter(f));
            }
            return this.next.filter(f);
        }

    }
}
