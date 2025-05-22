import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;
public class testAList {
    @Test
    public void test() {
        AList<String> x = new AList<>();
        x.addLast("i");
        x.addLast("am");
        String[] expected = {"i", "am"};
        assertThat(x.get(1)).isEqualTo(expected[1]);
    }
}
