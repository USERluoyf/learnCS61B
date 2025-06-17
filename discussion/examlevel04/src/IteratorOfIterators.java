import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorOfIterators implements Iterator {
    private List<Iterator<Integer>> iterators;
    private int curr;
    public IteratorOfIterators(List<Iterator<Integer>> a) {
        iterators = new LinkedList<>();
        for (Iterator<Integer> i : a) {
            if (i.hasNext()) {
                iterators.add(i);
            }
        }
        curr = 0;
    }

    @Override
    public boolean hasNext() {
        return curr < iterators.size();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Iterator<Integer> curriterator = iterators.get(curr);
        int result = curriterator.next();
        if (!curriterator.hasNext()) {
            iterators.remove(curr);
        } else {
            curr = (curr + 1) % iterators.size();
        }
        return result;
    }


}
