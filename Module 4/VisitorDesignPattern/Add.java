public class Add extends Node implements Visitable {
    public Add(Visitable left, Visitable right) {
        super(left, right);
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
