import org.w3c.dom.ls.LSOutput;

public class Test {

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();

        System.out.println("Test function empty :");
        System.out.println("\tWhen created : " + (stack.empty() ? "Ok" : "You messed up bro"));
        stack.push(42);
        stack.push(15);
        System.out.println("\tPushing elements : " + (!stack.empty() ? "Ok" : "You pushed elements, stack is not empty !"));
        stack.pop();
        stack.pop();
        System.out.println("\tPoping elements : " + (stack.empty() ? "Ok\n" : "You poped all elements, stack is empty !\n"));

        System.out.println("Doing SMALL tests on your peek, pop and push functions");

        for (int i = 0; i <= 5; i++) {
            stack.push(i);
        }

        for (int i = 5; i >= 0; i--) {
            int peekResult = stack.peek();
            int popResult = stack.pop();
            printTestResult(i, peekResult, popResult);
        }

        System.out.print("\n\nDoing BIG tests on your peek, pop and push functions : ");
        int[] value = new int[200];

        for (int i = 0; i < value.length; i++) {
            int x = (int) (Math.random() * 1000 - 500);
            stack.push(x);
            value[i] = x;
        }

        boolean ok = true;
        for (int i = value.length - 1; i >= 0 && ok; i--) {
            int peekResult = stack.peek();
            int popResult = stack.pop();
            //printTestResult(value[i], peekResult, popResult);
            ok = peekResult == value[i] && popResult == value[i];
        }
        if (ok) {
            System.out.println("PASSED\n");
        } else {
            System.out.println("FAILED\n");
        }
    }

    public static void printTestResult(int expected, int fromPeek, int fromPop) {
        System.out.print("\nExpected : " + expected + "\tFrom \"peek\" : " + fromPeek);
        System.out.print("\tFrom \"pop\" : " + fromPop);
        if (fromPeek != expected || fromPop != expected) {
            System.out.print("\tFAIL !");
        }
    }
}
