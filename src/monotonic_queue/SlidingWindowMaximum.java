package monotonic_queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.com/problems/sliding-window-maximum/">...</a>
 */

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {

        // when a[i] and a[j] are both in the sliding window, if
        // a[i] <= a[j] and i < j, we can safely remove a[i] from
        // sliding window, because a[i] can never be the max element
        // in the current sliding window

        // store index

        // maintain a deque to store the index of the elements in a monotonic decreasing order
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // maintain the monotonic decreasing property
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // maintain the validity of the max value
            while (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                deque.pollFirst();
            }
            // when the size reaches k, start to record the result
            if (i >= k - 1) {
                res[index++] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
