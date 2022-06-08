package data_structure;

/** <a href="https://leetcode.com/problems/design-tic-tac-toe/">...</a> */

public class DesignTicTacToe {
    private int[][] grid;
    private static int[] players = new int[3];

    public DesignTicTacToe(int n) {
        grid = new int[n][n];
        players[1] = 1;
        players[2] = 2;
    }

    // return player to represent win, 0 to represent no win
    public int move(int row, int col, int player) {
        grid[row][col] = players[player];
        return win(player, row, col) ? player : 0;
    }

    private boolean win(int player, int row, int col) {
        return validInRow(player, row) || validInCol(player, col)
                || validInDiagonal(player, row, col) || validInAntiDiagonal(player, row, col);
        // NOTICE:
        // when row == col, as long as it meets either diagonal or anti-diagonal condition we should call winner
        // do not directly return false when it only fails one of the conditions!
    }

    private boolean validInRow(int player, int row) {
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[row][i] != players[player]) {
                return false;
            }
        }
        return true;
    }

    private boolean validInCol(int player, int col) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] != players[player]) {
                return false;
            }
        }
        return true;
    }

    private boolean validInDiagonal(int player, int row, int col) {
        if (row != col) { // the position is not on the diagonal line
            return false;
        }
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][i] != players[player]) {
                return false;
            }
        }
        return true;
    }

    private boolean validInAntiDiagonal(int player, int row, int col) {
        if (grid.length - 1 - row != col) {
            return false;
        }
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][grid.length - 1 - i] != players[player]) {
                return false;
            }
        }
        return true;
    }
}
