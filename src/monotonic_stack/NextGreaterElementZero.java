package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class NextGreaterElementZero {
    public static int[] nextGreaterElement(int[] nums) {
        int[] res = new int[nums.length];

        // 对于这道题，stack 的 物理意义是，maintain i 元素后面最早出现的比 num[i] 大的元素

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] >= stack.peekFirst()) {
                stack.pollFirst();
            }
            // empty means no element after i that's larger then nums[i]
            res[i] = stack.isEmpty() ? -1 : stack.peekFirst();
            stack.offerFirst(nums[i]);
        }
        return res;
    }
}
