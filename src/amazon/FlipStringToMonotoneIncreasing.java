package amazon;

/**
 * <a href="https://leetcode.com/problems/flip-string-to-monotone-increasing/">...</a>
 */

public class FlipStringToMonotoneIncreasing {
    public int minFlipsMonoIncr(String s) {
        // Basically we go through string and found out how many '1' before index i must be flipped to '0' and how many '0'
        // after index i need to be flipped to '1'. adds them up and get min for result
        int n = s.length();
        int[] dp = new int[n + 1];
        // dp[i] represents from index 0 to index i inclusive, the num of 1's that needs to be flipped

        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i] + (s.charAt(i) == '1' ? 1 : 0);
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j <= n; j++) {
            // for index j, dp[j - 1] 1s need to be flipped
            // and from j to the end inclusive, dp[n] - dp[j] 1s need to be flipped
            // so n - j - (dp[n] - dp[j]) zeros need to be flipped
            res = Math.min(res, dp[j] + (n - j - (dp[n] - dp[j])));
        }
        return res;
    }
    // time: O(n)
}
