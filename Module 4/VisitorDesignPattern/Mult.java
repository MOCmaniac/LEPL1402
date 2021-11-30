public class Mult extends Node implements Visitable {
    public Mult(Visitable left, Visitable right) {
        super(left, right);
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
    // YOUR CODE HERE
}
