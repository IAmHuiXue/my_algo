package binary_search;

import java.util.List;

/**
 * https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
 */
interface BinaryMatrix {
    int get(int row, int col);

    List<Integer> dimensions();
}

public class LeftmostColumnWithAtLeastAOne {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int rows = dimensions.get(0);
        int cols = dimensions.get(1);
        int leftMostIndex = cols;
        for (int curRow = 0; curRow < rows; curRow++) {
            // every time when we invoke the bs on the current row,
            // we can feel free to use the current leftMostIndex as the right bound
            leftMostIndex = Math.min(leftMostIndex, binarySearch(binaryMatrix, curRow, leftMostIndex));
        }
        return leftMostIndex == cols ? -1 : leftMostIndex;
    }

    private int binarySearch(BinaryMatrix binaryMatrix, int row, int rightBound) {
        int l = 0;
        int r = rightBound - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (binaryMatrix.get(row, mid) == 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return binaryMatrix.get(row, l) == 1 ? l : rightBound;
    }
}
