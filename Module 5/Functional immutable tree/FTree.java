import java.util.function.Function;

public abstract class FTree<A> {

    public final int depth() {
        int depth = 1;
        if(this.left() != null) // Only go to the left because the tree is balanced
            depth += this.left().depth();
        else
            return 0;
        return depth;
    }

    public abstract A value();
    public abstract FTree<A> left();
    public abstract FTree<A> right();

    public final <B> FTree<B> map(Function<A,B> f) {
        B value = f.apply(this.value());
        if(this instanceof Node){
            return new Node<B>(value, this.left().map(f), this.right().map(f));
        } else{
            return new Leaf<>(value);
        }
    }

}
