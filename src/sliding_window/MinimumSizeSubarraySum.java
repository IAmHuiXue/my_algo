package sliding_window;

/** https://leetcode.com/problems/minimum-size-subarray-sum/ */

public class MinimumSizeSubarraySum {

    /** 核心： 如果都是整数，可以考虑使用 sliding window，这样保证扩大 window 和一定变大，缩小 window 和一定减小 */


    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int count = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                // update count first
                count = Math.min(count, i - left + 1);
                sum -= nums[left++];
            }
        }
        return count == Integer.MAX_VALUE ? 0 : count;
    }
    // O(n)
}
