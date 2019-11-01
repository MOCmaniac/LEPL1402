public class Cat extends Animal {

    // useful for the test function
    private final String forTestMethod = "Thinking";

    public Cat() {
        super("Cat");
    }

    public void act_forTestMethod() {
        super.act(forTestMethod);
    }

}
