public class Cons {
    // the item inside this list node
    public int v;
    // The next element, null if nothing
    public Cons next;
    // Constructor
    public Cons(int v, Cons next) {
        this.v = v;
        this.next = next;
    }

    // creates a new Cons that applies function f on all elements
    public Cons map(F f) {
        Cons mapped = new Cons(f.apply(this.v), null);
        if(this.next != null){
            mapped.next = this.next.map(f);
        }
        return mapped;
    }

    // creates a new Cons with all elements that matches predicate p
    public Cons filter(P p) {
        if(this.next != null){      // if next not null, to avoid nullPointerException
            if(p.filter(this.v)){   // if the value passes the filter
                return new Cons(this.v, this.next.filter(p));   // adds a Cons with its value and tries to filter the next one (recursion)
            } else {
                return this.next.filter(p); // does not adds his value and tries to filter the nexi one (recursion)
            }
        } else {                    // only if this is the last Cons of the original array
            if (p.filter(this.v)) { // if the value passes the filter
                return new Cons(this.v, null); // adds his value and returns null as next (no next to filter)
            } else {
                return null;    // returns null
            }
        } 
    }
}
