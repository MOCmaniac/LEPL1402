public class Test {
    public static void main(String[] args) {
        Cat cat = new Cat(/* ... */);
        Dog dog = new Dog(/* ... */);
        FrenchBouledogue fb = new FrenchBouledogue(/* ... */);
        Dog dog2 = (Dog) fb;

        System.out.println(cat.shout());
        System.out.println(dog.shout());
        System.out.println(fb.shout());
        System.out.println(dog2.shout());
        test2();
    }

    public static void test2(){
        Cow cow = new Cow(/*...*/);
        Cat cat = new Cat(/*...*/);

        Mammal cowMammal = (Mammal) cow;
        Mammal catMammal = (Mammal) cat;

        System.out.println(cow.canJump());
        System.out.println(cat.canJump());
        System.out.println(cowMammal.canJump());
        System.out.println(catMammal.canJump());
    }

    /*public static void test3(){
        // This is in your main
        Cat c = new Cat(*//*....*//*);
        FrenchBouledogue fb = new FrenchBouledogue(*//*...*//*);
        Dog d = (Dog) fb;
        Fish f = new Fish(*//*...*//*);

        Q4.printHeight(c);
        Q4.printHeight(fb);
        Q4.printHeight(d);
        Q4.printHeight(f);
    }*/

}
