package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/** <a href="https://leetcode.com/problems/rotting-oranges/">...</a> */

public class RottingOranges {
    int[][] grid;
    int m;
    int n;
    static final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int orangesRotting(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        // first round will scan the entire grid to
        // 1. count the num of fresh ones
        // 2. store the rotten ones all together into the queue for bfs processing later
        int numFresh = 0;
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    // 进 queue 的都是 rotten ones
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    numFresh++;
                }
            }
        }

        if (numFresh == 0) { // one corner case
            return 0;
        }

        int min = -1; // 走例子来理解好为什么起始是 -1

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : DIRS) {
                    int nr = dir[0] + cur[0];
                    int nc = dir[1] + cur[1];
                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] == 1) {
                        // 通过将 fresh 变成 rotten 来解决 de-dup 的问题
                        grid[nr][nc] = 2;
                        q.offer(new int[]{nr, nc});
                        numFresh--;
                    }
                }
            }
            min++;
        }
        // 如果还有 fresh ones 没有 reach 到，则结果为 -1
        return numFresh == 0 ? min : -1;
    }
    // time: O(m*n + m*n)
    // space: O(m*n) -> 取决于 queue 的 size  -> worst case 是所有 cell 都是烂橘子
}
