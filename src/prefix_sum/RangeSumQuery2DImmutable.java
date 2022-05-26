package prefix_sum;

/** <a href="https://leetcode.com/problems/range-sum-query-2d-immutable/">...</a> */

public class RangeSumQuery2DImmutable {
    private int[][] prefixSum;
    public RangeSumQuery2DImmutable(int[][] matrix) { // O(m*n) time and space
        int rows = matrix.length;
        int cols = matrix[0].length;
        // trick: use prefixSum[i+1][j+1] to represent the sum between [0,0] to [i, j]
        // doing so could avoid handling corner cases
        prefixSum = new int[rows + 1][cols + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // prefixSum of (i, j) =
                // prefixSum of (i, j - 1) + prefixSum of (i - 1, j) + matrix of (i, j) - prefixSum of (i - 1, j - 1)
                prefixSum[i + 1][j + 1] =
                        prefixSum[i + 1][j] + prefixSum[i][j + 1] + matrix[i][j] - prefixSum[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) { // O(1)
        // need to draw examples to better understand how to correctly cut the result sum from the points
        // sum between [r1, c1] & [r2, c2] =
        // prefixSum of (r2, c2) - prefixSum of (r1 - 1, c2) - prefixSum of (r2, c1 - 1) + prefixSum of (r1 - 1, c1 - 1)
        return prefixSum[row2 + 1][col2 + 1] - prefixSum[row1][col2 + 1] - prefixSum[row2 + 1][col1]
                + prefixSum[row1][col1];
    }
}
