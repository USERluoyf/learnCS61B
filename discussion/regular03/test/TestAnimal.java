public class TestAnimal {
    public static void main(String[] args) {
        Animal outer = new Animal("x");
        Animal a = outer.new Dog("Pluto");
        Animal b = new Animal("bear");
        Animal.Cat c = outer.new Cat("Garfield");
        Animal.Dog d = outer.new Dog("Lucky");

        Cat e = new Animal("kitty");
        a.greet(c);
        a.sleep();
        c.play();
        c.greet(d);
       ((Animal) c).greet(d);
       d.sleep();
       a = c;
       a.play(14);
       ((Cat) b).play();
       d = (Dog) a;
       c = a;


    }
}
