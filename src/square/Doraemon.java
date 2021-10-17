package square;
/*
Part 1:

Doraemon is a robot cat. He has been placed in a room.

The room is represented by a 2D grid of characters.

Doraemon is always facing the top, bottom, left, or right of the grid.

Your job is to program Doraemon to move through the room cell by cell,
reacting to each character he lands on, until he reaches his cat bed. Then he can rest.


Requirements：

The cat starts in the upper-left corner of the room, facing to the right.
If he is standing on a cat bed (@), return the location of the bed and stop.
If he is standing on an arrow (^ v < >), rotate him to face that direction.
Move him to the next space in the direction he is facing.

You can return the coordinates using any data structure you like, as long as it is not ambiguous.


{{'v', ' ', ' ', ' '},
 {' ', '@', ' ', '<'},
 {'>', ' ', ' ', '^'}}

Return: Row 1, Column 1.

*/

import java.util.*;

/**
 * classes:
 * Doraemon
 * <p>
 * Room:
 * char[][] grid
 * int rows
 * int cols
 * <p>
 * int[] move(int r, int c, direction) {
 * Map<key=arrow, value={0, 1}>
 * int[] po -> r, c
 * <p>
 * <p>
 * <p>
 * while (position is invalid) {
 * if (lands at bed) {
 * print sleep & return cur po
 * }
 * if (lands at empty) {
 * keep moving to the next position that he is facing to
 * }
 * if (lands at one of the arrows) {
 * update his direction,
 * keep moving
 * }
 * }
 * <p>
 * }
 */

/*

{{'v', ' ', ' ', ' '},
 {'1', '@', '3', '<'},
 {'>', '2', '-', '^'}}

Part 2:

Now teach Doraemon how to do arithmetic.

As part of the solution, give him a stack of integers to work with.

Additional rules:

0 .. 9 (digit) Push the integer value of this numeral on the stack. (e.g., 1 for ‘1’, etc.)
- (subtract) Subtract the top two values on the stack and push the difference. Order matters: pop a; pop b; push b - a.

When the program terminates, also return the value on the top of the stack, if there is one. Again, data structures are up to you.

The program should still work with the previous rooms.



Part 3:

Now program Doraemon with these additional rules:

* (multiply) pop two values, a and b; push b * a
$ (pop) pop the top of the stack and discard it
: (duplicate) duplicate top of the stack
S (swap) swap the values on the top of the stack
? (conditional) Pop stack; if 0, set direction to right, else left

 */



public class Doraemon {
    protected char dir;

    public Doraemon(char dir) {
        this.dir = dir;
    }
}

class Test {
    public static void main(String[] args) {

        //{{'v', ' ', ' ', ' '},
        // {'1', '@', '3', '<'},
        // {'>', '2', '-', '^'}}
        char[][] input = {{'v', ' ', ' ', ' '}, {'1', '@', '3', '<'}, {'>', '2', '-', '^'}};
        Room room = new Room(input);
        System.out.println(Arrays.toString(room.move(new Doraemon('>'), 0, 0)));
    }
}

class Room {
    private char[][] grid;
    private int rows;
    private int cols;
    private static Map<Character, int[]> dirs = new HashMap<Character, int[]>() {
        {
            put('<', new int[]{0, -1});
            put('>', new int[]{0, 1});
            put('^', new int[]{-1, 0});
            put('v', new int[]{1, 0});
        }
    };


    public Room(char[][] grid) {
        // assume the input grid is valid
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
    }

    public int[] move(Doraemon robot, int r, int c) {
        // assume r, c are valid

        Deque<Integer> stack = new ArrayDeque<>();

        // Now program Doraemon with these additional rules:

        // * (multiply) pop two values, a and b; push b * a
        // $ (pop) pop the top of the stack and discard it
        // : (duplicate) duplicate top of the stack
        // S (swap) swap the values on the top of the stack
        // ? (conditional) Pop stack; if 0, set direction to right, else left

        int[] pos = new int[]{r, c};
        while (validPos(pos)) {
            char cur = grid[pos[0]][pos[1]];
            if (cur == '@') {
                System.out.println("Sleep & Stop");
                if (!stack.isEmpty()) {
                    System.out.println("top of the stack is: " + stack.peekFirst());
                }
                return pos;
            }
            if (cur == '-' && stack.size() >= 2) {
                int last = stack.pollFirst();
                stack.offerFirst(stack.pollFirst() - last);
            }
            if (cur == '*' && stack.size() >= 2) {
                stack.offerFirst(stack.pollFirst() * stack.pollFirst());
            }
            if (cur == '$' && !stack.isEmpty()) {
                stack.pollFirst();
            }
            if (cur == ':' && !stack.isEmpty()) {
                stack.offerFirst(stack.peekFirst());
            }
            if (cur == 'S' && stack.size() >= 2) {
                int last = stack.pollFirst();
                int newLast = stack.peekFirst();
                stack.offerFirst(last);
                stack.offerFirst(newLast);
            }
            if (dirs.containsKey(cur)) {
                robot.dir = cur;
            }
            if (Character.isDigit(cur)) {
                stack.offerFirst(cur - '0');
            }
            // todo:
            // (conditional) Pop stack; if 0, set direction to right, else left
            // do not know what it means


            // else if cur == ' '
            pos[0] = pos[0] + dirs.get(robot.dir)[0];
            pos[1] = pos[1] + dirs.get(robot.dir)[1];
        }
        // otherwise
        System.out.println("Hit the wall, stop");
        if (!stack.isEmpty()) {
            System.out.println("top of the stack is: " + stack.peekFirst());
        }
        return null;
    }

    private boolean validPos(int[] pos) {
        return pos[0] >= 0 && pos[1] >= 0 && pos[0] < rows && pos[1] < cols;
    }

}
