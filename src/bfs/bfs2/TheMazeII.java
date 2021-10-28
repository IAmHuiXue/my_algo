package bfs.bfs2;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/the-maze-ii/
 */

public class TheMazeII {
    static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // distance[i][j] represents the minimum distance from start position to (i, j)
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dijkstra(maze, start, distance, destination);
        // we need to perform an entire dijkstra to get the final result.
        // during the operation, distance[destination[0]][destination[1]] may be updated to shorter answer
        // at later steps
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ?
                -1 : distance[destination[0]][destination[1]];
    }

    private void dijkstra(int[][] maze, int[] start, int[][] distance, int[] destination) {
        distance[start[0]][start[1]] = 0;
        int m = maze.length;
        int n = maze[0].length;
        // we store int[] {x, y, dist} in the q
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        q.offer(new int[]{start[0], start[1], 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            // because of BFS2, we can early return
            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return;
            }
            // because of BFS, we do not re-expand the same node
            if (distance[cur[0]][cur[1]] < cur[2]) {
                continue;
            }
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

                // this is the key point for dijkstra
                // we do not need to have a hashset for de-dup, because we allow to re-generate nodes into q,
                // but we need to make sure current distance is the smallest before offering it
                if (distance[cur[0]][cur[1]] + count < distance[nr][nc]) {
                    distance[nr][nc] = distance[cur[0]][cur[1]] + count;
                    q.offer(new int[]{nr, nc, distance[nr][nc]});
                }
            }
        }
    }
}
