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

/*

Background
Minesweeper is a game played on an X-by-Y board of tiles. Hidden among the tiles are M mines. The player is shown the current state of the board, and selects a tile to reveal. If it is revealed to
be a mine, the game ends and the player loses. Otherwise, a number indicating how many neighboring tiles (in eight directions) contain mines is shown. The player repeats the process by selecting a new tile to reveal, continuing until they successfully reveal all non-mine tiles and win the game.
For an interactive example, see http://minesweeperonline.com.
Interview
For this interview, you'll write code that could be used to implement a text-based Minesweeper game.
There won't be nearly enough time to build the full game, but let's try to get as far as we can.
Starter Tasks
Define a data structure to represent the game board.
Construct a new board with randomly placed mines.
Write a method to display which tiles contain mines.

> board = Board(width=5, height=4, mines=3)
> board.print()
- - - - -
- * - - -
- - - * -
- - * - -

Main Tasks
For non-mine tiles, calculate and display neighboring mine counts.

> board.print()
1 1 1 0 0
1 * 2 1 1
1 2 3 * 1
0 1 * 2 1


Set all tiles to be initially hidden. Write a method to reveal a tile.

> board.reveal(x=1, y=2)
- - - - -
- - - - -
- 2 - - -
- - - - -

Bonus Tasks
If the revealed tile has a neighboring mine count of zero, also reveal its neighbors.

> board.reveal(x=4, y=0)
- - 1 0 0
- - 2 1 1
- 2 - - -
- - - - -


Write a method to place a flag on a tile, to indicate where a mine is believed to be hidden.

> board.flag(x=3, y=2)
- - 1 0 0
- - 2 1 1
- 2 - # -
- - - - -


Write a method to check flag correctness for an already revealed tile.

> board.check(x=3, y=1)
- - 1 0 0
- - 2 1 1
- 2 3 # 1
- - - - -

Interviewer Tips
The candidate is initially given all three starter tasks as a warm-up. Once they have a satisfactory
starter solution, they are then given the two main tasks to complete. In general, for a candidate to
score a 4 on the interview, they should be able to produce a coherent solution to both main tasks.
If the candidate still has time remaining, I can then give them one or more of the bonus tasks to
try and score a 5.
When we near the end of the remaining time, I try to help the candidate find a stopping point and
then discuss some of the following topics before wrapping up.
Discussion Topics
How would we determine if the game is over (i.e. if the player has won)?
How would we write tests to verify the correctness of the code?
What sort of safety checks are needed, since we are dealing with user input?



 */

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
        autoPlaceMines(numMines);
        arrangeBoard();
    }

    private void autoPlaceMines(int numMines) {
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
