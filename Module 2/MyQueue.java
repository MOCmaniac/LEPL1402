import java.util.Stack;

public class MyQueue<E> {

    Stack<E> s1;
    Stack<E> s2;

    private E front;

    /*
     * Constructor
     */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
        this.front = null;
    }

    /*
     * Push element x to the end of the queue (remember, a queue is FIFO)
     */
    public void enqueue(E elem) {
        s1.push(elem);
        if(front == null){
            front = elem;
        }
    }

    /*
     * Removes the front element of this queue
     */
    public E dequeue() {
        E elem;
        while (!s1.empty()) {
            s2.push(s1.pop());
        }
        elem = s2.pop();
        if(!s2.empty()) {
            front = s2.peek();
        } else {
            front = null;
        }
        while(!s2.empty()){
            s1.push(s2.pop());
        }
        return elem;
    }

    /*
     * Get the first element of this list but does not remove it
     */
    public E peek() {
        return front;
    }

    public boolean empty() {
        return s1.empty();
    }
}
