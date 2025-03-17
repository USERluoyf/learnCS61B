public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f ,IntList r) {
        this.first = f;
        this.rest = r;
    }
    public int size() {
        if (rest == null) return 1;
        return 1 + rest.size();
    }
    public int get(int i) {
        IntList p = this;
        while (i != 0) {
            p = p.rest;
            i--;
        }
        return p.first;
    }
    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L = new IntList(3, L);
        L = new IntList(1, L);
        System.out.println(L.size());
        System.out.println(L.get(2));
    }
}
