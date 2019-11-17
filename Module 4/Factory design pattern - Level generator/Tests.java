public class Tests {
    public static void main(String[] args) {
        System.out.println("The following strings should be equals !");

        String s1 = "#-K\n-D-\n#-K\n";
        System.out.println(s1);

        Level l = new Level(s1);
        String s2 = l.toString();
        System.out.println(s2);
        System.out.println("Are they equals ? " + s1.equals(s2));
    }
}
