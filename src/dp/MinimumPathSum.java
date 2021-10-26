package dp;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 */

public class MinimumPathSum {
    static class DP {
        public int minPathSum(int[][] grid) {
            int[][] dp = new int[grid.length][grid[0].length];
            // dp[i][j] represents from grid[0][0] to grid[i][j] the min sum
            dp[0][0] = grid[0][0];
            for (int i = 1; i < grid.length; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
            for (int j = 1; j < grid[0].length; j++) {
                dp[0][j] = dp[0][j - 1] + grid[0][j];
            }

            for (int i = 1; i < grid.length; i++) {
                for (int j = 1; j < grid[0].length; j++) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            return dp[grid.length - 1][grid[0].length - 1];
        }
    }

    static class BFS {
        static final int[][] DIRS = {{1, 0}, {0, 1}};

        public static int minPathSum(int[][] grid) {
            Queue<int[]> q = new ArrayDeque<>();
            int R = grid.length;
            int C = grid[0].length;
            q.offer(new int[]{0, 0, grid[0][0]});
            int minSum = Integer.MAX_VALUE;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                if (cur[0] == R - 1 && cur[1] == C - 1) {
                    minSum = Math.min(minSum, cur[2]);
                    continue;
                }
                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x < R && y < C) {
                        q.offer(new int[]{x, y, cur[2] + grid[x][y]});
                    }
                }
            }
            return minSum;
        }
    }

}
