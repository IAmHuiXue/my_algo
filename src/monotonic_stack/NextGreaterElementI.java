package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/** <a href="https://leetcode.com/problems/next-greater-element-i/">...</a> */

public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peekFirst() <= nums2[i]) {
                stack.pollFirst();
            }
            map.put(nums2[i], stack.isEmpty() ? - 1 : stack.peekFirst());
            stack.offerFirst(nums2[i]);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}
