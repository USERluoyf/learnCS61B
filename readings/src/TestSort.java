import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;
public class TestSort {
    @Test
    public void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);
        assertThat(input).isEqualTo(expected);
    }
    @Test
    public void testSmallest() {
        String[] input = {"i", "have", "an", "egg"};
        String expected = "an";

        int actual = Sort.findSmallest(input, 0);
        assertThat(input[actual]).isEqualTo(expected);

        String[] input2 = {"there", "are", "many", "pigs"};
        String expected2 = "are";

        int actual2 = Sort.findSmallest(input2, 0);
        assertThat(input2[actual2]).isEqualTo(expected2);
    }

    @Test
    public void testSwap() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};
        Sort.swap(input, a, b);
        assertThat(input).isEqualTo(expected);
    }

}

