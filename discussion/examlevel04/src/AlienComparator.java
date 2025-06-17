import java.util.Comparator;

public class AlienComparator implements Comparator<String> {
    private String order;

    public AlienComparator(String alphabetOder) {
        order = alphabetOder;
    }

    public int compare(String word1, String word2) {
        int minLength = Math.min(word1.length(), word2.length());
        for (int i = 0; i < minLength; i++) {
            char char1 = word1.charAt(i);
            char char2 = word2.charAt(i);
            int char1Rank = order.indexOf(char1);
            int char2Rank = order.indexOf(char2);
            if (char1Rank > char2Rank) {
                return 1;
            }
            else if (char1Rank < char2Rank) {
                return -1;
            }
        }
        return word1.length() - word2.length();
    }

}
