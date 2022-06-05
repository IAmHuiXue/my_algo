package dfs;

/** <a href="https://leetcode.com/problems/word-search/">...</a> */

public class WordSearch {
    static final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (helper(board, word, 0, i, j, new boolean[board.length][board[0].length])) {
                    return true;
                }
            }
        }
        return false;
    }

    // O(3^L)
    boolean helper(char[][] board, String word, int index, int r, int c, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length ||
                visited[r][c] ||
                board[r][c] != word.charAt(index)) {
            return false;
        }
        visited[r][c] = true;
        for (int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (helper(board, word, index + 1, nr, nc, visited)) {
                return true;
            }
        }
        visited[r][c] = false;
        return false;
    }
}
