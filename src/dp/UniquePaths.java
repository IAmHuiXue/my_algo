package dp;

import java.util.Arrays;

/** https://leetcode.com/problems/unique-paths/ */

public class UniquePaths {
/*
    To such cell one could move either from the upper cell (m, n - 1), or from the cell on the right (m - 1, n).
    That means that the total number of paths to move into (m, n) cell is uniquePaths(m - 1, n) + uniquePaths(m, n - 1).
    */
    // public int uniquePaths(int m, int n) {
    //     if (m == 1 || n == 1) {
    //         return 1;
    //     }
    //     return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    // }

    public int uniquePaths(int m, int n) {
        int[][] d = new int[m][n];

        for(int[] arr : d) {
            Arrays.fill(arr, 1);
        }
        for(int col = 1; col < m; ++col) {
            for(int row = 1; row < n; ++row) {
                d[col][row] = d[col - 1][row] + d[col][row - 1];
            }
        }
        return d[m - 1][n - 1];
    }
    // O(m*n)
}
