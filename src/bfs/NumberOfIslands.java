package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/number-of-islands/
 */

public class NumberOfIslands {
    static final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    class BFS {
        public int numIslands(char[][] grid) {
            int count = 0;
            boolean[][] visited = new boolean[grid.length][grid[0].length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        count++;
                        visited[i][j] = true;
                        Queue<Cell> q = new ArrayDeque<>();
                        q.offer(new Cell(i, j));
                        while (!q.isEmpty()) {
                            Cell cur = q.poll();
                            for (int[] dir : DIRS) {
                                int neiX = cur.row + dir[0];
                                int neiY = cur.col + dir[1];
                                if (neiX >= 0 && neiX < grid.length &&
                                        neiY >= 0 && neiY < grid[0].length &&
                                        !visited[neiX][neiY] &&
                                        grid[neiX][neiY] == '1') {
                                    visited[neiX][neiY] = true;
                                    q.offer(new Cell(neiX, neiY));
                                }
                            }

                        }
                    }
                }
            }
            return count;
        }

        class Cell {
            int row;
            int col;

            Cell(int row, int col) {
                this.row = row;
                this.col = col;
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

}
