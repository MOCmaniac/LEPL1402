import java.util.ArrayList;
import java.util.List;

/**
 * This class represents two assistants who grade homeworks.
 * Grading one homework takes around one second. But since the
 * two assistants split their work, they can grade twice as fast.
 * That means grading six homeworks should take around 3 seconds.
 * You should implement the assistants as two separate threads.
 *
 * You MUST use the basic Thread class.
 * DO NOT use futures and thread pools.
 *
 * Avoid race conditions.
 * Catch exceptions (and ignore them).
 */
public class HomeworkGrading {
    Thread a1;
    Thread a2;
    ArrayList<Integer> grade = new ArrayList<>();
    boolean exit = false;

    private boolean isAlreadyUsed;


    // This is a homework to grade.
    // A homework contains several errors and takes around one second to grade.
    // Do not change this class.
    public static class Homework {
        private int numErrors;

        public Homework(int numErrors) {
            this.numErrors = numErrors;
        }

        public int grade() {
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            return 20-numErrors;
        }
    }

    public class MyRunnable implements Runnable {
        List<Homework> list;

        MyRunnable(List<Homework> toCorrect){
            list = toCorrect;
        }
        @Override
        public void run() {
            for (Homework work : list) {
                if(!exit){
                    int result = work.grade();
                    synchronized (grade){
                        grade.add(result);
                    }
                }
            }
        }
    }

    /**
     * Starts grading a list of homeworks.
     * This method will use two threads (the two assistants) to grade the homeworks.
     * Both assistants should more or less grade half of the homeworks.
     * This method returns immediately. It does NOT wait for the
     * two assistants to finish grading.
     *
     * You can assume that this method will only be called once.
     * You can assume that the list is not empty.
     */
    public void startGrading(List<Homework> homeworks) {
        // this method can only be called once
        if(isAlreadyUsed) {
            return;
        }
        isAlreadyUsed=true;

        if(homeworks.size()==1){
            a1 = new Thread(new MyRunnable(homeworks));
            a2 = new Thread(new MyRunnable(new ArrayList<>()));
        } else {
            a1 = new Thread(new MyRunnable(homeworks.subList(0, homeworks.size() / 2)));
            a2 = new Thread(new MyRunnable(homeworks.subList(homeworks.size() / 2,homeworks.size())));
        }

        a1.start();
        a2.start();
    }

    /**
     * This method waits until the assistants have finished their work.
     */
    public void waitForGrading() {
        try {
            a1.join();
            a2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method returns the current number of homeworks that have been
     * graded so far.
     * It is allowed to call this method while the assistants are still
     * working. In that case, the method returns the number of homeworks that
     * have been already finished so far.
     */
    public int getNumberOfFinishedGradedHomeworks() {
        return grade.size();
    }

    /**
     * This method returns the current average grade of the homeworks
     * graded so far.
     * It is allowed to call this method while the assistants are still
     * working. In that case, the method returns the average of the homeworks that
     * have been already finished so far.
     */
    public int getAverageGrade() {
        if(getNumberOfFinishedGradedHomeworks()==0){
            return 0;
        }
        return grade.stream().reduce(0,Integer::sum)/getNumberOfFinishedGradedHomeworks();
    }

    /**
     * This methods tells the assistants to stop grading.
     * The assistants will finish grading the homeworks they are currently
     * working on and then they will stop and not grade anymore the
     * remaining homeworks.
     */
    public void cancelGrading() {
        exit = true;
    }
}
