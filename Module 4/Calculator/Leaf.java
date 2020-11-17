// LEPL1402 - Informatique II
// Vincent Bauffe - 2020-2021

package Module4.Calculator;

public class Leaf implements Visitable {

    private int value;

    public Leaf(int size) { value = size; }

    public int getValue() {
        return value;
    }

    @Override
    public int accept(Visitor visitor) {
        return value;
    }
}
