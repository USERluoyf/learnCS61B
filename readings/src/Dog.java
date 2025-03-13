public class Dog {
    public int weightInPounds;
    public Dog(int w) {
        weightInPounds = w;
    }
    // static method
    public static Dog maxDog(Dog d1, Dog d2) {
        if (d1.weightInPounds > d2.weightInPounds) {
            return d1;
        }
        return d2;
    }
    // non-static method
    public Dog minDog(Dog d) {
        if (this.weightInPounds < d.weightInPounds) {
            return this;
        }
        return d;
    }
    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("yipyyipy!");
        } else if (weightInPounds < 30) {
            System.out.println("Bark!");
        } else {
            System.out.println("woof!");
        }
    }
}
