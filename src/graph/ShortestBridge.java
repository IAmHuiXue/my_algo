package graph;

import org.junit.Assert;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/shortest-bridge/">https://leetcode.com/problems/shortest-bridge/</a>
 */

public class ShortestBridge {
    static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestBridge(int[][] grid) {
        Queue<int[]> q = new ArrayDeque<>();

        outer:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j, q, grid);
                    break outer;
                }
            }
        }

        return bfs(q, grid);
    }

    void dfs(int r, int c, Queue<int[]> q, int[][] grid) {
        grid[r][c] = -1; // mark it -1 or introduce a Set/int[][] visited
        q.offer(new int[]{r, c});
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc] == 1) {
                dfs(nr, nc, q, grid);
            }
        }
    }

    int bfs(Queue<int[]> q, int[][] grid) {
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int nr = cur[0] + dir[0];
                    int nc = cur[1] + dir[1];
                    if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length) {
                        if (grid[nr][nc] == 1) {
                            return step;
                        }
                        if (grid[nr][nc] == 0) {
                            q.offer(new int[]{nr, nc});
                            grid[nr][nc] = -1;
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
