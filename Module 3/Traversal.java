import java.util.List;
import java.util.Stack; // this should give you a hint for the iterative version

public class Traversal {

    public static void recursiveInorder(Node root, List<Integer> res) {
        if (root.left != null) {
            recursiveInorder(root.left, res);
        }
        res.add(root.val);
        if (root.right != null) {
            recursiveInorder(root.right, res);
        }
    }

    public static void iterativeInorder(Node root, List<Integer> res) {
        Stack<Node> s = new Stack<>();
        Node currentNode = root;

        while(!s.empty() || currentNode != null){
            while(currentNode.left != null){
                s.push(currentNode);
                currentNode = currentNode.left;
            }
            res.add(currentNode.val);
            currentNode = s.pop();
            res.add(currentNode.val);
            currentNode = currentNode.right;
        }
    }

    // https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
    public static void iterativeInorderFromGFG(Node root, List<Integer> res) {
        Stack<Node> s = new Stack<>();
        Node currentNode = root;

        while(s.size() > 0 || currentNode != null){
            while(currentNode != null){
                s.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = s.pop();
            res.add(currentNode.val);
            currentNode = currentNode.right;
        }
    }

}
