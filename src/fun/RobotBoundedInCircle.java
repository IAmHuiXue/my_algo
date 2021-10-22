package fun;

/** https://leetcode.com/problems/robot-bounded-in-circle/ */
// test

public class RobotBoundedInCircle {
    /** limit cycle trajectory **/
    public boolean isRobotBounded(String instructions) {
        // north = 0, east = 1, south = 2, west = 3
        //        up    right    down       left
        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0;

        /** this is a smart way to define directions when we need to pick a specified direction */
        // 一个 dirs[idx] 代表目前的方向
        // 当前方向的右边表示为: dirs[(idx + 1)%4]
        // 当前方向的左边表示为: dirs[(idx + 3)%4]
        int idx = 0; // facing north
        for (char instr : instructions.toCharArray()) {
            if (instr == 'L') {
                idx = (idx + 3) % 4; // cur + 3 -> left to cur !!!!!
            } else if (instr == 'R') {
                idx = (idx + 1) % 4; // cur + 1 -> right to cur !!!!
            } else {
                x += dirs[idx][0];
                y += dirs[idx][1];
            }
        }
        // TODO:
        //  to proof:
        //  After one cycle -> It's a limit cycle trajectory if the robot is back to the center: x = y = 0
        //  or if the robot doesn't face north: idx != 0
        return x == 0 && y == 0 || idx != 0;
    }
}
