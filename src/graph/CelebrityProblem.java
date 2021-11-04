package graph;

/**
 * https://app.laicode.io/app/problem/344?plan=25
 */

public class CelebrityProblem {
    public int celebrity(int[][] matrix) {
        int n = matrix.length;
        // count how many knows it
        int[] numKnownByOthers = new int[n];
        // check if it knows anyone else
        boolean[] knowsOthers = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) { // skip the case where one knows itself
                    continue;
                }
                if (matrix[i][j] == 1) { // means i knows j
                    knowsOthers[i] = true;
                    numKnownByOthers[j]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (numKnownByOthers[i] == n - 1 && !knowsOthers[i]) { // skip the case where one knows itself
                return i;
            }
        }
        return -1;
    }

}
