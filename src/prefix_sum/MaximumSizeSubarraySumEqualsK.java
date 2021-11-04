package prefix_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 */

public class MaximumSizeSubarraySumEqualsK {
    static class LCSolution {
        public int maxSubArrayLen(int[] nums, int k) {
            int prefixSum = 0;
            int longestSubarray = 0;
            HashMap<Integer, Integer> indices = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                prefixSum += nums[i];
                // Check if all the numbers seen so far sum to k.
                if (prefixSum == k) {
                    longestSubarray = i + 1;
                }
                // If any subarray seen so far sums to k, then
                // update the length of the longest_subarray.
                if (indices.containsKey(prefixSum - k)) {
                    // as p[i] - p[j] represent the sum of the subarray [j + 1, i]
                    // so the length of this subarray is i - (j+1) + 1 = i - j
                    longestSubarray = Math.max(longestSubarray, i - indices.get(prefixSum - k));
                }
                // Only add the current prefix_sum index pair to the
                // map if the prefix_sum is not already in the map.
                if (!indices.containsKey(prefixSum)) {
                    indices.put(prefixSum, i);
                }
            }
            return longestSubarray;
        }
    }

    static class Mine {
        public int maxSubArrayLen(int[] nums, int k) {
            // key=sum, value=index
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            // do we really need to create a prefix array?
            int[] prefixSum = new int[nums.length];
            prefixSum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i];
            }
            int globalMax = 0;
            for (int i = 0; i < nums.length; i++) {
                Integer index = map.get(prefixSum[i] - k);
                if (index != null) {
                    globalMax = Math.max(globalMax, i - index);
                }
                // if prefixSum[i] has been seen before, we do not want to update it because in this problem
                // we want the leftmost boundary to create the longest subarray
                if (!map.containsKey(prefixSum[i])) {
                    map.put(prefixSum[i], i);
                }
            }
            return globalMax;
        }
    }
}
