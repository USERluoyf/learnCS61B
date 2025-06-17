import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;
public class ArrayDeque61B<T> implements Deque61B<T> {


    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;

    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Deque61B otherDeque) {
            if (this.size == otherDeque.size()) {
                for (int i = 0; i < this.size; i++) {
                    if ( this.get(i) != otherDeque.get(i)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(this.get(i));
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }


    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        public ArrayDequeIterator() {
            wizPos = 0;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T result = get(wizPos);
            wizPos++;
            return result;
        }
    }

    private void resizeUp(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        //System.arraycopy(items, nextFirst+1, newItems, newSize - (items.length-nextFirst), size);
        for (int i = 0; i < size; i++) {
            newItems[Math.floorMod(newSize-size+nextFirst+1+i, newSize)] = items[Math.floorMod(i+nextLast, size)];
        }
        items = newItems;
        nextFirst = nextFirst + newSize - size;
    }

    private void resizeDown(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newItems[Math.floorMod(i- items.length+newSize+nextFirst+1, newSize)] = items[Math.floorMod(i+nextFirst+1, items.length)];
        }
        nextFirst = nextFirst - (items.length - newSize);
        items = newItems;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resizeUp(items.length * 2);
        }
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size++;

    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resizeUp(items.length * 2);
        }
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size++;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            returnList.add(get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size > 16 && size < items.length * 0.25) {
            resizeDown(2 * size);
        }
        if (size == 0) {
            return null;
        }
        T result = items[Math.floorMod(nextFirst+1,items.length)];
        items[Math.floorMod(nextFirst+1, items.length)] = null;
        nextFirst = Math.floorMod(nextFirst + 1, items.length);
        size--;
        return result;
    }

    @Override
    public T removeLast() {
        if (size > 16 && size < items.length * 0.25) {
            resizeDown(2 * size);
        }
        if (size == 0) {
            return null;
        }
        T result = items[Math.floorMod(nextLast-1, items.length)];
        items[Math.floorMod(nextLast-1, items.length)] = null;
        nextLast = Math.floorMod(nextLast - 1, items.length);
        size--;
        return result;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[Math.floorMod(index + nextFirst + 1, items.length)];
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return items[nextFirst+1];
        }
        removeFirst();
        return getRecursive(index - 1);
    }
}
