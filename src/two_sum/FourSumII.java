package two_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/4sum-ii/
 */

public class FourSumII {
    // the elements are in the different arrays, cannot use sort + two pointers
    // by enumerating elements, we could naturally avoid duplicates
    static class Naive {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            int cnt = 0;
            Map<Integer, Integer> m = new HashMap<>();
            for (int a : A)
                for (int b : B)
                    m.put(a + b, m.getOrDefault(a + b, 0) + 1);
            for (int c : C)
                for (int d : D)
                    cnt += m.getOrDefault(-(c + d), 0);
            return cnt;
        }
        // time & space: O(n^2)
    }

    // After you solve 4Sum II, an interviewer can follow up with 5Sum II, 6Sum II, and so on.
    // What they are really expecting is a generalized solution for k input arrays.
    // Fortunately, the hashmap approach can be easily extended to handle more than 4 arrays.
    static class Generalized {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            return KSumII.kSumCount(new int[][]{A, B, C, D});
        }


    }

}
