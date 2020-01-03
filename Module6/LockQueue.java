import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockQueue {

    public final static int SIZE = 100;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public int head = 0;
    public int tail = 0;
    public final Integer[] cells = new Integer[SIZE];
    public int count = 0;

    // Check out the example at: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Condition.html

    public Integer dequeue() {
        lock.lock();
        try {
            while (empty()) {
                notEmpty.await();
            }
            Integer x = cells[head++ % SIZE]; // /!\ head++ % SIZE does head % SIZE first then head++ 
            count--;
            notFull.signalAll();
            return x;
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }
    }


    public void enqueue(Integer i) {
        lock.lock();
        try {
            while (full()) {
                notFull.await();
            }
            cells[tail++ % SIZE] = i; // /!\ tail++ % SIZE does tail % SIZE first then tail++ 
            count++;
            notEmpty.signalAll();
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
    }

    public boolean full() {
        return this.count == SIZE;
    }

    public boolean empty() {
        return this.head == this.tail;
    }

    public int size() {
        return this.tail - this.head;
    }

}
