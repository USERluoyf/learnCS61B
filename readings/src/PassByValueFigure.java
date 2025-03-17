public class PassByValueFigure {
    public static void main(String[] args) {
        Walrus walrus = new Walrus(3300, 11);
        int x = 9;

        doStuff(walrus, x);
        System.out.println(walrus.weight);
        System.out.println(x);
    }
    public static class Walrus {
        int weight;
        int tuskSize;
        public Walrus(int weight, int tuskSize) {
            this.weight = weight;
            this.tuskSize = tuskSize;
        }
    }
    public static void doStuff(Walrus W, int x) {
        W.weight = W.weight - 100;
        x = x - 5;
    }

}
