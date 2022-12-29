import java.util.*;

public class AgentSimulation {

    /***** IMPORTANT *************************************************/
    // feel free to modify the content of the classes and the methods
    // and add new instance variables
    // But do not change the existing instance variables and
    // the signature of the existing methods
    /*****************************************************************/


    public interface ContaminationListener {
        /**
         * Notifies that a new person is contaminated
         * @param p the new contaminated person
         */
        void contaminated(Person p);
    }


    final static int INCUBATION_DURATION = 1;
    final static int CONTAMINATION_DURATION = 4;

    private int t = 0;

    public final Person [] population;
    public final List<int[]> contacts;
    private final List<ContaminationListener> listeners = new ArrayList<>();

    public AgentSimulation(int populationSize, List<int []> contacts) {
        population = new Person[populationSize];
        for (int i = 0; i < population.length; i++) {
            population[i] = new Person(i,false);
        }
        this.contacts = new LinkedList<>();
        this.contacts.addAll(contacts);
    }

    public void addContaminationListener(ContaminationListener l) {
        listeners.add(l);
    }

    public int currentTime() {
        return t;
    }


    public void simulateOneDay() {
        for (int [] contact: contacts) {
            Person p1 = population[contact[0]];
            Person p2 = population[contact[1]];

            if (p1.isContagious() || p2.isContagious()) {
                if(p1.contaminateUnlessImmune()) {
                    p1.addPersonInChain(p2);
                }
                if(p2.contaminateUnlessImmune()) {
                    p2.addPersonInChain(p1);
                }
            }

        }
        t++;
    }

    public void simulate(int numberOfDays) {
        // simulate all the bubble for a given number of days
        for (int d = 0; d < numberOfDays; d++) {
            simulateOneDay();
        }
    }


    public class Person {

        int endContagious = -1;
        int startContagious = -1;
        public final int id;
        private final LinkedList<Person> contaminationChain = new LinkedList<>();

        public Person(int id, boolean immune) {
            this.id = id;
            if (immune) {
                endContagious = -2;
            }
        }

        /**
         * Returns the chain of contamination from the origin up to this person (excluded)
         * The chain is empty if the person is not yet contaminated, and should never contain this person.
         * @return the chain of contamination from the origin up to this person
         */
        public List<Person> chainOfContamination() {
            return new LinkedList<>(contaminationChain);
        }


        boolean isContaminated() {
            int t = currentTime();
            return t < endContagious;
        }

        boolean isContagious() {
            int t = currentTime();
            return startContagious <= t && isContaminated();
        }

        boolean isImmune() {
            return endContagious != -1 && !isContaminated();
        }

        /**
         * Return true if the person was not yet immune and not yet contaminated
         */
        public boolean contaminateUnlessImmune() {
            if (!isImmune() & !isContaminated()) {
                startContagious = currentTime() + INCUBATION_DURATION;
                endContagious = currentTime() + CONTAMINATION_DURATION;
                AgentSimulation.this.listeners.forEach(l -> l.contaminated(this));
                return true;
            }
            return false;
        }

        public void addPersonInChain(Person p1) {
            contaminationChain.addAll(p1.contaminationChain);
            contaminationChain.add(p1);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", endContagious=" + endContagious +
                    ", startContagious=" + startContagious +
                    '}';
        }

    }
}
