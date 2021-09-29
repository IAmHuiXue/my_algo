package dp;

import java.util.HashMap;

public class MaximumSizeSubarraySumEqualsK {
    /**
     * Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
     * If there isn't one, return 0 instead.
     *
     * Note:
     * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
     *
     * Example 1:
     *
     * Given nums = [1, -1, 5, -2, 3], k = 3,
     * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
     *
     * Example 2:
     *
     * Given nums = [-2, -1, 2, 1], k = 1,
     * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
     *
     * Follow Up:
     * Can you do it in O(n) time?
     */
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        // we need to add 0 in hashtable when using prefixSum way
        // otherwise, in for-loop we need to check if sum == k first
        // map.put(0, -1);
        int result = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            map.put(sum, i);
            if (sum == k) {
                // this should be the best result of i
                result = i + 1;
                continue;
            }
            if (map.containsKey(sum - k)) {
                result = Math.max(result, i - map.get(sum - k));
            }
        }
        return result;
    }
    // time: O(n)
    // space: O(n)
}
