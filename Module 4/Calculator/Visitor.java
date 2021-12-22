// LEPL1402 - Informatique II
// Vincent Bauffe - 2020-2021

package Module4.Calculator;

public interface Visitor {

    public int visit(Add visitable);

    public int visit(Mult visitable);

    public int visit(Div visitable);

    public int visit(Sub visitable);

    public int visit(Leaf visitable);
}
