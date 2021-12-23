// LEPL1402 - Informatique II
// Vincent Bauffe - 2020-2021

package Module4.ListFiltering;

import java.util.ArrayList;
import java.util.List;

public class VisitorList extends Visitor {

    public VisitorList(Class cls) { super(cls); }

    /**
     * returns "filtered" list. Should only be called
     * after the visitor traversed the whole list.
     */
    @Override
    List<Object> getFiltered() { return filtered; }

    @Override
    void visit(Visitable visitable) { visitable.accept(this); }

    /**
     * If o is an instance of 'toFilter', add it to filtered.
     */
    @Override
    void visit(Object o) { if (o.getClass() == toFilter) filtered.add(o); }
}
