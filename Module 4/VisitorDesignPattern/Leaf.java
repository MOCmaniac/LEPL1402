public class Leaf implements Visitable {
    
    protected int value;

    public Leaf(int value){
        this.value = value;
    }



    public int getValue() {

        return this.value;

    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
