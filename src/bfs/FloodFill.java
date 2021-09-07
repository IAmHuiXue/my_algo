package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/** https://leetcode.com/problems/flood-fill/ */

public class FloodFill {
    final static int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static class Cell {
        int row;
        int col;

        Cell(int r, int c) {
            row = r;
            col = c;
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int curColor = image[sr][sc];
        if (curColor == newColor) {
            return image;
        }
        Queue<Cell> q = new ArrayDeque<>();
        q.offer(new Cell(sr, sc));
        while (!q.isEmpty()) {
            Cell cur = q.poll();
            image[cur.row][cur.col] = newColor;
            for (int[] dir : DIRS) {
                int neiR = cur.row + dir[0];
                int neiC = cur.col + dir[1];
                if (neiR >= 0 && neiR < image.length
                        && neiC >= 0 && neiC < image[0].length &&
                        image[neiR][neiC] == curColor) {
                    q.offer(new Cell(neiR, neiC));
                }
            }
        }
        return image;
    }
}
