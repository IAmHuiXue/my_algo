package string;

/**
 * <a href="https://leetcode.com/problems/maximum-repeating-substring/">https://leetcode.com/problems/maximum-repeating-substring/</a>
 */

public class MaximumRepeatingSubstring {
    public int maxRepeating(String sequence, String word) {
        int n = sequence.length(), m = word.length();
        int max = 0;
        for (int i = 0; i <= n - m; i++) {
            int cnt = 0, k = 0;
            for (int j = i; j < n; j++) {
                if (sequence.charAt(j) == word.charAt(k)) {
                    k++;
                } else {
                    break;
                }
                if (k == m) {
                    k = 0; // remember to clear
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}
