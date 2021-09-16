package dp;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example
 *
 * ")()())", where the longest valid parentheses' substring is "()()", which has length = 4.
 */

public class LongestValidParentheses {
    public int longestValidParentheses(String input) {
        // assume input is not null

        // dp[i] represents the length of the longest valid substring ending at ith index
        // dp[i] = dp[i - 2] + 2 if s[i] = ')' and s[i - 1] = '('
        // dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2 if s[i] = ')' and s[i - 1] = ')' and s[i - dp[i - 1] - 1] = '('
        int[] dp = new int[input.length()];
        int longest = 0;
        for (int i = 1; i < dp.length; i++) {
            // only when s[i] = ')', dp[i] is possible to be non-0
            if (input.charAt(i) == ')') {
                if (input.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && input.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
                }
                longest = Math.max(longest, dp[i]);
            }
        }
        return longest;
    }
    // time & space: O(n)
}
