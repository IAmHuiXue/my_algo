package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/number-of-islands/">...</a>
 */

public class NumberOfIslands {
    static final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    class BFS {
        public int numIslands(char[][] grid) {
            Queue<int[]> q = new ArrayDeque<>();
            int res = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        res++;
                        q.offer(new int[]{i, j});
                        bfs(q, grid);
                    }
                }
            }
            return res;
        }

        void bfs(Queue<int[]> q, char[][] grid) {
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int[] dir : DIRS) {
                    int nr = cur[0] + dir[0];
                    int nc = cur[1] + dir[1];
                    if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc] == '1') {
                        grid[nr][nc] = '0';
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }

    class DFS {
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
            visited[row][col] = true;
            for (int[] dir : DIRS) {
                int neiR = row + dir[0];
                int neiC = col + dir[1];
                if (neiR < 0 || neiR >= grid.length ||
                        neiC < 0 || neiC >= grid[0].length ||
                        visited[neiR][neiC] || grid[neiR][neiC] == '0') {
                    continue;
                }
                dfs(grid, neiR, neiC, visited);
            }
        }
    }

}
