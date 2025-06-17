public class Animal {
    public String name, noise;
    public Animal(String name) {
        this.name = name;
        this.noise = "Huh?";
    }
    public void greet(Animal a) {
        System.out.println("Hi " + a.name + ", I'm " + this.name);
    }
    public void play() {
        System.out.println("I love to play! " + this.noise);
    }
    public static void sleep() {
        System.out.println("I'm sleeping!");
    }

    public class Cat extends Animal {
        public Cat(String name) {
            super(name);
            this.noise = "Meow!";
        }
        public void greet(Animal a) {
            System.out.println("Cat " + name + " says: " + noise);
        }
        public void play() {
            System.out.println("Woo it is too much fun being a cat. " + this.noise);
        }
    }

    public class Dog extends Animal {
        public Dog(String name) {
            super(name);
            this.noise = "Woof!";
        }
        public void greet(Animal a) {
            System.out.println("Dog " + name + " says: " + noise);
        }
        public void play() {
            System.out.println("Woo it is too much fun being a dog." + this.noise);
        }
        public static void sleep() {
            System.out.println("I love napping!");
        }
    }


}
