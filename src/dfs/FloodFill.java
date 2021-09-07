package dfs;

/**
 * https://leetcode.com/problems/flood-fill/
 */

public class FloodFill {
    final static int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
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
        if (curColor != newColor) { // THIS IS IMPORTANT!
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
