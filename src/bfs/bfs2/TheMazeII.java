package bfs.bfs2;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/the-maze-ii/
 */

public class TheMazeII {
    static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        return dijkstra(maze, start, destination);
    }

    private int dijkstra(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        // distance[i][j] represents the minimum distance from start position to (i, j)
        // 即起始点到每一个其他点的距离
        int[][] distance = new int[m][n];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;

        // we store int[] {x, y, dist} in the q
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        q.offer(new int[]{start[0], start[1], 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            // because of BFS2, we can early return
            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return cur[2];
            }
            distance[cur[0]][cur[1]] = cur[2];
            for (int[] dir : DIRS) {
                int nr = cur[0] + dir[0];
                int nc = cur[1] + dir[1];
                int count = 1;
                // as long as the ball does not meet a wall, keep moving towards this direction
                while (nr >= 0 && nr < m && nc >= 0 && nc < n && maze[nr][nc] == 0) {
                    nr += dir[0];
                    nc += dir[1];
                    count++;
                }
                // when hit wall, return a step to stand at the stop cell
                nr -= dir[0];
                nc -= dir[1];
                count--;

                // only poll neighbor node if it has not been expanded!
                if (distance[nr][nc] == Integer.MAX_VALUE) {
                    q.offer(new int[]{nr, nc, distance[cur[0]][cur[1]] + count});
                }
            }
        }
        return -1;
    }
}
