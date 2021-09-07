package dfs;

/** https://leetcode.com/problems/number-of-islands/ */

public class NumberOfIslands {
    private final static int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int numIslands(char[][] grid) {
        int num = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    num++;
                    dfs(grid, i, j, visited);
                }
            }
        }
        return num;
    }

    private void dfs(char[][] grid, int row, int col, boolean[][] visited) {
        // base case
        if (row < 0 || row >= grid.length ||
                col < 0 || col >= grid[0].length ||
                visited[row][col] || grid[row][col] == '0') {
            return;
        }
        visited[row][col] = true;
        for (int[] dir : DIRS) {
            int neiR = row + dir[0];
            int neiC = col + dir[1];
            dfs(grid, neiR, neiC, visited);
        }
    }
}
