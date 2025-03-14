public class Dessert {
    int flavor;
    int price;
    static int numDesserts = 0;
    public Dessert(int flavor, int price) {
        this.flavor = flavor;
        this.price = price;
        Dessert.numDesserts++;
    }
    public void printDessert() {
        System.out.println(String.valueOf(this.flavor) + " " + String.valueOf(this.price) + " " + String.valueOf(numDesserts));
    }
    public static void main(String[] args) {
        System.out.println("I love dessert!");
        /*
        Dessert dessert = new Dessert(150, 100);
        dessert.printDessert();
        Dessert dessert2 = new Dessert(150, 100);
        dessert2.printDessert();
        */
    }
}
