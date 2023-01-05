//CETTE VERSION EST PLUS COMPLIQUEE, IL EST POSSIBLE DE FAIRE PLUS SIMPLE

import java.util.*;

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
 *
 */
public class HomeworkGrading {
    Thread a1;
    Thread a2;
    MyRunnable run1;
    MyRunnable run2;

    volatile boolean exit = false;
    // You are allowed to add methods or class members, but do not change the signature
    // of the existing methods and class members.

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
        List<Homework> toCorrect;
        int corrected = 0;
        int total = 0;

        public void setToCorrect(List<Homework> toCorrect){
            this.toCorrect = toCorrect;
        }

        @Override
        public synchronized void run() {
            for (Homework work : toCorrect) {
                if(!exit){
                    total+=work.grade();
                    corrected++;
                } else{
                    return;
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
            run1 = new MyRunnable();
            run1.setToCorrect(homeworks);
            a1 = new Thread(run1);
            a1.start();
            run2 = new MyRunnable();
            run2.setToCorrect(new ArrayList<>());
            a2 = new Thread(run2);
            return;
        }

        ArrayList<Homework> first = new ArrayList<>();
        ArrayList<Homework> second = new ArrayList<>();

        //Sublist c'est plus simple mais je l'ai codé ok
        for (int i = 0; i < homeworks.size(); i++) {
            if(i<homeworks.size()/2){
                first.add(homeworks.get(i));
            } else{
                second.add(homeworks.get(i));
            }
        }

        run1 = new MyRunnable();
        run1.setToCorrect(first);
        run2 = new MyRunnable();
        run2.setToCorrect(second);

        a1 = new Thread(run1);
        a2 = new Thread(run2);

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
        return run1.corrected + run2.corrected;
    }

    /**
     * This method returns the current average grade of the homeworks
     * graded so far.
     * It is allowed to call this method while the assistants are still
     * working. In that case, the method returns the average of the homeworks that
     * have been already finished so far.
     */
    public int getAverageGrade() {
        if(getNumberOfFinishedGradedHomeworks()!=0){
            return (run1.total+run2.total)/getNumberOfFinishedGradedHomeworks();
        }
        return 0;
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
