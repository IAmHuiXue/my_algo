package dfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/surrounded-regions/solution/
 */

public class SurroundedRegions {
    private static final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static class DFS {
        public static void solve(char[][] board) {
            // corner case
            if (board.length < 3 || board[0].length < 3) {
                return;
            }

            int m = board.length;
            int n = board[0].length;

            // start from boarders, if an 'O' is found, dfs and render all adjacent 'O's
            for (int i = 0; i < m; i++) {
                if (board[i][0] == 'O') {
                    dfs(board, i, 0, m, n);
                }
                if (board[i][n - 1] == 'O') {
                    dfs(board, i, n - 1, m, n);
                }
            }
            for (int j = 0; j < n; j++) {
                if (board[0][j] == 'O') {
                    dfs(board, 0, j, m, n);
                }
                if (board[m - 1][j] == 'O') {
                    dfs(board, m - 1, j, m, n);
                }
            }

            // then traverse the entire board
            // change the left 'O' to 'X'
            // change the rendered 'E' back to 'O'
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'E') {
                        board[i][j] = 'O';
                        continue;
                    }
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private static void dfs(char[][] board, int r, int c, int R, int C) {
            board[r][c] = 'E';
            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < R && nc >= 0 && nc < C && board[nr][nc] == 'O') {
                    dfs(board, nr, nc, R, C);
                }
            }
        }
    }

    static class BFS {
        public static void solve(char[][] board) {
            // corner case
            if (board.length < 3 || board[0].length < 3) {
                return;
            }

            int m = board.length;
            int n = board[0].length;

            for (int i = 0; i < m; i++) {
                if (board[i][0] == 'O') {
                    bfs(board, i, 0, m, n);
                }
                if (board[i][n - 1] == 'O') {
                    bfs(board, i, n - 1, m, n);
                }
            }
            for (int j = 0; j < n; j++) {
                if (board[0][j] == 'O') {
                    bfs(board, 0, j, m, n);
                }
                if (board[m - 1][j] == 'O') {
                    bfs(board, m - 1, j, m, n);
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'E') {
                        board[i][j] = 'O';
                        continue;
                    }
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private static void bfs(char[][] board, int r, int c, int R, int C) {
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{r, c});

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                board[cur[0]][cur[1]] = 'E';
                for (int[] dir : DIRS) {
                    int nr = cur[0] + dir[0];
                    int nc = cur[1] + dir[1];
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C && board[nr][nc] == 'O') {
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }
}
