package sliding_window.dq;

import java.util.ArrayDeque;
import java.util.Deque;

/** https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/ */

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanEqualToLimit {
    public int longestSubarray(int[] nums, int limit) {

        // maxDeque and minDeque are used to store the max and min value in this particular subarray
        // which range is from the left pointer(l) to right pointer(r).
        // thinking this subarray is Array[l , r], so two deques store max and min value individually.

        Deque<Integer> maxd = new ArrayDeque<>(); // maintain the max values in current sliding window
        Deque<Integer> mind = new ArrayDeque<>(); // maintain the min values in current sliding window
        int left = 0;
        int res = 0;
        for (int right = 0; right < nums.length; right++) {
            while (!maxd.isEmpty() && nums[right] > maxd.peekLast()) {
                maxd.pollLast();
            }
            while (!mind.isEmpty() && nums[right] < mind.peekLast()) {
                mind.pollLast();
            }
            maxd.offerLast(nums[right]);
            mind.offerLast(nums[right]);
            if (maxd.peekFirst() - mind.peekFirst() > limit) {
                if (maxd.peekFirst() == nums[left]) {
                    maxd.pollFirst();
                }
                if (mind.peekFirst() == nums[left]) {
                    mind.pollFirst();
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

}