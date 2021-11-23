package square;

import java.util.*;

public class MineSweeper {
    public static void main(String[] args) {
        Board board = new Board(4, 5, 3);
        board.printDash();
        board.printNum();
        board.reveal(0, 4);
        board.flag(3, 2);
        board.printReveal();
    }
}

class GameSimulator {
    //todo:
}

class Board {
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    int width;
    int height;
    char[][] board;
    boolean[][] revealed; // to represent the reveal status of each cell
    Set<Point> mines;
    Set<Point> flags;
    static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    public Board(int height, int width, int numMines) {
        mines = new HashSet<>();
        flags = new HashSet<>();
        board = new char[height][width];
        revealed = new boolean[height][width];
        this.width = width;
        this.height = height;
        autoGenMines(numMines);
        arrangeBoard();
    }

    private void autoGenMines(int numMines) {
        while (numMines != 0) {
            Point mine = new Point((int) (Math.random() * height), (int) (Math.random() * width));
            if (mines.add(mine)) {
                numMines--;
            }
        }
    }

    private void arrangeBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mines.contains(new Point(i, j))) {
                    board[i][j] = '*';
                    continue;
                }
                int count = 0;
                for (int[] dir : DIRS) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (!outBound(x, y) && mines.contains(new Point(x, y))) {
                        count++;
                    }
                }
                board[i][j] = (char) (count + '0');
            }
        }
    }

    /**
     * For non-mine tiles, display dash "-".
     */
    public void printDash() {
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.println();
            for (int j = 0; j < width; j++) {
                if (!mines.contains(new Point(i, j))) {
                    System.out.print('-');
                } else {
                    System.out.print(board[i][j]);
                }
                System.out.print(" ");
            }
        }
    }

    /**
     * For non-mine tiles, calculate and display neighboring mine counts.
     */
    public void printNum() {
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.println();
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
        }
    }

    public void reveal(int x, int y) {
        if (outBound(x, y)) {
            System.out.println("Incorrect position input");
            return;
        }
        revealHelper(x, y);
        printReveal();
    }

    private void revealHelper(int x, int y) {
        revealed[x][y] = true;
        if (board[x][y] == '0') {
            for (int[] dir : DIRS) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (!outBound(nx, ny) && !revealed[nx][ny]) {
                    revealHelper(nx, ny);
                }
            }
        }
    }

    /** flag only on an unrevealed cell. */
    public void flag(int x, int y) {
        if (outBound(x, y)) {
            System.out.println("\nCannot flag it because of the incorrect position input");
            return;
        }
        if (revealed[x][y]) {
            System.out.println("\nCannot flag it because the position input has been revealed");
            return;
        }
        flags.add(new Point(x, y));
    }

    boolean check(int x, int y) {
        Point cur = new Point(x, y);
        if (!flags.contains(cur)) {
            System.out.println("\nThe position input has not been flagged");
            return false;
        }
        return mines.contains(cur);
    }

    void printReveal() {
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.println();
            for (int j = 0; j < width; j++) {
                if (revealed[i][j]) {
                    System.out.print(board[i][j]);
                } else if (flags.contains(new Point(i, j))){
                    System.out.print('#');
                } else {
                    System.out.print('-');
                }
                System.out.print(" ");
            }
        }
    }

    private boolean outBound(int i, int j) {
        return i < 0 || j < 0 || i >= height || j >= width;
    }

}
