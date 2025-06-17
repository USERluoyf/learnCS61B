import java.util.ArrayDeque;
import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {

    Comparator<T> cmp;

    public MaxArrayDeque61B(Comparator<T> cp) {
        super();
        cmp = cp;

    }

    public T Max() {
        if (isEmpty()) {
            return null;
        }
        T t = get(0);
        for (int i = 0; i < size(); i++) {
            if (cmp.compare(get(i), t) > 0) {
                t = get(i);
            }
        }
        return t;
    }

    public T Max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T t = get(0);
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), t) > 0) {
                t = get(i);
            }
        }
        return t;
    }
}
