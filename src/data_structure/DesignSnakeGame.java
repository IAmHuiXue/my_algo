package data_structure;

import java.util.*;

public class DesignSnakeGame {
    public static void main(String[] args) {
        SnakeGame sg = new SnakeGame(3, 3, new int[][]{{2, 0}, {0, 0}, {0, 2}, {2, 2}});
        System.out.println(sg.move("D"));
        System.out.println(sg.move("D"));
        System.out.println(sg.move("R"));
        System.out.println(sg.move("U"));
        System.out.println(sg.move("U"));
        System.out.println(sg.move("L"));
        System.out.println(sg.move("D"));
        System.out.println(sg.move("R"));
        System.out.println(sg.move("R"));
        System.out.println(sg.move("U"));
        System.out.println(sg.move("L"));
        System.out.println(sg.move("D"));
    }
}

class SnakeGame {
    Queue<int[]> foods;
    int score = 0;
    int rows;
    int cols;
    Deque<int[]> body; // new footprint offer from head, obsolete footprint poll from tail
    Set<String> bodyDetails;
    static final Map<String, int[]> DIRS = new HashMap<>();

    static {
        DIRS.put("U", new int[]{-1, 0});
        DIRS.put("D", new int[]{1, 0});
        DIRS.put("L", new int[]{0, -1});
        DIRS.put("R", new int[]{0, 1});
    }

    public SnakeGame(int width, int height, int[][] food) {
        rows = height;
        cols = width;
        foods = new ArrayDeque<>();
        for (int[] f : food) {
            foods.offer(f);
        }
        body = new ArrayDeque<>();
        body.offerFirst(new int[]{0, 0});
        bodyDetails = new HashSet<>();
        bodyDetails.add(0 + "," + 0);
    }

    public int move(String direction) {
        int[] dir = DIRS.get(direction);
        int[] head = body.peekFirst();
        int[] position = new int[]{head[0] + dir[0], head[1] + dir[1]};
        if (hitWall(position) || hitItself(position)) {
            return -1;
        }
        int[] curFood = foods.peek();
        if (curFood != null && curFood[0] == position[0] && curFood[1] == position[1]) {
            eat(position);
        } else {
            // move and remove obsolete
            body.offerFirst(position);
            bodyDetails.add(position[0] + "," + position[1]);
            int[] tail = body.pollLast();
            bodyDetails.remove(tail[0] + "," + tail[1]);
        }
        return score;
    }

    boolean hitWall(int[] po) {
        int r = po[0];
        int c = po[1];
        return r < 0 || c < 0 || r >= rows || c >= cols;
    }

    boolean hitItself(int[] po) {
        int r = po[0];
        int c = po[1];
        int[] tail = body.peekLast();
        // attention.
        // if the po[] is not the body -> false
        if (!bodyDetails.contains(r + "," + c)) {
            return false;
        }
        // if the po[] is the body but not the tail -> true
        if (!(tail[0] == r && tail[1] == c)) {
            return true;
        }
        // if the po[] is the tail:
        // if po[] == food[] -> true
        // if po[] != food[] -> false
        int[] curFood = foods.peek();
        return !(curFood == null || !(curFood[0] == r && curFood[1] == c));
    }

    void eat(int[] position) {
        // add and no need to remove
        foods.poll();
        body.offerFirst(position);
        bodyDetails.add(position[0] + "," + position[1]);
        score++;
    }

}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
