package dq;

import java.util.ArrayDeque;
import java.util.Deque;

/** https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/ */

public class ShortestSubarrayWithSumAtLeastK {
    static class Dq {
        public int shortestSubarray(int[] nums, int k) {
            int N = nums.length;
            int res = N + 1;
            int[] preSum = new int[N + 1];
            for (int i = 0; i < N; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < N + 1; i++) {
                while (!q.isEmpty() && preSum[i] - preSum[q.peekFirst()] >= k) {
                    res = Math.min(res, i - q.pollFirst());
                }
                while (!q.isEmpty() && preSum[q.peekLast()] >= preSum[i]) {
                    q.pollLast();
                }
                q.offerLast(i);
            }
            return res <= N ? res : -1;
        }
    }

    static class PreFixSum {
        public static int shortestSubarray(int[] nums, int k) {
            if (nums.length == 1) {
                return nums[0] >= k ? 1 : -1;
            }
            int[] preSum = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                preSum[i] = i == 0 ? nums[i] : preSum[i - 1] + nums[i];
            }

            // need to find from i to j, preSum[j] - preSum[i] + nums[i] >= k and min(j - i + 1);
            int shortest = Integer.MAX_VALUE;
            for (int j = 1; j < nums.length; j++) {
                for (int i = 0; i <= j; i++) {
                    if (preSum[j] - preSum[i] + nums[i] >= k) {
                        shortest = Math.min(shortest, j - i + 1);
                    }
                }
            }
            return shortest == Integer.MAX_VALUE? -1 : shortest;
        }
    }


}
