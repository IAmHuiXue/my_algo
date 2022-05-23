package string;

/** <a href="https://leetcode.com/problems/longest-palindromic-substring/">https://leetcode.com/problems/longest-palindromic-substring/</a> */

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        // index[0] represents the index of the beginning letter of s
        // index[1] represents the index of the ending letter of s
        int[] index = new int[] {0, 0};
        for (int i = 0; i < s.length(); i++) {
            // need to consider aba & abba
            helper(index, s, i, i);
            helper(index, s, i, i + 1);
        }
        return s.substring(index[0], index[1] + 1);
    }

    private void helper(int[] index, String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        int length = right - left - 1;

        int diff = index[1] - index[0];
        if (length > diff) {
            index[0] = left + 1;
            index[1] = right - 1;
        }
    }
    // O(n^2)
}
