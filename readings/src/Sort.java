public class Sort {
    public static void sort(String[] x) {
        sort(x, 0);
    }
    private static void sort(String[] x, int start) {
        if (start == x.length) {
            return;
        }
        int smallest = findSmallest(x, start);

        swap(x, start, smallest);
        sort(x, start + 1);
    }
    // find smallest string
    public static int findSmallest(String[] x, int start) {
        int smallest = start;
        for (int i = start; i < x.length; i++) {
            int cmp = x[i].compareTo(x[smallest]);
            if (cmp < 0) {
                smallest = i;
            }
        }
        return smallest;
    }

    public static void swap(String[] x, int a, int b) {
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }
}
