package amazon;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/range-addition/
 */

public class RangeAddition {
    // Update only the first and end element is sufficient.
    // The optimal time complexity is O(k + n)
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        Arrays.fill(res, 0);
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int val = update[2];

            res[start] += val;
            if (end + 1 < length) {
                res[end + 1] -= val;
            }
        }

        int totVal = 0;
        for (int i = 0; i < length; i++) {
            totVal += res[i];
            res[i] = totVal;
        }
        return res;
    }
}
