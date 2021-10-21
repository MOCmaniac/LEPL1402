// Solution by Martin Gyselinck

public class Tree {

    public Node root;

    public Tree(Node root){
        this.root = root;
    }

    public Tree combineWith(Tree o){
        if (o == null)
            return this;
        if (o.root == null)
            return this;
        if (this.root == null)
            return o;
        Tree newTree = new Tree(new Node(0));
        createTree(newTree.root, this.root, o.root);
        return newTree;
    }

    public void createTree(Node newNode, Node node1, Node node2) {
        if (node1.left != null || node2.left != null) {
            newNode.left = new Node(0);
            if (node1.left != null && node2.left != null)
                createTree(newNode.left, node1.left, node2.left);
            else if (node1.left == null)
                continueTreeSolo(newNode.left, node2.left);
            else
                continueTreeSolo(newNode.left, node1.left);
        }
        newNode.val = node1.val + node2.val;
        if (node1.right != null || node2.right != null) {
            newNode.right = new Node(0);
            if (node1.right != null && node2.right != null)
                createTree(newNode.right, node1.right, node2.right);
            else if (node1.right == null)
                continueTreeSolo(newNode.right, node2.right);
            else
                continueTreeSolo(newNode.right, node1.right);
        }
    }

    public void continueTreeSolo(Node newNode, Node node1) {
        if (!node1.isLeaf()) {
            if (node1.left != null) {
                newNode.left = new Node(0);
                continueTreeSolo(newNode.left, node1.left);
            }
            newNode.val = node1.val;
            if (node1.right != null) {
                newNode.right = new Node(0);
                continueTreeSolo(newNode.right, node1.right);
            }
        } else {
            newNode.val = node1.val;
        }
    }


}