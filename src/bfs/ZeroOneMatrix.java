package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/** https://leetcode.com/problems/01-matrix/ */

public class ZeroOneMatrix {
    /*
    solution 1: for each element in mat (m * n), perform BFS to update the distance (m * n) -> (m * n)^2

    solution 2: we reversely solve the problem by starting from all 0 elements considering then as the initial layer,
        updating the distances of all the 1 in the path,
        so we perform BFS only once (m * n * 2)
     */

    public int[][] updateMatrix(int[][] mat) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = mat.length;
        int n = mat[0].length;
        int[][] result = new int[m][n];
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    visited[i][j] = true;

                    // faster node representation for queue storage when the graph is 2D matrix
                    q.offer(new int[] {i, j});
                }
            }
        }

        // cost varies for each layer/level
        int cost = 0;
        while (!q.isEmpty()) {
            // record the size of each layer/level
            int curSize = q.size();
            for (int i = 0; i < curSize; i++) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                result[x][y] = cost; // when mat[x][y] == 0, its cost is same as default value -> 0

                for (int[] dir : dirs) {
                    int neiX = x + dir[0];
                    int neiY = y + dir[1];
                    if (neiX >= 0 && neiX < m && neiY >= 0 && neiY < n
                            && !visited[neiX][neiY]) {
                        visited[neiX][neiY] = true;
                        q.offer(new int[] {neiX, neiY});
                    }
                }
            }
            // remember to increment the cost before going to the next layer/level
            cost++;
        }
        return result;
    }
    // time: O(m * n)
    // space: O(m * n)
}
