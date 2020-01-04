import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StudentFunctions implements StudentStreamFunction {

    public Student findFirst(Stream<Student> studentsStream, Map<String, Predicate<?>> conditions){
        Student[] students = findAll(studentsStream, conditions);
        return (students.length > 0) ? students[0] : null;
    }

    public Student[] findAll(Stream<Student> studentsStream, Map<String, Predicate<?>> conditions){
        if(conditions.containsKey("firstName")){
            Predicate<String> firstNameTest = (Predicate<String>) conditions.get("firstName");
            studentsStream = studentsStream.filter(std -> firstNameTest.test(std.getFirstName()));
        }
        if(conditions.containsKey("lastName")){
            Predicate<String> firstNameTest = (Predicate<String>) conditions.get("lastName");
            studentsStream = studentsStream.filter(std -> firstNameTest.test(std.getLastName()));
        }
        if(conditions.containsKey("section")){
            Predicate<Integer> firstNameTest = (Predicate<Integer>) conditions.get("section");
            studentsStream = studentsStream.filter(std -> firstNameTest.test(std.getSection()));
        }
        if(conditions.containsKey("courses_results")){
            Predicate<Map<String, Double>> firstNameTest = (Predicate<Map<String, Double>>) conditions.get("courses_results");
            studentsStream = studentsStream.filter(std -> firstNameTest.test(std.getCourses_results()));
        }

        return studentsStream.toArray(Student[]::new);
    }

    public boolean exists(Stream<Student> studentsStream,
                          Map<String, Predicate<?>> conditions,
                          int n)
    {
        return findAll(studentsStream, conditions).length >= n;
    }

    public Student[] filterThenSort(Stream<Student> studentsStream,
                                    Map<String, Predicate<?>> conditions,
                                    Comparator<Student> comparator)
    {
        return findAll(studentsStream.sorted(comparator), conditions);
    }

}
