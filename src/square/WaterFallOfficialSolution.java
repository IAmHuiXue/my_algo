package square;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

This is a 2D "waterfall" simulation problem (really more like simulating sand).

Given a grid that looks something like:

| a b c d |
| ==  =   |
|   = ==  |
|         |
-----------

Where any lowercase letter represents a particle of water, ' ' represents
empty space, and any other character is an obstacle, iteratively simulate
the particles falling.

Water should move downward if there is a space below it, and randomly either
left or right otherwise (assuming there is nothing in the way).

No particle should move more than one space within a single iteration.

For example, one progression of iterations might look like:

Iteration 0    Iteration 1    Iteration 2    Iteration 3
| a b c d |    |a     c  |    |         |    |         |
| ==  =   |    | ==b = d |    |a== b=c  |    | ==  = c |
|   = ==  | -> |   = ==  | -> |   = ==d | -> |a  =b==  | ->
|         |    |         |    |         |    |       d |
-----------    -----------    -----------    -----------

Iteration 4    Iteration 5
|         |    |         |
| ==  =   |    | ==  =   |
|   = ==c | -> |   = == c|
|a   b d  |    | a b   d |
-----------    -----------


Or alternately (with some different random choices):

Iteration 0    Iteration 1    Iteration 2    Iteration 3
| a b c d |    |  a   c  |    |   a     |    |         |
| ==  =   |    | ==b = d |    | == b=c  |    | ==a = c |
|   = ==  | -> |   = ==  | -> |   = ==d | -> |   =b==  | ->
|         |    |         |    |         |    |       d |
-----------    -----------    -----------    -----------

Iteration 4    Iteration 5
|         |    |         |
| == a=   |    | ==  =   |
|   = ==c | -> |   =a==  |
|    b d  |    |   b d c |
-----------    -----------


Or, with a slightly different board set-up:
Notice here: a, r, e are initially are stacked at the same col
in this case, the bottom-most particle should move first, followed by the higher ones
so that in Iteration 1, a did not move to left or right, but downwards because r has moved
its way for a

Iteration 0    Iteration 1    Iteration 2    Iteration 3
| s q u a |    |  s   u  |    | s       |    |s        |
| ==  = r |    | ==q = a |    | == q=u  |    | ==  = u |
|   = ==e | -> |   = ==r | -> |   = ==a | -> |   =q== a| ->
|         |    |       e |    |       re|    |      re |
-----------    -----------    -----------    -----------

Iteration 4    Iteration 5
|         |    |         |
|s==  =u  |    | ==  = u |
|   = ==a | -> |s  = == a|
|    qr  e|    |   q  re |
-----------    -----------

 */

class WaterFallTest {
    public static void main(String[] args) {
        String[] INPUT = {
                "| s q u a |",
                "| ==  = r |",
                "|   = ==e |",
                "|         |",
                "-----------"
        };
        WaterFall game = new WaterFall(INPUT);
        game.print();
        game.move();
        game.print();
        game.move();
        game.print();
        game.move();
        game.print();
        game.move();
        game.print();
        game.move();
        game.print();

//        WaterFallOfficialSolution waterfall = new WaterFallOfficialSolution(INPUT);
//        System.out.println(waterfall);
//        for (int i = 0; i < 5; i++) {
//            waterfall.step();
//            System.out.println(waterfall);
//        }
    }

}

public class WaterFallOfficialSolution {
    private char[][] grid;
    private Set<Point> particles = new HashSet<>();

    public WaterFallOfficialSolution(String[] input) {
        grid = new char[input.length][];
        for (int i = 0; i < input.length; i++) {
            grid[i] = input[i].toCharArray();
            for (int j = 0; j < input[i].length(); j++) {
                char c = input[i].charAt(j);
                if (c >= 'a' && c <= 'z') {
                    particles.add(new Point(j, i));
                }
            }
        }
    }

    public void step() {
        List<Point> positions = new ArrayList<>(particles);
        // sort to iterate bottom-up
        positions.sort((a, b) -> -Integer.compare(a.y, b.y));

        for (Point pos : positions) {
            if (tryMove(pos, 1, 0)) continue;
            int deltaC = Math.random() > 0.5 ? 1 : -1;
            if (tryMove(pos, 0, deltaC)) continue;
            tryMove(pos, 0, -deltaC);
        }
    }

    private boolean tryMove(Point point, int dr, int dc) {
        int r = point.y + dr;
        int c = point.x + dc;
        if (grid[r][c] == ' ') {
            grid[r][c] = grid[point.y][point.x];
            grid[point.y][point.x] = ' ';
            particles.remove(point);
            particles.add(new Point(c, r));
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            if (sb.length() > 0) sb.append("\n");
            sb.append(new String(row));
        }
        return sb.toString();
    }
}

class WaterFall {
    /*
rectangle
letter is water lower case
empty space
obstacle cannot move,
no end
5 iteration each state print
move the bottom one first

grid[][] char
Map<Integer, Position> ->
int[]{0, 1}{0, -1}
move()
water(i, j)
if (grid[i+1][j] == ' ') -> water(i+1, j)
else {
  random pick left or right and check if that position is empty
    if(yes) -> update the po
      else stay

   */
    char[][] grid;
    int rows;
    int cols;
    List<Particle> particles;

    public WaterFall(String[] input) {
        particles = new ArrayList<>();
        cols = input[0].length() - 2;
        rows = input.length - 1;
        grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cur = input[i].charAt(j + 1);
                if (cur >= 'a' && cur <= 'z') {
                    particles.add(new Particle(cur, new int[]{i, j}));
                }
                grid[i][j] = cur;
            }
        }
    }

    public void move() {
        int[][] dirs = {{0, 1}, {0, -1}};
        particles.sort((a, b) -> Integer.compare(b.po[0], a.po[0]));
        for (Particle w : particles) {
            int[] po = w.po;
            int r = po[0], c = po[1];
            if (r + 1 < rows && grid[r + 1][c] == ' ') {
                grid[r][c] = ' ';
                po[0]++;
                grid[po[0]][c] = w.id;
                continue;
            }
            int index = (int) (Math.random() * 2);
            int[] dir = dirs[index];
            int[] anotherDir = dirs[1 - index];

            if (c + dir[1] >= 0 && c + dir[1] < cols && grid[r][c + dir[1]] == ' ') {
                grid[r][c] = ' ';
                po[1] = c + dir[1];
                grid[po[0]][c] = w.id;
            } else if (c + anotherDir[1] >= 0 && c + anotherDir[1] < cols && grid[r][c + anotherDir[1]] == ' ') {
                grid[r][c] = ' ';
                po[1] = c + anotherDir[1];
                grid[po[0]][c] = w.id;
            }
        }
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            if (sb.length() > 0) sb.append("\n");
            sb.append(new String(row));
        }

        System.out.println(sb.toString());
        System.out.println("");
    }

}

class Particle {
    final Character id;
    int[] po;

    Particle(Character i, int[] p) {
        id = i;
        po = p;
    }
}



