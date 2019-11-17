public class Level extends AbstractLevel {

    public Level(String input) {
        size = input.split("\n").length;
        components = new LevelComponent[size][size];
        ElementFactory eF = ElementFactory.getInstance();

        int iter = 0;
        for (int r = 0; r < size ; r++) {
            for (int c = 0; c < size; c++) {
                char a = input.charAt(iter);
                components[r][c] = eF.getElement(input.charAt(iter));
                iter++;
            }
            iter ++;
        }
    }

    /* Example of level building with this String : String s = "#-K\n-D-\n#-K\n"
     * Gives : LevelComponent[][] componentsObjects = {
     *                                        {new Wall(), new Floor(), new Key()},
     *                                        {new Floor(), new Door(), new Floor()},
     *                                        {new Wall(), new Floor(), new Key()}}
     */
    public LevelComponent getElement(char c) {
        ElementFactory eF = ElementFactory.getInstance();
        return eF.getElement(c);
    }

    @Override
    public String toString() {
        String str = "";
        for (int r = 0; r < size ; r++) {
            for (int c = 0; c < size; c++) {
                str += components[r][c].draw();
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public LevelComponent[][] getComponents() {
        return components;
    }

    @Override
    public int getSize() {
        return size * size;
    }

}