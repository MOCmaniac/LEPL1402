public class Launcher {

    /*
     * Instantiate and start each thread from "t" with its OWN Counter object,
     * then wait for all threads to finish and return the set of Counter objects
     * the threads ran on. Each thread must be named according to its index in the
     * "t" array.
     */
    public static Counter[] init(Thread[] t) {
        Counter[] counters = new Counter[t.length]; // Stock all counters to retrieve them later
        for (int x = 0; x < t.length; x++) {
            counters[x] = new Counter();
            t[x] = new Thread(counters[x], Integer.toString(x));
            t[x].start();
        }

        for (Thread thread : t) { // Wait for the threats to finish
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
        }
        return counters;
    }
}
