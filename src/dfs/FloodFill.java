package dfs;

import javafx.scene.control.Cell;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/flood-fill/
 */

public class FloodFill {
    final static int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static class DFS {
        public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
            int curColor = image[sr][sc];
            dfs(image, sr, sc, newColor, curColor);
            return image;
        }

        private void dfs(int[][] image, int sr, int sc, int newColor, int curColor) {
            if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length) {
                return;
            }
            if (image[sr][sc] == newColor || image[sr][sc] != curColor) {
                return;
            }

            image[sr][sc] = newColor;
            for (int[] dir : DIRS) {
                int neiR = sr + dir[0];
                int neiC = sc + dir[1];
                dfs(image, neiR, neiC, newColor, curColor);
            }
        }


        public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
            int curColor = image[sr][sc];
            if (curColor != newColor) { // edge case
                image[sr][sc] = newColor;
                for (int[] dir : DIRS) {
                    int neiR = sr + dir[0];
                    int neiC = sc + dir[1];
                    if (neiR >= 0 && neiR < image.length
                            && neiC >= 0 && neiC < image[0].length &&
                            image[neiR][neiC] == curColor) {
                        floodFill2(image, neiR, neiC, newColor);
                    }
                }
            }
            return image;
        }
    }

    static class BFS {
        public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
            int curColor = image[sr][sc];
            if (curColor != newColor) { // edge case
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{sr, sc});
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    image[cur[0]][cur[1]] = newColor;
                    for (int[] dir : DIRS) {
                        int nextX = dir[0] + cur[0];
                        int nextY = dir[1] + cur[1];
                        if (nextX >= 0 && nextX < image.length && nextY >= 0 && nextY < image[0].length && image[nextX][nextY] == curColor) {
                            queue.offer(new int[]{nextX, nextY});
                        }
                    }
                }
            }
            return image;
        }

        public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
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

        static class Cell {
            int row;
            int col;

            Cell(int r, int c) {
                row = r;
                col = c;
            }
        }
    }

}
