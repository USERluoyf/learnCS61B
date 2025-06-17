import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    @DisplayName("A new ArrayDeque61B should be empty and have size 0")
    void testIsEmptyAndSizeInitial() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        assertThat(ad.isEmpty()).isTrue();
        assertThat(ad.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Adding items should update size and isEmpty")
    void testAddUpdatesSize() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("a");
        assertThat(ad.isEmpty()).isFalse();
        assertThat(ad.size()).isEqualTo(1);

        ad.addLast("b");
        assertThat(ad.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Removing items should update size and isEmpty")
    void testRemoveUpdatesSize() {
        ArrayDeque61B<Character> ad = new ArrayDeque61B<>();
        ad.addLast('x');
        ad.addLast('y');

        ad.removeFirst();
        assertThat(ad.size()).isEqualTo(1);
        assertThat(ad.isEmpty()).isFalse();

        ad.removeLast();
        assertThat(ad.size()).isEqualTo(0);
        assertThat(ad.isEmpty()).isTrue();
        ad.addLast('z');
        ad.addFirst('d');
        assertThat(ad.removeLast()).isEqualTo('z');
        assertThat(ad.removeFirst()).isEqualTo('d');
    }

    @Test
    void testAdd() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        ad.addLast(4);
        ad.addLast(5);
        ad.addLast(6);
        ad.addFirst(7);
        ad.addFirst(8);
        ad.addFirst(9);
        assertThat(ad.get(0)).isEqualTo(9);
        assertThat(ad.get(8)).isEqualTo(6);
    }

    @Test
    @DisplayName("get should retrieve the correct item")
    void testGet() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(0);
        ad.addLast(1);
        ad.addLast(2);

        assertThat(ad.get(0)).isEqualTo(0);
        assertThat(ad.get(1)).isEqualTo(1);
        assertThat(ad.get(2)).isEqualTo(2);

        // Test getting from an empty list
        ArrayDeque61B<String> ad2 = new ArrayDeque61B<>();
        assertThat(ad2.get(0)).isNull();

        // Test out of bounds
        assertThat(ad.get(-1)).isNull();
        assertThat(ad.get(3)).isNull();
    }

    @Test
    @DisplayName("getRecursive should retrieve the correct item")
    void testGetRecursive() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(0);
        ad.addLast(1);
        ad.addLast(2);
        assertThat(ad.getRecursive(1)).isEqualTo(1);

        // Reset deque for next test since getRecursive is destructive
        ad = new ArrayDeque61B<>();
        ad.addLast(5);
        ad.addLast(10);
        ad.addLast(15);
        assertThat(ad.getRecursive(0)).isEqualTo(5);

        // Reset deque
        ad = new ArrayDeque61B<>();
        ad.addLast(5);
        ad.addLast(10);
        ad.addLast(15);
        assertThat(ad.getRecursive(2)).isEqualTo(15);

        // Test getting from an empty list
        ArrayDeque61B<String> ad2 = new ArrayDeque61B<>();
        assertThat(ad2.getRecursive(0)).isNull();

        // Test out of bounds
        ad = new ArrayDeque61B<>();
        ad.addLast(5);
        ad.addLast(10);
        assertThat(ad.getRecursive(-1)).isNull();
        assertThat(ad.getRecursive(2)).isNull();
    }

    @Test
    @DisplayName("Resizing down should occur when usage is low")
    void testResizeDown() throws NoSuchFieldException, IllegalAccessException {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        // Add enough items to resize up multiple times
        for (int i = 0; i < 65; i++) {
            ad.addLast(i);
        }

        // Check backing array size after additions
        Field itemsField = ArrayDeque61B.class.getDeclaredField("items");
        itemsField.setAccessible(true);
        Object[] items = (Object[]) itemsField.get(ad);
        assertThat(items.length).isEqualTo(128);

        // Remove items until the resize down condition is almost met
        // Condition: size > 16 && size < items.length * 0.25
        // items.length = 128, 128 * 0.25 = 32.
        // We need to remove until 16 < size < 32. Let's get size to 31.
        for (int i = 0; i < (65 - 31); i++) {
            ad.removeLast();
        }
        assertThat(ad.size()).isEqualTo(31);

        // Remove one more item to trigger resizeDown
        ad.removeLast();
        assertThat(ad.size()).isEqualTo(30);

        // Check backing array size after resizeDown

        items = (Object[]) itemsField.get(ad);
        // New size should be 2 * 31 = 62
        // The logic in the code is resizeDown(2 * size)
        assertThat(items.length).isEqualTo(62);

        // Verify the content is still correct
        assertThat(ad.get(0)).isEqualTo(0);
        assertThat(ad.get(29)).isEqualTo(29);
    }

    @Test
    @DisplayName("Resizing up should occur when the array is full")
    void testResizeUp() throws NoSuchFieldException, IllegalAccessException {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        // Initial capacity is 8
        for (int i = 0; i < 8; i++) {
            ad.addLast(i);
        }

        // Check backing array size before resize
        Field itemsField = ArrayDeque61B.class.getDeclaredField("items");
        itemsField.setAccessible(true);
        Object[] items = (Object[]) itemsField.get(ad);
        assertThat(items.length).isEqualTo(8);

        // Add one more item to trigger resizeUp
        ad.addLast(8);
        assertThat(ad.size()).isEqualTo(9);

        // Check backing array size after resizeUp
        items = (Object[]) itemsField.get(ad);
        assertThat(items.length).isEqualTo(16); // 8 * 2

        // Verify the content is still correct
        for (int i = 0; i < 9; i++) {
            assertThat(ad.get(i)).isEqualTo(i);
        }
    }

    @Test
    @DisplayName("toList should return a correct list representation of the deque")
    void testToList() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        // Test with empty deque
        assertThat(ad.toList()).isEmpty();

        // Add some items
        ad.addLast(10);
        ad.addFirst(5);
        ad.addLast(20); // Deque should be [5, 10, 20]

        List<Integer> expected = List.of(5, 10, 20);
        assertThat(ad.toList()).containsExactlyElementsIn(expected).inOrder();

        // Test after wrap around
        ArrayDeque61B<Integer> adWrap = new ArrayDeque61B<>(); // capacity 8
        for (int i = 0; i < 6; i++) {
            adWrap.addLast(i);
        }
        adWrap.removeFirst();
        adWrap.removeFirst();
        adWrap.addLast(6);
        adWrap.addLast(7);
        adWrap.addLast(8); // Deque: [2, 3, 4, 5, 6, 7, 8]

        List<Integer> expectedWrap = List.of(2, 3, 4, 5, 6, 7, 8);
        assertThat(adWrap.toList()).containsExactlyElementsIn(expectedWrap).inOrder();
    }

    @Test
    @DisplayName("Removing from an empty deque should return null and not change size")
    void testRemoveFromEmpty() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        assertThat(ad.removeFirst()).isNull();
        assertThat(ad.size()).isEqualTo(0);
        assertThat(ad.removeLast()).isNull();
        assertThat(ad.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("A complex sequence of operations should not break the deque")
    void testComplexOperations() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        // Add and remove a bunch of items
        for (int i = 0; i < 100; i++) {
            ad.addFirst(i);
        }
        for (int i = 0; i < 50; i++) {
            assertThat(ad.removeLast()).isEqualTo(i);
        }
        assertThat(ad.size()).isEqualTo(50);
        for (int i = 99; i > 49; i--) {
            assertThat(ad.get(99 - i)).isEqualTo(i);
        }
        for (int i = 0; i < 50; i++) {
            assertThat(ad.removeFirst()).isEqualTo(99 - i);
        }
        assertThat(ad.isEmpty()).isTrue();
    }

    @Test
    void testEquals() {
        ArrayDeque61B<Integer> ad1 = new ArrayDeque61B<>();
        ArrayDeque61B<Integer> ad2 = new ArrayDeque61B<>();
        assertThat(ad1).isEqualTo(ad2);
        ad1.addLast(1);
        ad1.addLast(2);
        assertThat(ad1).isNotEqualTo(ad2);
        ad2.addLast(1);
        ad2.addLast(2);
        assertThat(ad2).isEqualTo(ad1);
    }
    @Test
    void testToString() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        ad.addLast(4);
        ad.addFirst(5);
        LinkedListDeque61B<Integer> ld = new LinkedListDeque61B<>();
        System.out.println(ad);
        ld.addLast(6);
        System.out.println(ld);
    }

    @Test
    @DisplayName("get should work correctly after the array pointers wrap around")
    void testGetWithWrapAround() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>(); // capacity 8
        // Fill up the deque to cause wrap around
        // Initial: nextFirst=3, nextLast=4
        ad.addFirst(0); // nf=2, items[3]=0
        ad.addFirst(1); // nf=1, items[2]=1
        ad.addFirst(2); // nf=0, items[1]=2
        ad.addFirst(3); // nf=7, items[0]=3
        ad.addLast(4);  // nl=5, items[4]=4
        ad.addLast(5);  // nl=6, items[5]=5
        ad.addLast(6);  // nl=7, items[6]=6
        ad.addLast(7);  // nl=0, items[7]=7

        // Deque state: [3, 2, 1, 0, 4, 5, 6, 7]
        // nextFirst is 7, nextLast is 0
        // The logical order is from index nextFirst+1
        assertThat(ad.get(0)).isEqualTo(3);
        assertThat(ad.get(1)).isEqualTo(2);
        assertThat(ad.get(2)).isEqualTo(1);
        assertThat(ad.get(3)).isEqualTo(0);
        assertThat(ad.get(4)).isEqualTo(4);
        assertThat(ad.get(5)).isEqualTo(5);
        assertThat(ad.get(6)).isEqualTo(6);
        assertThat(ad.get(7)).isEqualTo(7);

        // Remove some and check again
        // This will likely throw an exception if removeFirst is buggy
        assertThat(ad.removeFirst()).isEqualTo(3);
        // This will likely throw an exception if removeLast is buggy
        assertThat(ad.removeLast()).isEqualTo(7);

        // Deque state: [2, 1, 0, 4, 5, 6]
        assertThat(ad.get(0)).isEqualTo(2);
        assertThat(ad.get(5)).isEqualTo(6);
        assertThat(ad.size()).isEqualTo(6);
    }


}




