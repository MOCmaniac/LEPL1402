// LEPL1402 - Informatique II
// Vincent Bauffe - 2020-2021

package Module4.Calculator;

public class Evaluation implements Visitor {
    @Override
    public int visit(Add visitable) {
        return visitable.accept(this);
    }

    @Override
    public int visit(Mult visitable) {
        return visitable.accept(this);
    }

    @Override
    public int visit(Div visitable) {
        return visitable.accept(this);
    }

    @Override
    public int visit(Sub visitable) {
        return visitable.accept(this);
    }

    @Override
    public int visit(Leaf visitable) {
        return visitable.accept(this);
    }
}
