package dfs.trick;

/**
 * <a href="https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword/">...</a>
 */

public class CheckIfWordCanBePlacedInCrossword {
    static final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public boolean placeWordInCrossword(char[][] board, String word) {
        int rows = board.length, cols = rows > 0 ? board[0].length : 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (isPossible(board, word, row, col, rows, cols)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isPossible(char[][] grid, String word, int row, int col, int rows, int cols) {
//        The start character should not be '#', or when is it not empty, it should be the same char as the first char of word
        if (grid[row][col] == '#' || (grid[row][col] != ' ' && grid[row][col] != word.charAt(0))) {
            return false;
        }

//        Check if word can be placed from each direction.
//        For that you have to check if the element of the opposite direction of the starting point
//        should be either out of index, or should be '#'.
        for (int[] dir : DIRS) {
            int prevR = row - dir[0];
            int prevC = col - dir[1];
            if (isValid(grid, prevR, prevC, rows, cols) && isPossible(grid, word, row, col, rows, cols, dir)) {
                return true;
            }
        }
        return false;
    }

    // We need to pass the direction variable to check along this direction if the word can be placed.
    private static boolean isPossible(char[][] board, String word, int row, int col, int rows, int cols, int[] dir) {
        int i = 0;
        int len = word.length();
        while (i < len && isInRange(row, col, rows, cols) &&
                (board[row][col] == ' ' || board[row][col] == word.charAt(i))) {
            row += dir[0];
            col += dir[1];
            i++;
        }
//        If the pointer does not reach the end of word
        if (i < len) return false;
//        check the validity of the ending character
        return isValid(board, row, col, rows, cols);
    }

    private static boolean isInRange(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private static boolean isValid(char[][] board, int row, int col, int rows, int cols) {
        if (isInRange(row, col, rows, cols)) {
            return board[row][col] == '#';
        }
        return true;
    }
}
