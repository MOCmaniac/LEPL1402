// LEPL1402 - Informatique II
// Vincent Bauffe - 2020-2021

package Module4.ListFiltering;

public class VisitableList extends Visitable {

    public VisitableList(Object[] list) { elements = list; }

    @Override
    void accept(Visitor visitor) {
        for (int i = 0; i < elements.length; i++) {
            visitor.visit(elements[i]);
        }
    }
}
