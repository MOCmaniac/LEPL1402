// LEPL1402 - Informatique II
// Vincent Bauffe - 2020-2021

package Module4.Calculator;

public class Div extends Node implements Visitable {

    public Div(Visitable left, Visitable right) {
        super(left, right);
    }

    @Override
    public int accept(Visitor visitor) throws IllegalArgumentException {
        if (getRight().accept(visitor) == 0) throw new IllegalArgumentException();
        return getLeft().accept(visitor) / getRight().accept(visitor);
    }
}
