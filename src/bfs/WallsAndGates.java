package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/walls-and-gates/">...</a>
 */

public class WallsAndGates {
    private int[][] grid;
    private int m;
    private int n;
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public void wallsAndGatesBFSAllTogether(int[][] rooms) {
        grid = rooms;
        m = grid.length;
        n = grid[0].length;
        Queue<String> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    q.offer(i + "," + j);
                }
            }
        }
        int dis = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String[] cur = q.poll().split(",");
                for (int[] dir : DIRS) {
                    int nr = Integer.parseInt(cur[0]) + dir[0];
                    int nc = Integer.parseInt(cur[1]) + dir[1];
                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] > dis) {
                        grid[nr][nc] = dis;
                        q.offer(nr + "," + nc);
                    }
                }
            }
            dis++;
        }

    }

    public void wallsAndGatesOneByOne(int[][] rooms) {
        grid = rooms;
        m = grid.length;
        n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }
    }

    private void bfs(int r, int c) {
        Queue<String> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        q.offer(r + "," + c);
        visited[r][c] = true;

        int dis = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String[] cur = q.poll().split(",");
                for (int[] dir : DIRS) {
                    int nr = Integer.parseInt(cur[0]) + dir[0];
                    int nc = Integer.parseInt(cur[1]) + dir[1];
                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && !visited[nr][nc] && grid[nr][nc] > dis) {
                        grid[nr][nc] = dis;
                        visited[nr][nc] = true;
                        q.offer(nr + "," + nc);
                    }
                }
            }
            dis++;
        }
    }

}
