public class DogArrayDemo {
    public static void main(String[] args) {
        Dog[] dogs = new Dog[2];
        dogs[0] = new Dog(4);
        dogs[1] = new Dog(30);
        dogs[1].makeNoise();
    }
}
