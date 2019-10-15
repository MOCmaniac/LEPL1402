public class Fibonacci {

    public static void main(String[] args) {
        // More than 46 iteration will result in a int overflow
        for (int i = 0; i < 47; i++) {
            int a = fiboRecursive(i);
            int b = fiboIterative(i);
            boolean equal = a == b;
            System.out.println("Iteration " + i + "\tR = " + a + "\tI = " + b + "\t" + equal);
        }
    }

    public static int fiboRecursive(int index) {
        if (index < 2) {
            return index;
        }
        return fiboRecursive(index - 1) + fiboRecursive(index - 2);
    }


    public static int fiboIterative(int index) {
        if (index < 2) {
            return index;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= index; i++) {
            int temp = b;
            b += a;
            a = temp;
        }
        return b;
    }
}
