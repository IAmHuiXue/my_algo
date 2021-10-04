package array;

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
            return kSumCount(new int[][]{A, B, C, D});
        }

        private int kSumCount(int[][] lists) {
            Map<Integer, Integer> m = new HashMap<>();
            addToHash(lists, m, 0, 0);
            return countComplements(lists, m, lists.length / 2, 0);
        }

        private void addToHash(int[][] lists, Map<Integer, Integer> m, int i, int sum) {
            if (i == lists.length / 2) {
                m.put(sum, m.getOrDefault(sum, 0) + 1);
                return;
            }
            for (int a : lists[i])
                addToHash(lists, m, i + 1, sum + a);
        }

        private int countComplements(int[][] lists, Map<Integer, Integer> m, int i, int complement) {
            if (i == lists.length) {
                return m.getOrDefault(complement, 0);
            }
            int cnt = 0;
            for (int a : lists[i]) {
                cnt += countComplements(lists, m, i + 1, complement - a);
            }
            return cnt;
        }
    }

}
