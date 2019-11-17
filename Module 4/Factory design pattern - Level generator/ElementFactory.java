public class ElementFactory extends Factory {

    private static final ElementFactory currentElementFactory = new ElementFactory();

    private ElementFactory(){}

    public static ElementFactory getInstance() {
        return currentElementFactory;
    }

    @Override
    public LevelComponent getElement(char c) {
        switch (c) {
            case 'D':
                return new Door();
            case '-':
                return new Floor();
            case 'K':
                return new Key();
            case '#':
                return new Wall();
            default:
                throw new IllegalArgumentException("This character does not correspond to one of the component");
        }
    }
}