package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/** https://leetcode.com/problems/the-maze/ */

public class TheMaze {
    static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static class BFS {
        public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
            int m = maze.length;
            int n = maze[0].length;
            // we decided no need to maintain the direction of each node/state
            Queue<int[]> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[m][n];
            q.offer(start);
            // visited[][] only store the visited stop cells
            visited[start[0]][start[1]] = true;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                if (cur[0] == destination[0] && cur[1] == destination[1]) {
                    return true;
                }
                for (int[] dir : DIRS) {
                    int nr = cur[0] + dir[0];
                    int nc = cur[1] + dir[1];
                    // as long as the ball does not meet a wall, keep moving towards this direction
                    while (nr >= 0 && nr < m && nc >= 0 && nc < n && maze[nr][nc] != 1) {
                        nr += dir[0];
                        nc += dir[1];
                    }
                    // when hit wall, return a step to stand at the stop cell
                    nr -= dir[0];
                    nc -= dir[1];
                    if (!visited[nr][nc]) {
                        q.offer(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            return false;
        }
    }

    static class DFS {
        public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
            boolean[][] visited = new boolean[maze.length][maze[0].length];
            return dfs(maze, start, destination, visited);
        }

        private static boolean dfs(int[][] maze, int[] curStop, int[] destination, boolean[][] visited) {
            if (visited[curStop[0]][curStop[1]]) {
                return false;
            }
            if (curStop[0] == destination[0] && curStop[1] == destination[1]) {
                return true;
            }
            visited[curStop[0]][curStop[1]] = true; // only when we stop here we need to mark as visited
            for (int[] dir : DIRS) {
                int x = curStop[0] + dir[0];
                int y = curStop[1] + dir[1];
                while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0]; // while we traverse, we should not mark as visited until we stop
                    y += dir[1];
                }
                x -= dir[0];
                y -= dir[1];
                if (dfs(maze, new int[] {x, y}, destination, visited)) {
                    return true;
                }
            }
            return false;
        }
    }

}
