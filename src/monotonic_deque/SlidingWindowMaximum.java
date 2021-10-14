package monotonic_deque;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** https://leetcode.com/problems/sliding-window-maximum/ */

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {

        // when a[i] and a[j] are both in the sliding window, if
        // a[i] <= a[j] and i < j, we can safely remove a[i] from
        // sliding window, because a[i] can never be the max element
        // in the current sliding window

        // store index
        Deque<Integer> dq = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // firstly deal with head
            if (!dq.isEmpty() && i - dq.peekFirst() >= k) {
                dq.pollFirst();
            }
            // secondly, check tail
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            // third, add cur element
            dq.offerLast(i);
            // at last, get the current result
            if (i >= k - 1) {
                result.add(nums[dq.peekFirst()]);
            }
        }
        int[] finalResult = new int[result.size()];
        for (int i = 0; i < finalResult.length; i++) {
            finalResult[i] = result.get(i);
        }
        return finalResult;
    }
}
