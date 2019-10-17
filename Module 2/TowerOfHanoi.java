import java.util.Stack;

public class TowerOfHanoi {

    public static void main(String[] args) {
        towerOfHanoiString(3, "A", "B", "C");
    }

    /*
     * we want the tower to be moved from a to b.
     * The first value of n is the size of the stack.
     */
    public static void towerOfHanoi(int n, Stack<Disk> a, Stack<Disk> b, Stack<Disk> c) {
        if (n == 1) {
            b.push(a.pop());
        } else {
            towerOfHanoi(n - 1, a, c, b);
            b.push(a.pop());
            towerOfHanoi(n - 1, c, b, a);
        }
    }

    public static int numberOfMoves(int stackSize) {
        return (int) Math.pow(2, stackSize) - 1;
    }

    public static void towerOfHanoiString(int n, String start, String end, String auxiliary) {
        if (n == 1) {
            System.out.println(start + " -> " + end);
        } else {
            towerOfHanoiString(n - 1, start, auxiliary, end);
            System.out.println(start + " -> " + end);
            towerOfHanoiString(n - 1, auxiliary, end, start);
        }
    }

}
