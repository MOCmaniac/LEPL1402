/**
 * Question:
 *
 * You are asked to implement the abstract data type RecursiveStack below.
 *
 * Your implementation can be an inner static class or a class below.
 * The factory method "makeRecursiveStack" returns a new instance of your implementation
 *
 * Once it is done, copy-paste the complete file in Inginious also with the imports
 *
 *
 * Feel free to add classes, methods or fields in the class but do not modify
 * the signature of existing code
 *
 */




import java.util.Stack;
public interface RecursiveStack {
            /*
            Ici, on pouvait travailler avec un Stack du package Java et le mettre en variable d'instance
            de notre implémentation. ça nous permettait de passer tous les tests, c'est à dire créer une
            nouvelle référence dès qu'on faisait une opération.
            */

    /**
     * Factory method
     * @return a new instance of your implementation of RecursiveStack
     */
    public static RecursiveStack makeRecursiveStack() {


         return new MyStack(new Stack());
    }


    int size();

    int top();

    RecursiveStack removeTop();

    RecursiveStack addTop(int v);

    public class MyStack implements RecursiveStack{


        Stack stack;

        public MyStack(Stack stack){

            this.stack = stack;

        }

        @Override
        public int size() {
            return this.stack.size();
        }

        @Override
        public int top() {
            return (int) this.stack.peek();
        }

        @Override
        public RecursiveStack removeTop() {
            Stack newStack = (Stack) this.stack.clone();

            newStack.pop();

            return new MyStack(newStack);

        }

        @Override
        public RecursiveStack addTop(int v) {

            Stack newStack = (Stack) this.stack.clone();

            newStack.push(v);

            return new MyStack(newStack);
        }
    }

}






