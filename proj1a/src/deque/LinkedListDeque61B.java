package deque;

import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    public class Node {
        T item;
        Node prev;
        Node next;
        public Node(T x, Node p, Node n) {
            item = x;
            prev = p;
            next = n;
        }
    }
    private final Node sentinel;
    private int size;
    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

    }

    @Override
    public void addFirst(T x) {
        sentinel.next.prev = new Node(x, sentinel, sentinel.next);
        sentinel.next = sentinel.next.prev;
        if (size == 0) {
            sentinel.prev = sentinel.next;
        }
       size++;

    }

    @Override
    public void addLast(T x) {
        sentinel.prev.next = new Node(x, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        if (size == 0) {
            sentinel.next = sentinel.prev;
        }
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> result = new ArrayList<>();
        Node temp = sentinel;
        for (int i = 0; i < size; i++) {
            result.add(temp.next.item);
            temp = temp.next;
        }
        return result;
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
        if (size == 0) {
            return null;
        }
        T result = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return result;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T result = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return result;
    }

    @Override
    public T get(int index) {
        Node temp = sentinel;
        T result = null;
        for (int i = 0; i <= index && index <= size-1; i++) {
            result = temp.next.item;
            temp = temp.next;
        }
        return result;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
    private T getRecursiveHelper(Node temp, int index) {
        if (index == 0) {
            return temp.item;
        }
        return getRecursiveHelper(temp.next, index-1);
    }
}
