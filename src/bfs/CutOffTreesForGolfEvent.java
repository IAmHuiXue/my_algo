package bfs;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/cut-off-trees-for-golf-event/">...</a>
 */
public class CutOffTreesForGolfEvent {
    static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) {
            return 0;
        }
        int m = forest.size();
        int n = forest.get(0).size();

        // use a pq to help to sort the cutting order of all the trees based on their height
        // int[]{a=row, b=col, c=height}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        // traverse the entire matrix to get all the tree pos sorted
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int height = forest.get(i).get(j);
                if (height > 1) {
                    pq.offer(new int[]{i, j, height});
                }
            }
        }

        int[] curStart = new int[]{0, 0};
        int steps = 0;
        while (!pq.isEmpty()) {
            int[] curEnd = pq.poll();
            int curSteps = minStep(forest, curStart, curEnd, m, n, new boolean[m][n]);
            if (curSteps < 0) {
                return -1;
            }
            steps += curSteps;
            curStart[0] = curEnd[0];
            curStart[1] = curEnd[1];
        }
        return steps;
    }

    /** to get the min steps travelling from start to end, -1 if cannot reach */
    private int minStep(List<List<Integer>> forest, int[] start, int[] end, int m, int n, boolean[][] visited) {
        int steps = 0;
        // int[]{a=row, b=col}
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == end[0] && curr[1] == end[1]) {
                    return steps;
                }
                for (int[] dir : DIRS) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n
                            || forest.get(nr).get(nc) == 0 || visited[nr][nc]) {
                        continue;
                    }
                    queue.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
            steps++;
        }
        return -1;
    }
}
