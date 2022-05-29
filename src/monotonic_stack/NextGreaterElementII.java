package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/** <a href="https://leetcode.com/problems/next-greater-element-ii/">...</a> */

public class NextGreaterElementII {

    public int[] nextGreaterElementsDoubleSize(int[] nums) {
        // trick: loop twice
        // [1, 2, 3, 4, 3] -> [1, 2, 3, 4, 3, 1, 2, 3, 4, 3], should cover the requirement
        int[] tmp = new int[2 * nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmp[i] = tmp[i + nums.length] = nums[i];
        }
        int[] res = new int[tmp.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = tmp.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && tmp[i] >= stack.peekFirst()) {
                stack.pollFirst();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peekFirst();
            stack.offerFirst(tmp[i]);
        }
        return Arrays.copyOf(res, nums.length);
    }

    // 不是很直观
    // public int[] nextGreaterElements(int[] nums) {
    //     int n = nums.length;
    //     int[] res = new int[n];
    //     Deque<Integer> stack = new ArrayDeque<>();
    //     for (int i = 2 * n - 1; i >= 0; i--) {
    //         while (!stack.isEmpty() && nums[i % n] >= stack.peekFirst()) {
    //             stack.pollFirst();
    //         }
    //         res[i % n] = stack.isEmpty() ? -1 : stack.peekFirst();
    //         stack.offerFirst(nums[i % n]);
    //     }
    //     return res;
    // }


    // brute force
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = -1;
            for (int j = 1; j < nums.length; j++) {
                if (nums[(i + j) % nums.length] > nums[i]) {
                    res[i] = nums[(i + j) % nums.length];
                    break;
                }
            }
        }
        return res;
    }
}
