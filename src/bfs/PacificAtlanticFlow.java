package bfs;

import java.util.*;

/**
 * <a href="https://app.laicode.io/app/problem/665?plan=32">...</a>
 */

public class PacificAtlanticFlow {
    static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] cache = new int[m][n];
        Queue<int[]> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        collectEdgeCellsToPacific(m, n, q, cache, visited);

        bfs(matrix, cache, m, n, q, visited, dirs);

        visited.clear();

        collectEdgeCellsToAtlantic(m, n, q, cache, visited);

        bfs(matrix, cache, m, n, q, visited, dirs);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cache[i][j] == 2) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void collectEdgeCellsToAtlantic(int m, int n, Queue<int[]> q, int[][] cache, Set<String> visited) {
        for (int i = 0; i < n; i++) {
            cache[m - 1][i]++;
            q.offer(new int[]{m - 1, i});
            visited.add(m - 1 + "," + i);
        }
        for (int i = 0; i < m - 1; i++) { // to avoid calculating <m - 1, n - 1> twice
            cache[i][n - 1]++;
            q.offer(new int[]{i, n - 1});
            visited.add(i + "," + (n - 1));
        }
    }

    private void collectEdgeCellsToPacific(int m, int n, Queue<int[]> q, int[][] cache, Set<String> visited) {
        for (int i = 0; i < n; i++) {
            cache[0][i]++;
            q.offer(new int[]{0, i});
            visited.add(0 + "," + i);
        }
        for (int i = 1; i < m; i++) { // to avoid calculating <0, 0> twice
            cache[i][0]++;
            q.offer(new int[]{i, 0});
            visited.add(i + "," + 0);
        }
    }

    private void bfs(int[][] matrix, int[][] cache, int m, int n, Queue<int[]> q, Set<String> visited, int[][] dirs) {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] dir : dirs) {
                int nr = cur[0] + dir[0];
                int nc = cur[1] + dir[1];

                if (nr >= 0 && nc >= 0 && nr < m && nc < n && !visited.contains(nr + "," + nc) && matrix[cur[0]][cur[1]] <= matrix[nr][nc]) {
                    cache[nr][nc]++;
                    q.offer(new int[]{nr, nc});
                    visited.add(nr + "," + nc);
                }
            }
        }
    }
}
