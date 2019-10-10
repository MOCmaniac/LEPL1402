import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Person {

    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }

    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("Guillaume",20));
        persons.add(new Person("John",50));
        persons.add(new Person("Guillaume",10));
        persons.add(new Person("John",10));
        persons.add(new Person("Luc",5));

        System.out.println(persons);
        sortPerson(persons);
        System.out.println(persons);
    }

    public static void sortPerson(ArrayList<Person> persons){
        class sortPerson implements Comparator<Person>{
            @Override
            public int compare(Person p1, Person p2) {
                if(p1.name == p2.name){
                    return p1.age - p2.age;
                } else{
                    return p1.name.compareTo(p2.name);
                }
            }
        }
        Collections.sort(persons,new sortPerson() );
    }
}
