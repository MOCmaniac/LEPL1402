public class Tree {

    public Node root;

    public Tree(Node root){

        this.root = root;
    }

    @Override
    public boolean equals(Object o) {
        //TODO
        if (o != null && o instanceof Tree) {

             Tree tree = (Tree) o;
             if (this.root == null){
                 return tree.root == null;
             } else if(tree.root == null){
                 return false;
             }else {
                 return this.root.equals(tree.root);
             }
        } else {
            return false;
        }


    }







}