package dp;

/** https://leetcode.com/problems/distinct-subsequences/ */

public class DistinctSubsequences {

    // good example from recursive + memo -> dp

    public int numDistinct(String s, String t) {
        int M = s.length();
        int N = t.length();

        int[][] dp = new int[M + 1][N + 1];

        // Base case initialization
        for (int j = 0; j <= N; j++) {
            dp[M][j] = 0;
        }

        // Base case initialization
        for (int i = 0; i <= M; i++) {
            dp[i][N] = 1; // !
        }

        // Iterate over the strings in reverse to
        // satisfy the way we've modeled our recursive solution
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {

                // Remember, we always need this result
                dp[i][j] = dp[i + 1][j];

                // If the characters match, we add the
                // result of the next recursion call (in this
                // case, the value of a cell in the dp table
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i + 1][j + 1];
                }
            }
        }

        return dp[0][0];
    }
}
