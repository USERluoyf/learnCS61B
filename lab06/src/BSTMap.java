import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>  {
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     */
    private class BSTNode {
        K key;
        V value;
        BSTNode left, right;
        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private BSTNode root;

    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        if(!containsKey(key)) {
            size++;
        }
        root = putHelper(key, value, root);
    }

    private BSTNode putHelper(K key, V value, BSTNode node) {
        if (node == null) {
            return new BSTNode(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = putHelper(key, value, node.left);
        } else if (cmp > 0) {
            node.right = putHelper(key, value, node.right);
        } else {
            node.value = value;
        }
        return node;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    private V getHelper(K key, BSTNode node) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {
            return node.value;
        }
        if (key.compareTo(node.key) < 0) {
            return getHelper(key, node.left);
        }
        if (key.compareTo(node.key) > 0) {
            return getHelper(key, node.right);
        }
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(key, root);
    }

    public boolean containsKeyHelper(K key, BSTNode node) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) == 0) {
            return true;
        }
        if (key.compareTo(node.key) < 0) {
            return containsKeyHelper(key, node.left);
        }
        if (key.compareTo(node.key) > 0) {
            return containsKeyHelper(key, node.right);
        }
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new TreeSet<>();
        keySetHelper(root, keySet);
        return keySet;
    }

    private void keySetHelper(BSTNode node, Set<K> keySet) {
        if (node == null) {
            return;
        }
        keySetHelper(node.left, keySet);
        keySet.add(node.key);
        keySetHelper(node.right, keySet);
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        V valueToRemove = get(key);
        if (valueToRemove == null) {
            return null;
        }
        root = removeHelper(key, root);
        size--;
        return valueToRemove;
    }

    private BSTNode removeHelper(K key, BSTNode node) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeHelper(key, node.left);
        } else if (cmp > 0) {
            node.right = removeHelper(key, node.right);
        } else { // Found the node to remove
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // Node has two children
            BSTNode original = node;
            node = min(original.right);
            node.right = deleteMin(original.right);
            node.left = original.left;
        }
        return node;
    }

    private BSTNode min(BSTNode node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private BSTNode deleteMin(BSTNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }
    private class BSTMapIterator implements Iterator<K> {
        private Stack<BSTNode> stack;

        public BSTMapIterator() {
            stack = new Stack<>();
            pushLeft(root);
        }

        private void pushLeft(BSTNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BSTNode node = stack.pop();
            pushLeft(node.right);
            return node.key;
        }
    }
}
