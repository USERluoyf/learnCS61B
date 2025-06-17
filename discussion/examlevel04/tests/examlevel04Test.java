import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class examlevel04Test {

    @Test
    @DisplayName("AlienComparator: Basic comparison")
    public void testAlienComparator() {
        AlienComparator ac = new AlienComparator("hlabcdefgijkmnopqrstuvwxyz");
        assertThat(ac.compare("hello", "habc")).isGreaterThan(0);
        assertThat(ac.compare("word", "world")).isGreaterThan(0);
        assertThat(ac.compare("apple", "apply")).isLessThan(0);
    }

    @Test
    @DisplayName("AlienComparator: One word is a prefix of the other")
    public void testAlienComparatorPrefix() {
        AlienComparator ac = new AlienComparator("hlabcdefgijkmnopqrstuvwxyz");
        assertThat(ac.compare("app", "apple")).isLessThan(0);
        assertThat(ac.compare("apple", "app")).isGreaterThan(0);
    }

    @Test
    @DisplayName("AlienComparator: With empty strings")
    public void testAlienComparatorEmpty() {
        AlienComparator ac = new AlienComparator("hlabcdefgijkmnopqrstuvwxyz");
        assertThat(ac.compare("", "a")).isLessThan(0);
        assertThat(ac.compare("a", "")).isGreaterThan(0);
        assertThat(ac.compare("", "")).isEqualTo(0);
    }

    @Test
    @DisplayName("IteratorOfIterators: Basic functionality")
    public void testIteratorOfIterators() {
        List<Integer> l1 = Arrays.asList(1, 2, 3);
        List<Integer> l2 = Arrays.asList(4, 5);
        List<Integer> l3 = Arrays.asList(6, 7, 8);
        List<Iterator<Integer>> iterators = new ArrayList<>();
        iterators.add(l1.iterator());
        iterators.add(l2.iterator());
        iterators.add(l3.iterator());

        IteratorOfIterators it = new IteratorOfIterators(iterators);
        List<Integer> result = new ArrayList<>();
        while (it.hasNext()) {
            result.add(it.next());
        }
        assertThat(result).containsExactly(1, 4, 6, 2, 5, 7, 3, 8).inOrder();
    }

}
