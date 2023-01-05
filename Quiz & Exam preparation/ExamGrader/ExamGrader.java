public class ExamGrader {
    public interface RoundingFunction {
        int roundGrade(double grade);
    }


    public static class ExamQuestion {
        public double pointsObtained;
        public ExamQuestion nextQuestion;

        public ExamQuestion(double points, ExamQuestion next) {
            this.pointsObtained = points;
            this.nextQuestion = next;
        }

    }

    /**
     *  Write a method calculateExamGrade that calculates the grade
     *  obtained for an exam. The grade is the sum of the points
     *  obtained in all questions. The questions are provided in a
     *  linked list (class ExamQuestion).
     *
     *  However, the points are real numbers, while the exam grade
     *  is a natural number. To round the grade, the caller of this
     *  method provides a rounding function that you have to use
     *  to obtain the final result.
     *
     *  Look at the test "testWithTwoQuestions" to see how users will
     *  call this method.
     *
     *  You can assume that all points are positive numbers and that
     *  the list of questions is not empty.
     */
    public static int calculateExamGrade(ExamQuestion questions, RoundingFunction roundingFunction) {
        ExamQuestion current = questions;
        double total = 0.0;
        while (current!=null){
            total+=current.pointsObtained;
            current = current.nextQuestion;
        }
        return roundingFunction.roundGrade(total);
    }


    /**
     * Write a method gradeExams that calculates the grades of two exams
     * using two threads to accelerate the grading (one exam graded per thread).
     * The method must return the two grades in an int array.
     * Like for the method calculateExamGrade, the caller of this method
     * provides a rounding function.
     *
     * Look at the test "testTwoShortExams" to see how users will
     * call this method.
     *
     * You MUST use threads (or a threadpool).
     * Catch (and ignore) all exceptions.
     */
    public static int[] gradeExams(ExamQuestion exam1, ExamQuestion exam2, RoundingFunction roundingFunction) {
         class MyRunnable implements Runnable{

             final ExamQuestion question;
             int result;
            MyRunnable(ExamQuestion question){
                this.question = question;
            }
             @Override
             public void run() {
                 result = calculateExamGrade(question,roundingFunction);
             }
         }

         MyRunnable run1 = new MyRunnable(exam1);
         MyRunnable run2 = new MyRunnable(exam2);
         Thread t1 = new Thread(run1);
         Thread t2 = new Thread(run2);
         t1.start();
         t2.start();
         try {
             t1.join();
             t2.join();
         } catch (InterruptedException e){
         }

         return new int[]{run1.result, run2.result};
    }
}
