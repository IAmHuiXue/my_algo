package dfs;

/** https://leetcode.com/problems/minesweeper/*/

public class Minesweeper {
    static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        int r = click[0];
        int c = click[1];

        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }
        // if the cell is not a mine
        update(board, m, n, new int[m][n], r, c, new boolean[m][n]);
        return board;
    }

    void update(char[][] board, int m, int n, int[][] counts, int r, int c, boolean[][] visited) {
        int numNei = 0;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M') {
                numNei++;
            }
        }
        // if there are surrounding mines, update the cell with the num and return
        if (numNei != 0) {
            board[r][c] = (char) (numNei + '0');
            return;
        }
        // otherwise, process on the neighboring cells
        visited[r][c] = true;
        board[r][c] = 'B';
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nc >= 0 && nr < m && nc < n && !visited[nr][nc]) {
                update(board, m, n, counts, nr, nc, visited);
            }
        }
    }
}
