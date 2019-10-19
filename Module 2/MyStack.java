import java.util.LinkedList;

public class MyStack<E> {

    private LinkedList<E> queue;

    /*
     * Constructor
     */
    public MyStack() {
        this.queue = new LinkedList<>();
    }

    /*
     * push an element at top (remember, a stack is "Last In First Out")
     */
    public void push(E elem) {
        queue.add(elem);
        for (int i = 0; i < queue.size()-1; i++) {
            queue.add(queue.remove());
        }
    }

    /*
     * Return the top of the stack AND remove the retrieved element
     */
    public E pop() {
        return queue.remove();
    }

    /*
     * Return the top element of the stack, without removing it
     */
    public E peek() {
        return queue.peek();
    }

    /*
     * Tells if the stack is empty or not
     */
    public boolean empty() {
        return queue.isEmpty();
    }

}
