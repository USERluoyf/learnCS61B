public class DogLauncher {
    public static void main(String[] args) {
        Dog d1;
        Dog d2;
        d1 = new Dog(9);
        d2 = new Dog(25);
        Dog.maxDog(d1, d2).makeNoise();// call static method
        d1.minDog(d2).makeNoise();// call non-static method

    }
}
