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
        Cons filtered = new Cons(null, null);
        Cons head = this;
        while(head != null){
            E headValue = (E) head.v;
            if(predicate.test(headValue)){
                filtered.v = head.v;
                if(head.next != null){
                    filtered.next = head.next.filter(predicate);
                }
                return filtered;
            }
            head = head.next;
        }
        return null;
    } 
}
