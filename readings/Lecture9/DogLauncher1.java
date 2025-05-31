import java.util.Comparator;

public class DogLauncher1 {
    public static void main(String[] args) {
        Dog1 d1 = new Dog1("ipx", 711);
        Dog1 d2 = new Dog1("fsdss", 943);
        Dog1 d3 = new Dog1("HIKR", 202);
        Dog1 d4 = new Dog1("fns", 011);


        Comparator<Dog1> nc =  Dog1.getNameComparator();
        if (nc.compare(d1, d2) > 0) { // if d1 comes later than d2 in alphabet.
            System.out.println(d2.name() + "-" + d2.size());
        } else {
            System.out.println(d1.name() + "-" + d1.size());
        }
    }
}
