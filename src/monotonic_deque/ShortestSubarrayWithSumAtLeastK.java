package monotonic_deque;

import java.util.ArrayDeque;
import java.util.Deque;

/** <a href="https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/">...</a> */

public class ShortestSubarrayWithSumAtLeastK {
    static class Dq {

        /** 核心：在 deque 里面 maintain 一个单调递增 的 prefixSum 的 元素的 index */

        // https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C%2B%2BJavaPython-O(N)-Using-Deque

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

}
