import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Question:
 *
 * You are asked to retrieve the elements of the binary search tree
 * in decreasing order (see the TODO below)
 *
 * Once it is done, copy-paste the complete class in Inginious also with the imports
 *
 *
 * A Binary Search Tree is a Data Structure that represents
 * a set (no duplicates thus) of integers stored such that
 * for a given node, keys in the left subtree are all smaller that the key in the node
 *                   keys in the right right subtree are larger than the key in the node
 *
 * Your algorithm should have a time complexity of Theta(n)
 * where n is the number of elements in the set.
 *
 * Feel free to add methods or fields in the class but do not modify
 * the signature and behavior of existing code
 *
 */
public class BinarySearchTree {
    
    private Node root;             // root of BST

    private class Node {

        private int key;           // sorted by key
        private Node left, right;  // left and right subtrees

        public Node(int key) {
            this.key = key;
        }
    }


    public BinarySearchTree() {
        
    }
 
    public void insert(int key) {
        root = put(root, key);
    }

    private Node put(Node x, int key) {
        if (x == null) return new Node(key);
        if      (key < x.key) x.left  = put(x.left,  key);
        else if (key > x.key) x.right = put(x.right, key);
        return x;
    }

    /**
     * Return the list that contains the elements in decreasing order
     * The complexity should be Theta(n) where n is the number of elements in the set.
     * @return
     */

    public List<Integer> toList(Node node) {
        List<Integer> retList = new ArrayList<>();
        if (node.left == null && node.right == null) {
            retList.add(node.key);
        } else if (node.left == null){
            retList.addAll(toList(node.right));
            retList.add(node.key);
        } else if (node.right == null) {
            retList.add(node.key);
            retList.addAll(toList(node.left));
        } else {
            retList.addAll(toList(node.right));
            retList.add(node.key);
            retList.addAll(toList(node.left));
        } return retList;
    }

    public List<Integer> decreasing() {
        if (root == null) return null;
        return toList(root);
    }
}
