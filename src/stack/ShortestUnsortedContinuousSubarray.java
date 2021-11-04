package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 */

public class ShortestUnsortedContinuousSubarray {
    static class UsingStack {
        public int findUnsortedSubarray(int[] nums) {
            // stack stores indices
            Deque<Integer> stack = new ArrayDeque<>();
            int l = nums.length, r = 0;
            // 左到右 scan 一遍，找左边界
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[stack.peekFirst()] > nums[i])
                    l = Math.min(l, stack.pollFirst());
                stack.offerFirst(i);
            }
            stack.clear();
            // 右到左 scan 一遍，找右边界
            for (int i = nums.length - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peekFirst()] < nums[i])
                    r = Math.max(r, stack.pollFirst());
                stack.offerFirst(i);
            }
            return r - l > 0 ? r - l + 1 : 0;
        }
        //O(n)
    }

    static class Sort {
        public int findUnsortedSubarray(int[] nums) {
            int[] snums = nums.clone();
            Arrays.sort(snums);
            int start = snums.length, end = 0;
            for (int i = 0; i < snums.length; i++) {
                if (snums[i] != nums[i]) {
                    start = Math.min(start, i);
                    end = Math.max(end, i);
                }
            }
            return (end - start >= 0 ? end - start + 1 : 0);
        }
        // O(nlog(n))
    }

    static class BruteForce {
        public int findUnsortedSubarray(int[] nums) {
            int l = nums.length, r = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] < nums[i]) {
                        r = Math.max(r, j);
                        l = Math.min(l, i);
                    }
                }
            }
            return r - l < 0 ? 0 : r - l + 1;
        }
        // O(n^2)
    }

}
