public class Maxmizer {
    public static Comparable max(Comparable[] items) {
        int maxDex = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[maxDex].compareTo(items[i]) < 0) {
                maxDex = i;
            }
        }
        return items[maxDex];
    }

    public static void main(String[] args) {
        Dog1[] dogs = {new Dog1("aka", 2), new Dog1("bka", 3), new Dog1("cka", 4)};
        Dog1 maxDog = (Dog1) max(dogs);
        System.out.println(maxDog.size());
    }
}
