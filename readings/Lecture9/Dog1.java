import java.util.Comparator;

public class Dog1 implements Comparable<Dog1>{
    private String name;
    private int size;
    public Dog1(String n, int s) {
        name = n;
        size = s;
    }
    public int size() {
        return size;
    }
    public String name() {
        return name;
    }

    public int compareTo(Dog1 o) {
        return this.size - o.size;
    }

    private static class NameComparator implements Comparator<Dog1> {
        public int compare(Dog1 a, Dog1 b) {
            return a.name.compareTo(b.name);
        }
    }
    public static Comparator<Dog1> getNameComparator() {
        return new NameComparator();
    }
}
