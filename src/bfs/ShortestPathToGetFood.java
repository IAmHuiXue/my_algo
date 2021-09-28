package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/shortest-path-to-get-food/
 */

public class ShortestPathToGetFood {
    static final int[][] DIRS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    static class Cell {
        int x;
        int y;
        int dis;

        Cell(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

    public int getFood(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<Cell> queue = new ArrayDeque<>();
        // in this solution, we define visited[i][j] = true when the cell is a free space, and we have just visited it.
        boolean[][] visited = new boolean[m][n];
        // used label to break the entire nested loop
        OuterLoop:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '*') {
                    queue.offer(new Cell(i, j, 0));
                    break OuterLoop;
                }
            }
        }
        while (!queue.isEmpty()) {
            Cell cur = queue.poll();
            if (grid[cur.x][cur.y] == '#') {
                return cur.dis;
            }
            for (int[] dir : DIRS) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];
                // not out of bound, not barrier, and not visited
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] != 'X' && !visited[nextX][nextY]) {
                    queue.offer(new Cell(nextX, nextY, cur.dis + 1));
                    visited[nextX][nextY] = true;
                }
            }
        }
        return -1;
    }

    // another variant solution -> replace class Cell with int[] {x, y},
    // and declare a dist variable and record size of the queue at each layer
    public int getFood2(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        // in this solution, we define visited[i][j] = true when the cell is a free space, and we have just visited it.
        boolean[][] visited = new boolean[m][n];
        // used label to break the entire nested loop
        OuterLoop:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '*') {
                    queue.offer(new int[]{i, j});
                    break OuterLoop;
                }
            }
        }
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                int[] cur = queue.poll();
                if (grid[cur[0]][cur[1]] == '#') {
                    return dist;
                }
                for (int[] dir : DIRS) {
                    int nextX = cur[0] + dir[0];
                    int nextY = cur[1] + dir[1];
                    // not out of bound, not barrier, and not visited
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] != 'X' && !visited[nextX][nextY]) {
                        queue.offer(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                }
            }
            dist++;
        }
        return -1;
    }

}
