import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage; // Using Google Truth, as in your example
import java.util.List; // Required for toList() return type and potentially for containsExactly
// You'll need to import your DMSList class, e.g.:
// import com.yourpackage.DMSList;

// Assuming DMSList.java (and its inner/related classes like IntNode, LastIntNode)
// are accessible.

public class testDMSList {

    /**
     * Tests the basic functionality of insertFront by adding several elements
     * and then verifying the entire list's content and order in a single assertion.
     * This test assumes DMSList has a toList() method.
     */
   // @Test
//    public void insertFrontTestBasic() {
//        DMSList dmsList1 = new DMSList();
//
//        // insertFront adds elements to the beginning of the list.
//        dmsList1.insertFront(30); // Expected list after this: [30]
//        dmsList1.insertFront(20); // Expected list after this: [20, 30]
//        dmsList1.insertFront(10); // Expected list after this: [10, 20, 30]
//
//        // Assuming dmsList1.toList() returns a List<Integer>
//        // The elements [10, 20, 30] should be in order from the front.
//        assertThat(dmsList1.toList()).containsExactly(10, 20, 30).inOrder();
//    }

    /**
     * Tests the max() method functionality for an empty list and after
     * several insertFront operations.
     */
    @Test
    public void maxTestBasic() {
        DMSList dmsList1 = new DMSList();

        // 1. Test max() on an empty list (should return 0 as per previous problem's spec)
        assertThat(dmsList1.max()).isEqualTo(0);

        // 2. Add one item and test max()
        dmsList1.insertFront(10); // List: [10]
        assertThat(dmsList1.max()).isEqualTo(10);

        // 3. Add a larger item to the front and test max()
        dmsList1.insertFront(30); // List: [30, 10]
        assertThat(dmsList1.max()).isEqualTo(30);

        // 4. Add a smaller item to the front and test max()
        dmsList1.insertFront(5);  // List: [5, 30, 10]
        assertThat(dmsList1.max()).isEqualTo(30);

        // 5. Add an item equal to current max
        dmsList1.insertFront(30); // List: [30, 5, 30, 10]
        assertThat(dmsList1.max()).isEqualTo(30);

        // Optional: Verify the final list state if toList() is available and you want to be thorough
        // assertThat(dmsList1.toList()).containsExactly(30, 5, 30, 10).inOrder();
    }
}