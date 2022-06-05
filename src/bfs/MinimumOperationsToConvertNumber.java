package bfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/minimum-operations-to-convert-number/">...</a>
 */

public class MinimumOperationsToConvertNumber {
        public int minimumOperations(int[] nums, int start, int goal) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        Set<Integer> set = new HashSet<>();
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int val = q.poll();
                if (val == goal) {
                    return res;
                }
                // condition from the problem:
                // 0 <= x <= 1000
                if ((!(val >= 0 && val <= 1000)) || set.contains(val)) {
                    continue;
                }
                set.add(val);
                for (int num : nums) {
                    q.offer(val + num);
                    q.offer(val - num);
                    q.offer(val ^ num);
                }
            }
            res++;
        }
        return -1;
    }
}
