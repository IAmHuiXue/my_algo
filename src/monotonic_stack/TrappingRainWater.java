package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 */

public class TrappingRainWater {
    static class MonotonicStack {
        public int trap(int[] height) {
            // maintain 一个单调递减栈
            // store index
            /** 只有当 当前元素大于栈顶元素的时候，如果 poll 出之后 stack 还存在元素，此时才会形成 u 型凹槽蓄水 */

            Deque<Integer> stack = new ArrayDeque<>();
            int sum = 0;
            for (int right = 0; right < height.length; right++) {
                while (!stack.isEmpty() && height[stack.peekFirst()] < height[right]) {
                    int lowest = stack.pollFirst();
                    if (stack.isEmpty()) {
                        break;
                    }
                    int left = stack.peekFirst();
                    int minHeight = Math.min(height[left], height[right]);
                    sum += (minHeight - height[lowest]) * (right - left - 1);
                }
                stack.offerFirst(right);
            }
            return sum;
        }
    }

    static class DP {
        public int trap(int[] height) {

            int[] left = new int[height.length];

            left[0] = height[0];
            for (int i = 1; i < height.length; i++) {
                left[i] = Math.max(left[i - 1], height[i]);
            }

            int[] right = new int[height.length];
            right[height.length - 1] = height[height.length - 1];
            for (int i = height.length - 2; i >= 0; i--) {
                right[i] = Math.max(right[i + 1], height[i]);
            }

            int trap = 0;
            for (int i = 1; i < height.length - 1; i++) {
                trap += Math.min(left[i], right[i]) - height[i];
            }
            return trap;
        }
        // time: O(3*n)
        // space: O(2*n)
    }
}
