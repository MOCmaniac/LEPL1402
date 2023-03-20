public class SharedCounter {

    private int counter = 0;

    // Increment once the counter
    synchronized void inc(){
        counter++;
        notify();
    }

    // Decrement the counter if and only if its current value is positive. In fact, the counter we ask you to implement
    // must always be positive. If a thread wants to decrement the counter but its value is 0, it has to wait.
    synchronized void dec(){
        while(counter <= 0) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        counter--;
    }

    // Returns the current value of the counter
    synchronized int get(){
        return counter;
    }

}
