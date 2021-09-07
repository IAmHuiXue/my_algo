package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/** https://leetcode.com/problems/number-of-islands/ */

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    visited[i][j] = true;
                    Queue<Cell> neighbors = new ArrayDeque<>();
                    neighbors.offer(new Cell(i, j));
                    while (!neighbors.isEmpty()) {
                        Cell cur = neighbors.poll();
                        for (int[] dir : dirs) {
                            int neiX = cur.row + dir[0];
                            int neiY = cur.col + dir[1];
                            if (neiX >= 0 && neiX < grid.length &&
                                    neiY >= 0 && neiY < grid[0].length &&
                                    !visited[neiX][neiY] &&
                                    grid[neiX][neiY] == '1') {
                                visited[neiX][neiY] = true;
                                neighbors.offer(new Cell(neiX, neiY));
                            }
                        }

                    }
                }
            }
        }
        return count;
    }

    static class Cell {
        int row;
        int col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
