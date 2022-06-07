package dfs;

/**
 * <a href="https://leetcode.com/problems/battleships-in-a-board/">...</a>
 */

public class BattleshipsInABoard {
    public int countBattleships(char[][] board) {
        //count of our battleships
        int res = 0;
        //iterate through the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    res++;
                    dfs(board, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] board, int i, int j) {
        board[i][j] = '.';
        //up
        if (i > 0 && board[i - 1][j] == 'X') {
            dfs(board, i - 1, j);
        }
        //down
        if (i < board.length - 1 && board[i + 1][j] == 'X') {
            dfs(board, i + 1, j);
        }
        //left
        if (j > 0 && board[i][j - 1] == 'X') {
            dfs(board, i, j - 1);
        }
        //right
        if (j < board[i].length - 1 && board[i][j + 1] == 'X') {
            dfs(board, i, j + 1);
        }
    }
}
