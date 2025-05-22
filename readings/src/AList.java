public class AList<Glorp> {

    private int size;
    private Glorp[] items;

    public AList() {
        items = (Glorp[]) new Object[100];
        size = 0;
    }

    private void resize(int capacity) {
        Glorp[] a = (Glorp[])new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public void addLast(Glorp x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size += 1;
    }

    public Glorp getLast() {
        return items[size-1];
    }

    public Glorp get(int i) {
        if (i >= items.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return items[i];
    }
    public int size() {
        return size;
    }
    public Glorp removeLast() {
        Glorp x = items[size-1];
        items[size-1] = null;
        size -= 1;
        return x;
    }
}
