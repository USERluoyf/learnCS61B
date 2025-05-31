import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size;
    public ArraySet() {
        items = (T[]) new Object[10];
        size = 0;
    }
    public boolean contains(T t) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    public void add(T t) {
        if (!contains(t)) {
            items[size] = t;
            size++;
        }
    }

//    @Override
//    public String toString() {
//        String result = "{";
//        for (T item : this) {
//            result += item.toString();
//            result += ", ";
//        }
//        return result + "}";
//    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(items[i]);
            sb.append(", ");
        }
        return sb.toString();
    }

    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int i;
        public ArraySetIterator() {
            i = 0;
        }
        public T next() {
            T result = items[i];
            i++;
            return result;
        }
        public boolean hasNext() {
            return (i < size);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArraySet oas) {
            if (oas.size != this.size) {
                return false;
            }

            for (T i: this) {
                if (!oas.contains(i)) {
                    return false;
                }

            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(1);
        aset.add(1);
        aset.add(2);
        aset.add(3);
        aset.add(4);

//        Iterator<Integer> aseer = aset.iterator();
//        while (aseer.hasNext()) {
//            System.out.println(aseer.next());
//        }
        for (int i : aset) {
            System.out.println(i);
        }
        System.out.println(aset);
        System.out.println(aset.equals(aset2));
    }

}
