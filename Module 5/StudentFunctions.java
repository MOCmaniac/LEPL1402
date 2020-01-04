import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StudentFunctions implements StudentStreamFunction {

    public Stream<Student> findSecondAndThirdTopStudentForGivenCourse(Stream<Student> studentStream, String name) {
        Student[] students =  studentStream.sorted(Comparator.comparingDouble(std -> std.getCoursesResults().get(name))).toArray(Student[]::new);
        return Stream.of(students[students.length - 2], students[students.length - 3]);
    }

    public Object[] computeAverageForStudentInSection(Stream<Student> studentStream, int section) {
        return studentStream.filter(std -> std.getSection() == section).sorted(Comparator.naturalOrder()).
                map(std -> new Object[]{
                        String.format("Student %s %s", std.getFirstName(), std.getLastName()),
                        std.getCoursesResults().values().stream().reduce(0.0, Double::sum)
                                / std.getCoursesResults().size()}).toArray();
    }

    public int getNumberOfSuccessfulStudents(Stream<Student> studentStream) {
        return (int) studentStream.filter(std ->
        {Stream<Double> success = std.getCoursesResults().values().stream().filter(n -> n > 10);
        return success.count() == std.getCoursesResults().size();}
        ).count();
    }

    public Student findLastInLexicographicOrder(Stream<Student> studentStream) {
        return studentStream.max(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName)).get();
    }

    public double getFullSum(Stream<Student> studentStream) {
        return studentStream.map(st -> st.getCoursesResults().values().stream().reduce(0.0, Double::sum)).reduce(0.0, Double::sum);
    }
}
