package bfs.bfs2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/the-maze-ii/">...</a>
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

        // bfs2 特点1： 一个 distance[] or cost[]

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
            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return cur[2];
            }

            // bfs2 特点2：poll 出后检查是否已经被 expanded 过

//          we do not re-expand the same node
            //  > 的情况是存在的 -> 当node第二次进去 queue 之前，第一次的 node 还没有被 expand，所以导致node进了多次
            // 所以当 node 被 expand 出来的时候，要检查一下是否已经被 expand 过，只有还没被 expand 过，才继续
            if (distance[cur[0]][cur[1]] < cur[2]) {
                continue;
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

                // bfs2 特点3： 只有在还没有被 expand 过的时候才 generate 进 queue

                // technically, we do not need to check the if-statement for bfs2, but doing this will reduce redundant processing.
                if (distance[nr][nc] > distance[cur[0]][cur[1]] + count) {
                    q.offer(new int[]{nr, nc, distance[cur[0]][cur[1]] + count});
                }
            }
        }
        return -1;
    }

    private int dijkstra2(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;

        // Maintaining a distance[] is not necessary. The key is to record the nodes that have been expanded, so that
        // based on the rule of dijkstra, we don't need to re-generate / expand the same nodes.
        // Here for demonstration, we use a Set.
        Set<String> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.offer(new int[]{start[0], start[1], 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return cur[2];
            }
            // do not process the nodes having been expanded
            // trick, set.add() -> we only add the nodes into the set when expanding it now
            if (!visited.add(cur[0] + "," + cur[1])) {
                continue;
            }
            for (int[] dir : DIRS) {
                int nr = cur[0] + dir[0];
                int nc = cur[1] + dir[1];
                int count = 1;
                while (nr >= 0 && nr < m && nc >= 0 && nc < n && maze[nr][nc] == 0) {
                    nr += dir[0];
                    nc += dir[1];
                    count++;
                }
                nr -= dir[0];
                nc -= dir[1];
                count--;
                // technically, we do not need to check the if-statement for bfs2, but doing this will reduce redundant processing.
                if (!visited.contains(nr + "," + nc)) {
                    pq.offer(new int[]{nr, nc, cur[2] + count});
                }
            }
        }
        return -1;
    }

}
