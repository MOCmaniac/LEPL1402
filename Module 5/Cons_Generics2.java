import java.util.function.Predicate;
import java.util.function.Function;

public class Cons<E> {
    // the item inside this list node
    public E v;
    // The next element, null if nothing
    public Cons<E> next;
    // Constructor
    public Cons(E v, Cons<E> next) {
        this.v = v;
        this.next = next;
    }

    public <R> Cons <R> map(Function <E,R> function) {
      Cons<R> mappedCons = new Cons<R>(function.apply(this.v), null);
      if(this.next != null) {
          mappedCons.next = this.next.map(function);
      }
      return mappedCons;
    }

    public Cons <E> filter(Predicate <E> predicate) {
        if(this.next != null){  // if next not null, to avoid nullPointerException
            if(predicate.test((E)this.v)){  // if the value passes the filter
                return new Cons<>(this.v, this.next.filter(predicate));  // adds a Cons with its value and tries to filter the next one (recursion)
            } else {
                return this.next.filter(predicate);  // does not adds his value and tries to filter the next one (recursion)
            }
        } else {  // only if this is the last Cons of the original array
            if (predicate.test((E)this.v)) {  // if the value passes the filter
                return new Cons(this.v, null);  // adds his value and returns null as next (no next to filter)
            } else {
                return null;  // does not pass the filter, so only returns null
            }
        }
    } 
}
