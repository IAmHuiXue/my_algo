package prefix_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/">https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/</a>
 */

public class MaximumSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        // key=sum, value=index
        Map<Integer, Integer> map = new HashMap<>();
        // for each iterated i,
        // 1. get prefix[i] = prefix[i - 1] + nums[i]
        // 2. check if in the map there is {prefix[i] - k}: which is prefix[j] -> p[i] - p[j] = k
        // 3. if there is one, update longest
        // 4. regardless, put <prefix[i], i> in the map ONLY if there is not a <prefix[i], *> pair in the map yet
        // because if there is one already, its index is far left, which could give a better result than the current one

        map.put(0, -1);
        int prefixSum = 0;
        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (map.containsKey(prefixSum - k)) {
                longest = Math.max(longest, i - map.get(prefixSum - k));
            }
            // if prefixSum[i] has been seen before, we do not want to update it because in this problem
            // we want the leftmost boundary to create the longest subarray
            map.putIfAbsent(prefixSum, i);
        }
        return longest;
    }
}
